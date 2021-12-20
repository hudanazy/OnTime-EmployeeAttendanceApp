package com.onTime.employer.viewAllEmployees.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.onTime.DataBaseHelper;
import com.onTime.R;
import com.onTime.employer.viewAllEmployees.adapters.ViewAllEmployeesAdapter;
import com.onTime.employer.viewAllEmployees.models.ViewAllEmployeesModel;
import com.onTime.singIn.SignIn;

import java.util.ArrayList;

public class ViewAllEmployees extends AppCompatActivity {

    RecyclerView employeesListRv;
    DataBaseHelper dataBaseHelper;
    ArrayList<ViewAllEmployeesModel> viewAllEmployeesModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employees);

        initViews();
    }

    private void initViews() {
        viewAllEmployeesModelArrayList = new ArrayList<>();
        dataBaseHelper = new DataBaseHelper(this);
        employeesListRv = findViewById(R.id.employeesListRv);
        employeesListRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        viewAllEmployeesModelArrayList = dataBaseHelper.getAllEmployeesData(String.valueOf(SignIn.companyId));

        employeesListRv.setAdapter(new ViewAllEmployeesAdapter(viewAllEmployeesModelArrayList));

    }
}