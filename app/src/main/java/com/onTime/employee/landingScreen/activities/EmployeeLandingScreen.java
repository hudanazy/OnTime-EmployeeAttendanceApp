package com.onTime.employee.landingScreen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.onTime.R;
import com.onTime.employee.landingScreen.adapters.EmployeeTimelineAdapter;
import com.onTime.employee.landingScreen.settings.EmployeeSettings;
import com.onTime.employee.landingScreen.stopWatch.StopWatch;

public class EmployeeLandingScreen extends AppCompatActivity implements View.OnClickListener {

    RecyclerView employeeTimelineRv;
    Button checkInBtn, workingTimeBtn, checkOutBtn;

    final int MSG_START_TIMER = 0;
    final int MSG_STOP_TIMER = 1;
    final int MSG_UPDATE_TIMER = 2;

    StopWatch timer = new StopWatch();
    final int REFRESH_RATE = 100;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_START_TIMER:
                    timer.start(); //start timer
                    mHandler.sendEmptyMessage(MSG_UPDATE_TIMER);
                    break;

                case MSG_UPDATE_TIMER:
                    workingTimeBtn.setText("" + timer.getElapsedTimeHour()+":"+timer.getElapsedTimeMin()+":"+timer.getElapsedTimeSecs());
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER, REFRESH_RATE); //text view is updated every second,
                    break;                                  //though the timer is still running
                case MSG_STOP_TIMER:
                    mHandler.removeMessages(MSG_UPDATE_TIMER); // no more updates.
                    timer.stop();//stop timer
                    workingTimeBtn.setText("" + timer.getElapsedTimeHour()+":"+timer.getElapsedTimeMin()+":"+timer.getElapsedTimeSecs());
                    break;

                default:
                    break;
            }
        }
    };

    CardView employeeSettingsCv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_landing_screen);

        initViews();
    }

    private void initViews() {
        employeeTimelineRv = findViewById(R.id.employeeTimelineRv);
        employeeSettingsCv = findViewById(R.id.employeeSettingsCv);
       // checkInBtn = findViewById(R.id.checkInBtn);
        //workingTimeBtn = findViewById(R.id.workingTimeBtn);
        //checkOutBtn = findViewById(R.id.checkOutBtn);
        employeeTimelineRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        employeeTimelineRv.setAdapter(new EmployeeTimelineAdapter());
/*
        checkInBtn.setOnClickListener(this);
        checkOutBtn.setOnClickListener(this);

 */
        employeeSettingsCv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
/*
        if (v.getId() == R.id.checkInBtn) {
            mHandler.sendEmptyMessage(MSG_START_TIMER);
        }
        if (v.getId() == R.id.checkOutBtn) {
            mHandler.sendEmptyMessage(MSG_STOP_TIMER);
        }

 */

        if (v.getId() == R.id.employeeSettingsCv) {
            startActivity(new Intent(EmployeeLandingScreen.this, EmployeeSettings.class));
        }
    }



}