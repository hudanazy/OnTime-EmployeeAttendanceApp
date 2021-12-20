package com.onTime.singIn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.onTime.R;

public class ChooseRole extends AppCompatActivity implements View.OnClickListener {

    CardView chooseRoleEmployerCv, chooseRoleEmployeeCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);

        initViews();
    }

    private void initViews() {
        chooseRoleEmployerCv = findViewById(R.id.chooseRoleEmployerCv);
        chooseRoleEmployeeCv = findViewById(R.id.chooseRoleEmployeeCv);

        chooseRoleEmployerCv.setOnClickListener(this);
        chooseRoleEmployeeCv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chooseRoleEmployerCv: {
                Intent intent = new Intent(ChooseRole.this, SignIn.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;

            }
            case R.id.chooseRoleEmployeeCv: {
                Intent intent = new Intent(ChooseRole.this, SignIn.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            }
        }
    }
}