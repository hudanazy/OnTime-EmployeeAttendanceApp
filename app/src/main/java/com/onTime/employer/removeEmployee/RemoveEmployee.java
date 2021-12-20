package com.onTime.employer.removeEmployee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.onTime.DataBaseHelper;
import com.onTime.R;

public class RemoveEmployee extends AppCompatActivity {

    CardView reg;
    EditText email;
    String emp_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_employee);
        reg = findViewById(R.id.registerBtn);
        email = findViewById(R.id.signUpEmailField);
        DataBaseHelper db = new DataBaseHelper(RemoveEmployee.this);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emp_email = email.getText().toString();
                if (db.deleteOne(emp_email)) {
                    Toast.makeText(RemoveEmployee.this, "Employee deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RemoveEmployee.this, "This email does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}