package com.onTime;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.onTime.employer.addEmployee.employeeModel;
import com.onTime.employer.landingScreen.officeModel;
import com.onTime.employer.viewAllEmployees.models.ViewAllEmployeesModel;
import com.onTime.signUp.models.EmployerSignUpModel;
import com.onTime.singIn.SignIn;

import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;
import static com.onTime.singIn.SignIn.employerSignUpModel;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String EMP_TABLE = "EMP_TABLE";
    public static final String COMP_TABLE = "COMP_TABLE";
    public static final String ATTENDANCE_TABLE = "EMP_TABLE";
    public static final String OFFICE_TABLE = "offices";
    public static final String ID_COMP = "ID_COMP";
    public static final String ID_EMP = "ID_EMP";
    public static final String ID_OFFICE = "ID_OFFICE";
    public static final String ID_ATTENDANCE = "ID_ATTENDANCE";
    public static final String COMP_PASSWORD = "COMP_PASSWORD";
    public static final String comp_id = "comp_id";
    public static final String office_id = "office_id";
    public static final String emp_id = "emp_id";
    public static final String DATE = "DATE";
    public static final String CHECK_IN_TIME = "CHECK_IN_TIME";
    public static final String CHECK_OUT_TIME = "CHECK_OUT_TIME";
    public static final String EMPLOYEE_FNAME = "EMPLOYEE_FNAME";
    public static final String EMPLOYEE_LNAME = "EMPLOYEE_LNAME";
    public static final String COMPANY_NAME = "COMPANY_NAME";
    public static final String EMPLOYEE_USERNAME = "EMPLOYEE_USERNAME";
    public static final String EMPLOYEE_EMAIL = "email";
    public static final String EMPLOYEE_PASSWORD = "password";


    public static final String EMPLOYER_TABLE = "EMPLOYER_TABLE";
    private static final String LONG = "LONG";
    private static final String LAT = "LAT";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "employees7.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //        String createEmployeeTable = "CREATE TABLE  EMP_TABLE  (  ID_EMP  INTEGER PRIMARY KEY AUTOINCREMENT, " + EMPLOYEE_FNAME + " TEXT, " + EMPLOYEE_LNAME + " TEXT, " + COMPANY_NAME + " TEXT, " + EMPLOYEE_USERNAME + " TEXT, "+ EMPLOYEE_EMAIL+ " TEXT PRIMARY KEY, "+EMPLOYEE_PASSWORD+" TEXT, "+ " FOREIGN KEY ("+ comp_id +") " +
//                " REFERENCES "+ COMP_TABLE+" ("+ID_COMP+") " +
//                "ON DELETE CASCADE ON UPDATE CASCADE, "+" FOREIGN KEY ("+ office_id +") " +
//                " REFERENCES "+ OFFICE_TABLE+" ("+ID_OFFICE+") " +
//                "ON DELETE CASCADE ON UPDATE CASCADE)";

//
//        String createCompanyTable = "CREATE TABLE " + COMP_TABLE + " ( " + ID_COMP + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COMP_PASSWORD + " TEXT)";
//
//
//        String createOfficeLocationTable = "CREATE TABLE " + OFFICE_TABLE + " ( " + ID_OFFICE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LAT + " TEXT, " + LONG + " TEXT, " + " FOREIGN KEY ("+ comp_id +") " +
//                " REFERENCES "+ COMP_TABLE+" ("+ID_COMP+") " +
//                "ON DELETE CASCADE ON UPDATE CASCADE)";
//
//
//        String createAttendanceTable = "CREATE TABLE " + ATTENDANCE_TABLE + " ( " + ID_ATTENDANCE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " TEXT, " + CHECK_IN_TIME + " TEXT, " + CHECK_OUT_TIME + " TEXT, " +" FOREIGN KEY ("+ emp_id +")" +
//                " REFERENCES "+ COMP_TABLE+" ("+ID_EMP+") "+
//                "ON DELETE CASCADE ON UPDATE CASCADE)";
//
//
//
        String createRolesTable = "CREATE TABLE roles (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
                "    name varchar(255) NOT NULL )";

        String createEmployeeTable = "CREATE TABLE employees (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    company_id INTEGER ,\n" +
//                "    office_id INTEGER ,\n" +
                "    user_id INTEGER ,\n" +
                "    age INTEGER ,\n" +
                "    lng varchar(30) ,\n" +
                "    lat varchar(30) ,\n" +
                "    officeLng varchar(30) ,\n" +
                "    officeLat varchar(30) ,\n" +
                "    status varchar(30),\n" +
                "    FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE,\n" +
                "    FOREIGN KEY (user_id) REFERENCES companies(id) ON DELETE CASCADE\n" +
//                "    FOREIGN KEY (office_id) REFERENCES offices(id) ON DELETE CASCADE\n" +
                ");";


        String createCompanyTable = " CREATE TABLE companies (\n" +
                "    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,\n" +
                "    username varchar(255) NOT NULL,\n" +
                "    fName varchar(255) ,\n" +
                "    lName varchar(255) ,\n" +
                "    email varchar(255) ,\n" +
                "    password varchar(255) ,\n" +
                "    role_id INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE\n" +
                ");";


        String createOfficeLocationTable = "CREATE TABLE offices (\n" +
                "    id INTEGER NOT NULL ,\n" +
                "    company_id INTEGER NOT NULL,\n" +
                "    location varchar(255),\n" +
                "    lat varchar(255)NOT NULL,\n" +
                "    lng varchar(255)NOT NULL,\n" +
                "    PRIMARY KEY (id),\n" +
                "    FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE\n" +
                ");\n";


        String createAttendanceTable = "CREATE TABLE attendence (\n" +
                "    id INTEGER NOT NULL ,\n" +
                "    employee_id INTEGER NOT NULL,\n" +
                "    checkin varchar(10)NOT NULL,\n" +
                "    checkout varchar(10)NOT NULL,\n" +
                "    a_date date ,\n" +
                "    status varchar(30),\n" +
                "    PRIMARY KEY (id),\n" +
                "    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE\n" +
                ");";

        db.execSQL(createRolesTable);
        db.execSQL("INSERT INTO roles (name)\n" +
                "VALUES ('company');");

        db.execSQL("INSERT INTO roles (name)\n" +
                "VALUES ('employee');");

        db.execSQL("INSERT INTO roles (name)\n" +
                "VALUES ('office');");

        db.execSQL(createCompanyTable);
        db.execSQL(createOfficeLocationTable);
        db.execSQL(createEmployeeTable);
        db.execSQL(createAttendanceTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + "employees7.db" + "'");

    }

    public boolean addOne(employeeModel emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EMPLOYEE_FNAME, employeeModel.getFname());
        cv.put(EMPLOYEE_LNAME, employeeModel.getLname());
        cv.put(COMPANY_NAME, employeeModel.getCompany_name());
        cv.put(EMPLOYEE_USERNAME, employeeModel.getUsername());
        cv.put(EMPLOYEE_EMAIL, employeeModel.getEmail());
        cv.put(EMPLOYEE_PASSWORD, employeeModel.getPassword());

        long insert = db.insert(EMP_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean registerEmployer(EmployerSignUpModel emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("fName", emp.getfName());
        cv.put("lName", emp.getlName());
        cv.put("email", emp.getEmail());
        cv.put("password", emp.getPassword());
        cv.put("username", emp.getUserName());
        cv.put("role_id", emp.getRole());

        long insert = db.insert("companies", null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addEmployee(String fName, String lName, String username,
                               String lat, String lng, String officeLat, String officeLng, int companyId, String role, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        ContentValues cv1 = new ContentValues();

        cv.put("fName", fName);
        cv.put("lName", lName);
        cv1.put("officeLat", officeLat);
        cv1.put("officeLng", officeLng);
        cv.put("email", email);
        cv.put("password", password);

        cv.put("username", username);
        cv.put("role_id", role);

        long insert = db.insert("companies", null, cv);

        Log.e(TAG, "addEmployee: " + insert);
        cv1.put("company_id", companyId);
        cv1.put("user_id", insert);
        long insert1 = db.insert("employees", null, cv1);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addOffice(int companyId, Double lat, Double lng) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("company_id", companyId);
        cv.put("lat", lat);
        cv.put("lng", lng);


        long insert = db.insert(OFFICE_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
//
//        cv.put(LAT, officeModel.getLAT());
//        cv.put(LONG, officeModel.getLONG());
//        cv.put(comp_id, officeModel.getComp_ID());
//
//
//        long insert = db.insert(OFFICE_TABLE, null, cv);
//        if (insert == -1) {
//            return false;
//        } else {
//            return true;
//        }
    }

    public LatLng getOffice(String companyId) {
        String officeLat, officeLng;
        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from offices  where " + "company_id" + " =?  ", new String[]{companyId});

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                officeLat = cursor.getString(cursor.getColumnIndex("lat"));
                officeLng = cursor.getString(cursor.getColumnIndex("lng"));

                return new LatLng(Double.parseDouble(officeLat), Double.parseDouble(officeLng));

            }
        }
        return null;
    }

    public LatLng getOfficeLatLng(String companyId) {
        String officeLat, officeLng;
        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from employees  where " + "user_id" + " =?  ", new String[]{companyId});

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                officeLat = cursor.getString(cursor.getColumnIndex("officeLat"));
                officeLng = cursor.getString(cursor.getColumnIndex("officeLng"));


                Log.e(TAG, "getOfficeLatLng: "+officeLat );
                Log.e(TAG, "getOfficeLatLng: "+officeLng );
                return new LatLng(Double.parseDouble(officeLat), Double.parseDouble(officeLng));

            }
        }
        return null;
    }

    public boolean deleteOne(String email) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String queryString = "DELETE FROM companies  WHERE " + EMPLOYEE_EMAIL + " = ?" ;
//        Cursor cursor = db.rawQuery(queryString, new String[]{email});
//        if (cursor.moveToFirst()) {
//            return true;
//        }
//        return false;
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("companies", EMPLOYEE_EMAIL + "=?", new String[]{email}) > 0;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from " + "companies" + " where " + EMPLOYEE_EMAIL + " =?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public int checkEmailAndPassword(String email, String password) {
        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from companies  where " + EMPLOYEE_EMAIL + " =? and " + EMPLOYEE_PASSWORD + " =?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                if (cursor.getString(cursor.getColumnIndex("role_id")).equals("1")) {
                    SignIn.companyId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                    employerSignUpModel = new EmployerSignUpModel("" + cursor.getString(cursor.getColumnIndex("username")),
                            "" + cursor.getString(cursor.getColumnIndex("fName")),
                            "" + cursor.getString(cursor.getColumnIndex("lName")),
                            "" + cursor.getString(cursor.getColumnIndex("email")),
                            "" + cursor.getString(cursor.getColumnIndex("username")),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex("role_id"))));
                    return 1;
                } else {
                    SignIn.companyId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                    return 2;
                }
            }
        }
        return 0;
    }

    public ArrayList<LatLng> getAllEmployeesLocations(String companyId) {
        ArrayList<LatLng> latLngArrayList = new ArrayList<>();

        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM employees WHERE company_id = ?", new String[]{companyId});


//        Cursor cursor = myDB.rawQuery("SELECT * FROM employees WHERE company_id = ? and lat!=null",  new String[]{companyId});

        Log.e(TAG, "getAllEmployees:ID  " + companyId);
        Log.e(TAG, "getAllEmployees: " + DatabaseUtils.dumpCursorToString(cursor));
        if (cursor.moveToFirst()) {

            do {
                String lat = cursor.getString(cursor.getColumnIndex("lat"));
                String lng = cursor.getString(cursor.getColumnIndex("lng"));

                if (lat != null)
                    latLngArrayList.add(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)));
            }
            while (cursor.moveToNext());
        }
        return latLngArrayList;
    }


    public ArrayList<ViewAllEmployeesModel> getAllEmployeesData(String companyId) {

        ArrayList<String> user_ids_list = new ArrayList<>();
        ArrayList<ViewAllEmployeesModel> viewAllEmployeesModelArrayList = new ArrayList<>();

        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT user_id FROM employees WHERE company_id = ? ", new String[]{companyId});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String dir = cursor.getString(cursor.getColumnIndex("user_id"));
                    Log.e(TAG, "getAllEmployeesData: " + dir
                    );
                    user_ids_list.add(dir);
                } while (cursor.moveToNext());
            }
        }
//
        StringBuilder ids = new StringBuilder();

        for (int i = 0; i < user_ids_list.size(); i++) {
            if (i != user_ids_list.size() - 1) {
                ids.append(user_ids_list.get(i)).append(",");
            } else {
                ids.append(user_ids_list.get(i));

            }
        }

        Cursor cursor1 = myDB.rawQuery("SELECT * FROM " + "companies" +
                        " LEFT JOIN " + "attendence" + " ON " + "companies" + "." + "id" + " = " +
                        "attendence" + "." + "employee_id" + " WHERE  " + "companies" + "." + "id " + " IN (" + ids + ")",
                null);
        Log.e("Cursor Object", DatabaseUtils.dumpCursorToString(cursor1));

        if (cursor1 != null && cursor1.moveToFirst()) {

            do {
                String name = cursor1.getString(cursor1.getColumnIndex("fName")) + " " + cursor1.getString(cursor1.getColumnIndex("lName"));
                String status = cursor1.getString(cursor1.getColumnIndex("status"));
                String checkin = cursor1.getString(cursor1.getColumnIndex("checkin"));
                String checkout = cursor1.getString(cursor1.getColumnIndex("checkout"));
                String a_date = cursor1.getString(cursor1.getColumnIndex("a_date"));

                viewAllEmployeesModelArrayList.add(new ViewAllEmployeesModel("" + name, "" + status, "" + checkin, "" + checkout, "" + a_date));
            }
            while (cursor1.moveToNext());
        }


        return viewAllEmployeesModelArrayList;
    }

    public boolean updatePassword(String email, String newPass) {
        SQLiteDatabase myDB = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", newPass);
        int result = myDB.update("companies", cv, "email =?", new String[]{email});
        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean AddCheckIn(int employeeId, String checkIn, String checkOut, String date, String status, Double lat, Double lng) {
        SQLiteDatabase myDB = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("employee_id", employeeId);
        cv.put("checkin", checkIn);
        cv.put("checkout", checkOut);
        cv.put("a_date", date);
        cv.put("status", status);

        long insert = myDB.insert("attendence", null, cv);
        if (insert == -1) {
            return false;
        } else {

            Log.e(TAG, "AddCheckIn: " + lat + " , " + lng + " , " + employeeId);
            updateLocation(String.valueOf(employeeId), lat, lng);
            return true;
        }


//        int id = 0;
//        int empID = 0;
//        SQLiteDatabase myDB = getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("id", ++id);
//        cv.put("employee_id", empID);
//        cv.put("checkin", In);
//        cv.put("checkout", "--");
//        cv.put("a_date", d);
//
//        long insert = myDB.insert("attendence", null, cv);
//        if (insert == -1) {
//            return false;
//        } else {
//            return true;
//        }


    }

    private boolean updateLocation(String employeeId, Double lat, Double lng) {

        SQLiteDatabase myDB = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("lat", lat);
        cv.put("lng", lng);
        long result = myDB.update("employees", cv, "user_id=?", new String[]{employeeId});

        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean AddCheckOut(String checkInTime, String employeeId, String out) {
        SQLiteDatabase myDB = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("checkout", out);
        long result = myDB.update("attendence", cv, "checkin = ? and employee_id=?", new String[]{checkInTime, employeeId});

        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getTimeLine(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM attendence WHERE employee_id=?", new String[]{id});

        return c;
    }

    public int getEmpID(TextView empName) {

        int empID = 0;

        SQLiteDatabase myDB = this.getReadableDatabase();
        String Query = "select ID_EMP from EMP_TABLE where EMPLOYEE_USERNAME" + empName;

        Cursor cursor = myDB.rawQuery(Query, null);

        if (cursor.moveToFirst()) {

            empID = cursor.getInt(cursor.getColumnIndex("ID_EMP"));

        }

        return empID;

    }

}
