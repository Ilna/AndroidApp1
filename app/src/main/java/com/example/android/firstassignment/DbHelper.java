package com.example.android.firstassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;


import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "DATATABLE";
    public static final int DB_VERSION = 1;
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_USERID = "_USERID";
    public static final String COLUMN_LONGTITUDE = "_LONGTITUDE";
    public static final String COLUMN_LATITUDE = "_LATITUDE";
    public static final String COLUMN_TIME_STAMP = "timeStamp";

    //constructor of DbHelper
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Create Database
    //https://www.tutorialspoint.com/sqlite/sqlite_using_autoincrement.htm
    //https://stackoverflow.com/questions/16864128/how-to-insert-time-stamp-into-an-sqlite-database-column-using-the-function-time
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USERID + " TEXT NOT NULL," +
                COLUMN_LONGTITUDE + " REAL NOT NULL," +
                COLUMN_LATITUDE + " REAL NOT NULL," +
                COLUMN_TIME_STAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //insert to the DB the DataTable object
    @RequiresApi(api = Build.VERSION_CODES.N)
    public long insert(DataTable dataTable) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();//Create an object of this class


        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_USERID, dataTable.getUserid());
        values.put(DbHelper.COLUMN_LONGTITUDE, dataTable.getLongtitude());
        values.put(DbHelper.COLUMN_LATITUDE, dataTable.getLatitude());
        values.put(DbHelper.COLUMN_TIME_STAMP, dataTable.getTimestamp());
        return sqLiteDatabase.insert(DB_NAME, null, values);

    }

    //Query to get resutl for the 3Activity
    public String getRowByUseridTimestamp(String userid, String timestamp) {

        String table = DB_NAME;
        String[] columns = {"_ID", "_LONGTITUDE", "_LATITUDE"};
        String selection = "_USERID=? AND timeStamp=?";
        String[] selectionArgs = {userid, timestamp};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = "10";


        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        //get the correct  column for each attribute
        if (cursor.moveToFirst()) {
            String id = cursor.getString(0);
            String lon = cursor.getString(1);
            String lat = cursor.getString(2);

            //Create a String of results for the 3Activity
            String result = "UserID : " + userid + "\n\n" + "Id : " + id + "\n\n" + "Longtitude : " + " " + lon + "\n\n" + "Latitude:" + lat + "\n\n" + "Timestamp : " + timestamp;
            return result;
        }
        return null;
    }

    //Spinner's timestamplist (https://www.javatpoint.com/android-sqlite-example-with-spinner)
    //get all the timestamps for a specific userid
    public List getAllLabels(String userid) {

        //Getting all labels returns list of labels
        List<String> list = new ArrayList<String>();

        // Select Timestamp Query

        String table = DB_NAME;
        String[] columns = {"timeStamp"};
        String selection = "_USERID=?";
        String[] selectionArgs = {userid};
        String groupBy = null;
        String having = null;
        String orderBy = null;


        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);

        // looping through all rows and adding to list
        List itemtimestamps = new ArrayList<>();
        if (cursor.moveToFirst()) {

            do {
                String itemtimestamp = cursor.getString(0);
                itemtimestamps.add(itemtimestamp);

            } while (cursor.moveToNext());

        }


        // returning timestamps
        return itemtimestamps;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List examQuery() {

        //not working
        String table = DB_NAME;
        String[] columns = {"_ID", "_LONGTITUDE", "_LATITUDE","_USERID","timestamp"};
        //String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;


        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);

        List examList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            DataTable d;
            do {
                String id =  cursor.getString(0);
                String userid =  cursor.getString(1);
                Double longtitude =  cursor.getDouble(2);
                Double latitude =  cursor.getDouble(3);
                String timestamp =  cursor.getString(4);
                d = new DataTable(userid,longtitude,latitude,timestamp);


                examList.add(d.getUserid());

            } while (cursor.moveToNext());

        }
        return examList;
    }
    public Cursor examquery(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        return sqLiteDatabase.query(DB_NAME,null,null,null,null,null,null);

    }

}
