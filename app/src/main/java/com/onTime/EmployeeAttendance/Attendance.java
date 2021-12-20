package com.onTime.EmployeeAttendance;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.onTime.DataBaseHelper;
import com.onTime.R;
import com.onTime.employee.landingScreen.adapters.EmployeeTimelineAdapter;
import com.onTime.employee.landingScreen.settings.EmployeeSettings;
import com.onTime.singIn.SignIn;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

//public LocationManager locationManager;
//public LocationListener locationListener = new MyLocationListener();
//private boolean gps_enable = false; //to check if the gps is enabled or not
// private boolean network_enable = false;
//generate address
// Geocoder geocoder;
//For Notification
//NotificationManager manager;
//private static final String CHANNEL_1_ID = "channel1";

public class Attendance extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    //Initialize variable
    //ALL THE DATA IN THE SCREEN

    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationManager locationManager;
    private LocationRequest mLocationRequest;
    Button CheckIn;
    Button CheckOut;
    TextView empName;
    RecyclerView employeeTimelineRv;
    TextView role;
    CardView employeeSettingsCv;
    TextView inTime;
    TextView TodayDate;
    TextView OutTime;
    LatLng officeLatLng;
    private String checkInTime = "";
    DataBaseHelper db;
    FusedLocationProviderClient fusedLocationProviderClient;
    ArrayList<TimeLineModel> timeLineModelArrayList = new ArrayList<>();
    double lat, lon; //to store lat and lon to check ??
    Calendar calendar;
    boolean isCheckedIn = false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_landing_screen);//Set layout
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(getApplicationContext());
        isCheckedIn = sharedPreferences.getBoolean("isCheckedIn", false);
        checkInTime = sharedPreferences.getString("checkInTime", checkInTime);
        //Assign variables

        db = new DataBaseHelper(this);

        officeLatLng = db.getOfficeLatLng(String.valueOf(SignIn.companyId));


        CheckIn = (Button) findViewById(R.id.checkInBtn);
        CheckOut = (Button) findViewById(R.id.checkOutBtn);
        empName = (TextView) findViewById(R.id.textView);
        role = (TextView) findViewById(R.id.textView3);
        inTime = (TextView) findViewById(R.id.InTime);
        TodayDate = (TextView) findViewById(R.id.Date);
        OutTime = (TextView) findViewById(R.id.OutTime);
        employeeTimelineRv = findViewById(R.id.employeeTimelineRv);
        employeeSettingsCv = findViewById(R.id.employeeSettingsCv);
        employeeTimelineRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //Initialize fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        CheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isCheckedIn) {
                    RegisterAttendance();
                } else {
                    Toast.makeText(Attendance.this, "Please checkout first", Toast.LENGTH_SHORT).show();
                }
            }
        });


        CheckOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (isCheckedIn) {
                    RegisterLeave();
                    isCheckedIn = false;
                } else {
                    Toast.makeText(Attendance.this, "Please check in first", Toast.LENGTH_SHORT).show();
                }

            }

        });
        employeeSettingsCv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Attendance.this, EmployeeSettings.class));
            }

        });


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //get Timeline

        getTimeLine();
    }

    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isCheckedIn", isCheckedIn);
        editor.putString("checkInTime", checkInTime);
        editor.commit();
    }

    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isCheckedIn", isCheckedIn);
        editor.putString("checkInTime", checkInTime);
        editor.commit();
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(getApplicationContext());
        isCheckedIn = sharedPreferences.getBoolean("isCheckedIn", false);
        checkInTime = sharedPreferences.getString("checkInTime", checkInTime);
    }

    private void createNotification(String title, String message) {
        int NOTIFICATION_ID = 234;
        NotificationManager notificationManager = (NotificationManager) Attendance.this.getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID = "my_channel_01";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(Attendance.this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message);

        Intent resultIntent = new Intent(Attendance.this, Attendance.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(Attendance.this);
        stackBuilder.addParentStack(Attendance.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void RegisterAttendance() {
        boolean GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (GpsStatus == false) {
            Toast.makeText(Attendance.this, "Please switch on the location", Toast.LENGTH_SHORT).show();
        } else {


            officeLatLng = db.getOfficeLatLng(String.valueOf(SignIn.companyId));
        Location officeLocation = new Location("locationA");
        officeLocation.setLatitude(officeLatLng.latitude);
        officeLocation.setLongitude(officeLatLng.longitude);

        Location userLocation = new Location("locationA");
        userLocation.setLatitude(lat);
        userLocation.setLongitude(lon);


        double distance = officeLocation.distanceTo(userLocation);

        Log.e("TAG", "offLat: " + officeLatLng.latitude);
        Log.e("TAG", "offLng: " + officeLatLng.longitude);
        Log.e("TAG", "lat: " + lat);
        Log.e("TAG", "lng: " + lon);
        Log.e("TAG", "RegisterAttendance: " + distance);


        if (distance < 500) {

            createNotification("Check In", "You have checked in");
            isCheckedIn = true;

            //Get the date of attendance
            calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String currentDate = simpleDateFormat.format(calendar.getTime());

            //Time of CheckIn
            calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm:ss a");
            String currentTime = simpleDateFormat2.format(calendar.getTime());


            //int EmpFK=db.getEmpID(empName);
            //Store to db

            boolean isInserted = db.AddCheckIn(SignIn.companyId, currentTime, "--", currentDate, "Present", lat, lon);

            if (isInserted) {
                checkInTime = currentTime;
                //SET THE CHECK IN TIME & DATE IN UI
//            inTime.setText(currentTime);
//            TodayDate.setText(currentDate);
                Toast.makeText(Attendance.this, "Attendance Time & date is recorded ", Toast.LENGTH_LONG).show();

            } else
                Toast.makeText(Attendance.this, "Attendance Time & date is not recorded ", Toast.LENGTH_LONG).show();


            //get Timeline
            getTimeLine();
        } else {
            Toast.makeText(this, "Please go to office location to check in", Toast.LENGTH_SHORT).show();
        }
    }

}

    public void RegisterLeave() {

        //Time of CheckOut
        calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm:ss a");
        String currentTime = simpleDateFormat2.format(calendar.getTime());//get the time of check out


        //Store to db
        boolean isInserted = db.AddCheckOut(checkInTime, String.valueOf(SignIn.companyId), currentTime); //need to add the attribute

        if (isInserted) {
//            OutTime.setText(currentTime);
            Toast.makeText(Attendance.this, "Checkout Time is recorded", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(Attendance.this, "Checkout Time is not recorded ", Toast.LENGTH_LONG).show();

        createNotification("Check Out", "You have checked out");

        getTimeLine();
//
    }

    private void getTimeLine() {
//        get Timeline
        timeLineModelArrayList = new ArrayList<>();
        Cursor timeLines = db.getTimeLine(String.valueOf(SignIn.companyId));
        if (timeLines.moveToFirst()) {
            do {
                // Passing values
                String inTime = timeLines.getString(2);
                String outTime = timeLines.getString(3);
                String date = timeLines.getString(4);


                timeLineModelArrayList.add(new TimeLineModel(inTime, date, outTime));

                // Do something Here with values
            } while (timeLines.moveToNext());
        }

        employeeTimelineRv.setAdapter(new EmployeeTimelineAdapter(timeLineModelArrayList));

    }


    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startLocationUpdates();
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLocation == null) {
            startLocationUpdates();
        }
        if (mLocation != null) {
            lat = mLocation.getLatitude();
            lon = mLocation.getLongitude();
            Log.e("TAG", "onLocationChanged: " + lat);
            Log.e("TAG", "onLocationChanged: " + lon);
        } else {
            // Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
        }

    }

    protected void startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10)
                .setFastestInterval(10);
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("TAG", "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("TAG", "Connection failed. Error: " + connectionResult.getErrorCode());

    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}