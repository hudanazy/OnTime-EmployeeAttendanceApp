package com.onTime.employer.showEmployeesOnMap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.onTime.DataBaseHelper;
import com.onTime.R;
import com.onTime.singIn.SignIn;

import java.util.ArrayList;

public class ShowEmployeesOnMap extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employees_on_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        DB = new DataBaseHelper(ShowEmployeesOnMap.this);

        Log.e("TAG", "onMapReady: " + SignIn.companyId);
        ArrayList<LatLng> latLngArrayList = DB.getAllEmployeesLocations(String.valueOf(SignIn.companyId));

        Log.e("TAG", "onMapReady: " + latLngArrayList.toString());

        addMarkers(latLngArrayList);
    }

    private void addMarkers(ArrayList<LatLng> latLngArrayList) {

        for (int i = 0; i < latLngArrayList.size(); i++) {
            mMap.clear();
            MarkerOptions markerOptions = new MarkerOptions();

            Log.e("TAG", "addMarkers: " + latLngArrayList.get(i).latitude);
            Log.e("TAG", "addMarkers: " + latLngArrayList.get(i).longitude);
            // Setting the position for the marker
            markerOptions.position(new LatLng(latLngArrayList.get(i).latitude, latLngArrayList.get(i).longitude));

            markerOptions.title(latLngArrayList.get(i).latitude + " : " + latLngArrayList.get(i).longitude);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker));

            mMap.addMarker(markerOptions);
        }
    }
}