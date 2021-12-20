package com.onTime.employer.addOffice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.onTime.DataBaseHelper;
import com.onTime.R;
import com.onTime.singIn.SignIn;

public class AddOffice extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    Button chooseLocationBtn;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_office);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        chooseLocationBtn = findViewById(R.id.chooseLocationBtn);
        DB = new DataBaseHelper(this);


        chooseLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMap != null) {
                    Log.e("TAG", "onClick: " + mMap.getCameraPosition().target.latitude);
                    Log.e("TAG", "onClick: " + mMap.getCameraPosition().target.longitude);

                    if (DB.addOffice(SignIn.companyId, mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude))
                    {
                        Toast.makeText(AddOffice.this, "Office added successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(AddOffice.this, "An error occurred, please try again", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }
}