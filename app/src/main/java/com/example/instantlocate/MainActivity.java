package com.example.instantlocate;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.mukul56.instantlocate.InstantLocate;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    Button ntwkLocation, GpsLocation;
    double lon, lat;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.name);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        final InstantLocate location = new InstantLocate(this);
        if(!location.providerEnabled()){
            location.showSettingsAlert();
        }

        ntwkLocation = (Button) findViewById(R.id.network);
        GpsLocation = (Button) findViewById(R.id.gps);
        ntwkLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    location.getContinuousLocation();
                    lat = location.getlatitude();
                    lon = location.getlongitude();
                    Toast.makeText(MainActivity.this, "latitude :" + lat + " longitude :" + lon, Toast.LENGTH_SHORT).show();
                }
        });

        GpsLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.stop();
            }
        });

    }
}