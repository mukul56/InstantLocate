package com.example.instantlocate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import androidx.annotation.Nullable;



public class instaLocate extends Service implements LocationListener {

    private Context context;
    boolean checkGPs = false, checkNtwr = false, locaPossible = false;
    Location location;
    double latitide, longitude;
    protected LocationManager locationManager;
    Geocoder geocoder;


    public instaLocate(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        getlocation();
    }

    private void getlocation() {
        try {
            checkGPs = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            checkNtwr = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (checkNtwr && checkGPs) {
                locaPossible = true;
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        getGpsLocation();
                    }
                });
            }

        } catch (Exception e) {
            Log.d("location", e + "");
        }
    }

    @SuppressLint("MissingPermission")
    public void getNtwrLocation() {

        if (checkNtwr) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0, this);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                latitide = getlatitude();
                longitude = getlongitude();
            }

        }
    }

    @SuppressLint("MissingPermission")
    private void getGpsLocation() {

        while(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (checkGPs) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, this);
            }
        }


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


    public synchronized void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);


        alertDialog.setTitle("GPS is not Enabled!");

        alertDialog.setMessage("Do you want to turn on GPS?");


        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
                getlocation();
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

   /* public String getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            return obj.getSubLocality();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
