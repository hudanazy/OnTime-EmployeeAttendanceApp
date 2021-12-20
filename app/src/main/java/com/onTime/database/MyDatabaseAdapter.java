//package com.onTime.database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class MyDatabaseAdapter {
//    MyDbHelper myDbHelper;
//
//    public MyDatabaseAdapter(Context context) {
//        myDbHelper = new MyDbHelper(context);
//    }
//
//    public long insertEmployer(String firstName, String lastName, String companyName, String userName, String email, String pass) {
//        SQLiteDatabase dbb = myDbHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(MyDbHelper.FIRST_NAME, firstName);
//        contentValues.put(MyDbHelper.LAST_NAME, lastName);
//        contentValues.put(MyDbHelper.COMPANY_NAME, companyName);
//        contentValues.put(MyDbHelper.USERNAME, userName);
//        contentValues.put(MyDbHelper.EMAIL, email);
//        contentValues.put(MyDbHelper.PASSWORD, pass);
//        long id = dbb.insert(MyDbHelper.TABLE_NAME, null, contentValues);
//        return id;
//    }
//
//    public String getEmployer() {
//        SQLiteDatabase db = myDbHelper.getWritableDatabase();
//        String[] columns = {MyDbHelper.UID, MyDbHelper.FIRST_NAME,MyDbHelper.LAST_NAME,MyDbHelper.COMPANY_NAME,MyDbHelper.USERNAME,MyDbHelper.EMAIL,MyDbHelper.PASSWORD};
//        Cursor cursor = db.query(MyDbHelper.TABLE_NAME, columns, null, null, null, null, null);
//        StringBuffer buffer = new StringBuffer();
//        while (cursor.moveToNext()) {
//            int cid = cursor.getInt(cursor.getColumnIndex(MyDbHelper.UID));
//            String name = cursor.getString(cursor.getColumnIndex(MyDbHelper.FIRST_NAME));
//            String password = cursor.getString(cursor.getColumnIndex(MyDbHelper.PASSWORD));
//            buffer.append(cid + "   " + name + "   " + password + " \n");
//        }
//        return buffer.toString();
//    }
//
////        public  int delete(String uname)
////        {
////            SQLiteDatabase db = myDbHelper.getWritableDatabase();
////            String[] whereArgs ={uname};
////
////            int count =db.delete(MyDatabaseAdapter.TABLE_NAME ,MyDatabaseAdapter.NAME+" = ?",whereArgs);
////            return  count;
////        }
////
////        public int updateName(String oldName , String newName)
////        {
////            SQLiteDatabase db = myDbHelper.getWritableDatabase();
////            ContentValues contentValues = new ContentValues();
////            contentValues.put(MyDatabaseAdapter.NAME,newName);
////            String[] whereArgs= {oldName};
////            int count =db.update(MyDatabaseAdapter.TABLE_NAME,contentValues, MyDatabaseAdapter.NAME+" = ?",whereArgs );
////            return count;
////        }
//
//    static class MyDbHelper extends SQLiteOpenHelper {
//        private static final String DATABASE_NAME = "myDatabase";    // Database Name
//        private static final String TABLE_NAME = "employersTable";   // Table Name
//        private static final int DATABASE_Version = 1;    // Database Version
//        private static final String UID = "_id";     // Column I (Primary Key)
//        private static final String FIRST_NAME = "FirstName";    //Column II
//        private static final String LAST_NAME = "LastName";    //Column II
//        private static final String COMPANY_NAME = "CompanyName";    //Column II
//        private static final String USERNAME = "Username";    //Column II
//        private static final String EMAIL = "EMAIL";    //Column II
//        private static final String PASSWORD = "Password";    //Column II
//        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
//                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIRST_NAME + " VARCHAR(255) , " + LAST_NAME + " VARCHAR(255) , " + COMPANY_NAME + " VARCHAR(255) , " + USERNAME + " VARCHAR(255) , " + EMAIL + " VARCHAR(255) ," + PASSWORD + " VARCHAR(225));";
//        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
//        private Context context;
//
//        public MyDbHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_Version);
//            this.context = context;
//        }
//
//        public void onCreate(SQLiteDatabase db) {
//
//            try {
//                db.execSQL(CREATE_TABLE);
//            } catch (Exception e) {
//            }
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            try {
//                db.execSQL(DROP_TABLE);
//                onCreate(db);
//            } catch (Exception e) {
//            }
//        }
//    }
//}
