package com.onTime.forgotPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.onTime.R;
import com.onTime.DataBaseHelper;

public class ForgotPassword extends AppCompatActivity {

    EditText email;
    CardView submit;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        db = new DataBaseHelper(this);
        email =findViewById(R.id.signInEmailField);
        submit = findViewById(R.id.forgotPasswordSubmitBtn);
        submit.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

             String em_email=email.getText().toString();

             Boolean checkUser= db.checkEmail(em_email);

             if(checkUser==true){ //email exists in the database
                Intent intent =new Intent(getApplicationContext(), resetPassword.class);
                intent.putExtra("email",em_email );
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "The email doesn't exist", Toast.LENGTH_SHORT).show();
             }
        };

        });
}
}
