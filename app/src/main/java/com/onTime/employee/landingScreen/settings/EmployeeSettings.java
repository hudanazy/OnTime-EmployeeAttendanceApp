package com.onTime.employee.landingScreen.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.CompoundButton;

import com.onTime.R;

public class EmployeeSettings extends AppCompatActivity {

    SwitchCompat settingsLocationSwitch;
    boolean GpsStatus ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_settings);

        settingsLocationSwitch = findViewById(R.id.settingsLocationSwitch);
        CheckGpsStatus();
        settingsLocationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Intent intent1;

                    intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent1);

            }
        });
    }

    public void CheckGpsStatus(){
     LocationManager   locationManager = (LocationManager)EmployeeSettings.this.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(GpsStatus == true) {
            settingsLocationSwitch.setChecked(true);
        } else {
            settingsLocationSwitch.setChecked(false);
        }
    }
}