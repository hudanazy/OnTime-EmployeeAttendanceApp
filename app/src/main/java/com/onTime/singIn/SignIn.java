package com.onTime.singIn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.onTime.EmployeeAttendance.Attendance;
import com.onTime.R;
import com.onTime.employee.landingScreen.activities.EmployeeLandingScreen;
import com.onTime.DataBaseHelper;
import com.onTime.employer.landingScreen.EmployerLandingScreen;
import com.onTime.forgotPassword.ForgotPassword;
import com.onTime.signUp.SignUpEmployer;
import com.onTime.signUp.models.EmployerSignUpModel;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    TextView tv_forget_password, textView2;
    LinearLayout signInDontHaveLl;
    CardView loginBtn;
    EditText email, pass;
    public static EmployerSignUpModel employerSignUpModel;
    public static int companyId;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initViews();
    }

    private void initViews() {
        signInDontHaveLl = findViewById(R.id.signInDontHaveLl);
        tv_forget_password = findViewById(R.id.tv_forget_password);
        loginBtn = findViewById(R.id.loginBtn);
        textView2 = findViewById(R.id.textView2);
        signInDontHaveLl.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
        textView2.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        email = (EditText) findViewById(R.id.signInEmailField);
        pass = (EditText) findViewById(R.id.signInPasswordField);
        DB = new DataBaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signInDontHaveLl) {
            startActivity(new Intent(SignIn.this, SignUpEmployer.class));
        }
        if (v.getId() == R.id.tv_forget_password) {
            startActivity(new Intent(SignIn.this, ForgotPassword.class));
        }
        if (v.getId() == R.id.loginBtn) {
            String email2 = email.getText().toString();
            String password = pass.getText().toString();
            if (email2.equals("") || password.equals("")) //fields are empty
                Toast.makeText(getApplicationContext(), "Please fill all the fields ", Toast.LENGTH_SHORT).show();
            else {
                int check = DB.checkEmailAndPassword(email2, password);
                if (check == 1) {   //email and password exists in the database

                    Toast.makeText(SignIn.this, "Successfully signed in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignIn.this, EmployerLandingScreen.class));
                } else if (check == 2) {
                    Toast.makeText(SignIn.this, "Successfully signed in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignIn.this, Attendance.class));

                } else {
                    Toast.makeText(SignIn.this, "Wrong email or password", Toast.LENGTH_SHORT).show();

                }
            }
        }
        if (v.getId() == R.id.textView2) {
            startActivity(new Intent(SignIn.this, EmployerLandingScreen.class));
        }
    }
}