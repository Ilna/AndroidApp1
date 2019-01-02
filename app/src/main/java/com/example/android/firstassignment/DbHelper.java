package com.example.android.firstassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME= "DATATABLE";
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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DB_NAME+"("+
                COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_USERID+" TEXT,"+
                COLUMN_LONGTITUDE+" REAL,"+
                COLUMN_LATITUDE+" REAL,"+
                COLUMN_TIME_STAMP+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
    }

    //+ COLUMN_TIME_STAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    //contentValues.put( COLUMN_TIME_STAMP, " time('now') " );

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public long  insert(DataTable dataTable){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();//Create an object of this class


        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_USERID,dataTable.getUserid());
        values.put(DbHelper.COLUMN_LONGTITUDE,dataTable.getLongtitude());
        values.put(DbHelper.COLUMN_LATITUDE,dataTable.getLatitude());
        values.put( DbHelper.COLUMN_TIME_STAMP, dataTable.getTimestamp());
        return sqLiteDatabase.insert(DB_NAME,null,values);

    }
    public String getRowByUserid(String userid,String timestamp){

        String table = DB_NAME;
        String[] columns = {"_ID","_LONGTITUDE","_LATITUDE"};
        String selection = "_USERID=? AND timeStamp=?";
        String[] selectionArgs = {userid,timestamp};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = "10";


        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        if(cursor.moveToFirst()){
           String id = cursor.getString(0);
           String lon = cursor.getString(1);
           String lat = cursor.getString(2);
           String result = "UserID : "+userid+"\n"+"Id : "+id+"\n"+"Longtitude : "+" "+lon+"\n"+"Latitude:"+ lat;
           return result;
            }
            return null;
        }


}
