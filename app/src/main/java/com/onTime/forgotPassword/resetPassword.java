package com.onTime.forgotPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.onTime.R;
import com.onTime.DataBaseHelper;
import com.onTime.singIn.SignIn;

public class resetPassword extends AppCompatActivity {
        TextView email;
        EditText password, retypedPassword;
    CardView resetBtn;
        DataBaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

//        email= (TextView) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.newPassword);
        retypedPassword= (EditText) findViewById(R.id.retypedNewPassword);
        resetBtn= (CardView) findViewById(R.id.resetPasswordBtn);
        DB=new DataBaseHelper(this);

        Intent intent = getIntent();
//        email.setText(intent.getStringExtra("email"));
        
        resetBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email2 = intent.getStringExtra("email");
                String pass = password.getText().toString();
                String retypedPass = retypedPassword.getText().toString();
                if (! (pass.equals(retypedPass)) ){
                    Toast.makeText(resetPassword.this,"Passwords are not matching",Toast.LENGTH_SHORT).show();
                    //password.getText().clear();
                    //retypedPassword.getText().clear();
                } else {
                    boolean checkNewPassword = DB.updatePassword(email2, pass);
                    if (checkNewPassword) { //password updated
                        Intent intent = new Intent(getApplicationContext(), SignIn.class);
                        startActivity(intent);
                        Toast.makeText(resetPassword.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(resetPassword.this, "Password is not updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}