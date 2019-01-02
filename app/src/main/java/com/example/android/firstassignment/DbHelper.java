package com.example.android.firstassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME= "DATATABLE";
    public static final int DB_VERSION = 1;
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_USERID = "_USERID";
    public static final String COLUMN_LONGTITUDE = "_LONGTITUDE";
    public static final String COLUMN_LATITUDE = "_LATITUDE";
    public static final String COLUMN_TIME_STAMP = "timeStamp";
    public static final String ilia = "ilia";


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
}
