package com.onTime.signUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.onTime.DataBaseHelper;
import com.onTime.R;
import com.onTime.signUp.models.EmployerSignUpModel;
import com.onTime.singIn.SignIn;

public class SignUpEmployer extends AppCompatActivity {
    TextView registerSigninBtn;
    EditText signUpFirstNameField, signUpLastNameField, signUpCompanyNameField, signUpUsernameField, signUpEmailField, signUpPasswordField, signUpConfirmPasswordField;
    CardView registerBtn;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_employer);

        initViews();
    }

    private void initViews() {
        signUpFirstNameField = findViewById(R.id.signUpFirstNameField);
        signUpLastNameField = findViewById(R.id.signUpLastNameField);
        signUpCompanyNameField = findViewById(R.id.signUpCompanyNameField);
        signUpUsernameField = findViewById(R.id.signUpUsernameField);
        signUpEmailField = findViewById(R.id.signUpEmailField);
        signUpPasswordField = findViewById(R.id.signUpPasswordField);
        signUpConfirmPasswordField = findViewById(R.id.signUpConfirmPasswordField);
        registerBtn = findViewById(R.id.registerBtn);
        registerSigninBtn= findViewById(R.id.registerSigninBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    registerEmployer();
                }
            }
        });
        registerSigninBtn.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               startActivity(new Intent(SignUpEmployer.this, SignIn.class));
           }
        });
        DB = new DataBaseHelper(this);

    }

    private boolean validateForm() {
        if (TextUtils.isEmpty(signUpFirstNameField.getText().toString().trim())) {
            Toast.makeText(this, "Please enter first name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(signUpLastNameField.getText().toString().trim())) {
            Toast.makeText(this, "Please enter last name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(signUpCompanyNameField.getText().toString().trim())) {
            Toast.makeText(this, "Please enter company name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(signUpUsernameField.getText().toString().trim())) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(signUpEmailField.getText().toString().trim())) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(signUpPasswordField.getText().toString().trim())) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(signUpConfirmPasswordField.getText().toString().trim())) {
            Toast.makeText(this, "Please enter confirm password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!signUpConfirmPasswordField.getText().toString().trim().equals(signUpPasswordField.getText().toString().trim())) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void registerEmployer() {
        boolean check = DB.registerEmployer(new EmployerSignUpModel("" + signUpUsernameField.getText().toString().trim()
                , "" + signUpFirstNameField.getText().toString().trim(),
                "" + signUpLastNameField.getText().toString().trim(),
                "" + signUpEmailField.getText().toString().trim(),
                "" + signUpPasswordField.getText().toString().trim(), 1));

        if (check) {
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "An error occurred, please try again", Toast.LENGTH_SHORT).show();
        }
    }
}