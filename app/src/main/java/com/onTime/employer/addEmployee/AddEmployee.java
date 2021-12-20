package com.onTime.employer.addEmployee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.onTime.EmployeeAttendance.Attendance;
import com.onTime.R;
import com.onTime.DataBaseHelper;
import com.onTime.signUp.models.EmployerSignUpModel;
import com.onTime.singIn.SignIn;

import java.io.IOException;
import java.util.Locale;

public class AddEmployee extends AppCompatActivity {


    CardView registerBtn;
    EditText fname, lname, username, email, company_name, password;
    DataBaseHelper DB;
    FusedLocationProviderClient fusedLocationProviderClient;
    Double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        DB = new DataBaseHelper(AddEmployee.this);
        registerBtn = findViewById(R.id.registerBtn);
        fname = (EditText) findViewById(R.id.signUpFirstNameField);
        lname = (EditText) findViewById(R.id.signUpLastNameField);
        username = (EditText) findViewById(R.id.signUpUsernameField);
        email = (EditText) findViewById(R.id.signUpEmailField);
        company_name = (EditText) findViewById(R.id.signUpCompanyNameField);
        password = (EditText) findViewById(R.id.signUpPasswordField);


        getLocation();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            employeeModel employee_model;

            @Override
            public void onClick(View v) {

                addEmployee();
//                try {
//                    employee_model = new employeeModel(-1, fname.getText().toString(), lname.getText().toString(), company_name.getText().toString(), username.getText().toString(), email.getText().toString(), password.getText().toString());
//                    Toast.makeText(AddEmployee.this, employee_model.toString(), Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    Toast.makeText(AddEmployee.this, "Error registering employee, email already exists.", Toast.LENGTH_SHORT).show();
//                    employee_model = new employeeModel(-1, "error", "none", "none", "none", "none", "none");
//
//                }
            }

            //Toast.makeText(MainActivity.this, "Success = "+success, Toast.LENGTH_SHORT).show();


        });


    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {

                    try {

                        Geocoder geocoder = new Geocoder(AddEmployee.this, Locale.getDefault());
                        java.util.List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );

                        //Set latitude and longitude

                        lat = addresses.get(0).getLatitude();

                        lng = addresses.get(0).getLongitude();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    private void addEmployee() {
        LatLng officeLatLng = DB.getOffice(String.valueOf(SignIn.companyId));

        if (officeLatLng != null) {
            Log.e("TAG", "addEmployee: " + officeLatLng.latitude + " , " + officeLatLng.longitude);
            boolean check = DB.addEmployee("" + fname.getText().toString().trim()
                    , "" + lname.getText().toString().trim(),
                    "" + username.getText().toString().trim(),
                    "" + lat,
                    "" + lng, "" + officeLatLng.latitude, "" + officeLatLng.longitude, SignIn.companyId, "2", "" + password.getText().toString().trim(), "" + email.getText().toString().trim());

            if (check) {
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "An error occurred, please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please add an office first", Toast.LENGTH_SHORT).show();

        }

    }
}