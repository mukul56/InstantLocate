package com.mukul56.instantlocate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

public class InstantLocate extends Service implements LocationListener {

    protected Thread myGpsThread;
    private Context context;
    private boolean checkGPs = false, checkNtwr = false;
    private Location location;
    private double latitide, longitude;
    private LocationManager locationManager;


    public InstantLocate(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        initLocation();
    }


    //Initializing location
    private void initLocation() {
        checkGPs = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        checkNtwr = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        final Handler handler = new Handler();
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getGpsLocation();
                    }
                });
                synchronized (this) {
                    try {
                        wait(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        locationManager.removeUpdates(InstantLocate.this);
                    }
                }, 5000);
            }
        };
        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }

    //Function to get instant Location
    @SuppressLint("MissingPermission")
    public void instantLocation() {
        if (checkNtwr) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0, this);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                latitide = getlatitude();
                longitude = getlongitude();
            }
        }
    }

    public void getContinuousLocation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                while (checkGPs) {
                    try {
                        getGpsLocation();
                        Log.i("loc", "location called");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @SuppressLint("MissingPermission")
    private void getGpsLocation() {
        Log.i("Loca", "called");
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, this);
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }

    public synchronized void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("GPS is not Enabled!");
        alertDialog.setMessage("Do you want to turn on GPS?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
                initLocation();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    public double getlongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }
        return longitude;
    }

    public double getlatitude() {
        if (location != null) {
            latitide = location.getLatitude();
        }
        return latitide;
    }

    public boolean providerEnabled() {
        return checkGPs && checkNtwr;
    }


    //To stop location service
    public void stop() {
        checkGPs = false;
        locationManager.removeUpdates(this);
        onDestroy();
    }

    public boolean isGpsEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
