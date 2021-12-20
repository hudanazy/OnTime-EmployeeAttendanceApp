package com.onTime.employer.landingScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.onTime.R;
import com.onTime.employer.addEmployee.AddEmployee;
import com.onTime.employer.addOffice.AddOffice;
import com.onTime.employer.removeEmployee.RemoveEmployee;
import com.onTime.employer.showEmployeesOnMap.ShowEmployeesOnMap;
import com.onTime.employer.viewAllEmployees.activities.ViewAllEmployees;

public class EmployerLandingScreen extends AppCompatActivity implements View.OnClickListener {

    CardView addEmployeeCv, viewAllEmployeesCv, showEmployeesOnMapCv,removeEmployeeCv,addOfficeCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_landing_screen);

        initViews();
    }

    private void initViews() {
        addEmployeeCv = findViewById(R.id.addEmployeeCv);
        viewAllEmployeesCv = findViewById(R.id.viewAllEmployeesCv);
        showEmployeesOnMapCv = findViewById(R.id.showEmployeesOnMapCv);
        removeEmployeeCv = findViewById(R.id.removeEmployeeCv);
        addOfficeCv = findViewById(R.id.addOfficeCv);

        addEmployeeCv.setOnClickListener(this);
        viewAllEmployeesCv.setOnClickListener(this);
        showEmployeesOnMapCv.setOnClickListener(this);
        removeEmployeeCv.setOnClickListener(this);
        addOfficeCv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addEmployeeCv) {
            startActivity(new Intent(EmployerLandingScreen.this, AddEmployee.class));
        }
        //changed to add office location
        if (v.getId() == R.id.viewAllEmployeesCv) {
            startActivity(new Intent(EmployerLandingScreen.this, ViewAllEmployees.class));

        }
        if (v.getId() == R.id.showEmployeesOnMapCv) {
            startActivity(new Intent(EmployerLandingScreen.this, ShowEmployeesOnMap.class));

        } if (v.getId() == R.id.removeEmployeeCv) {
            startActivity(new Intent(EmployerLandingScreen.this, RemoveEmployee.class));

        }if (v.getId() == R.id.addOfficeCv) {
            startActivity(new Intent(EmployerLandingScreen.this, AddOffice.class));

        }
    }
}