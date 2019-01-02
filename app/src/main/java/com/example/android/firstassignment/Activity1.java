package com.example.android.firstassignment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;
import java.util.Locale;


public class Activity1 extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        DbHelper dbHelper = new DbHelper(Activity1.this);
        SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();



        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy,hh:mm:ss");
        String format = s.format(new Date());
        Log.d("DATEEEEEEEEEE", "DATEEEEEEE: " + format);


        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_USERID,"it214166");
        values.put(DbHelper.COLUMN_LONGTITUDE,Math.random()*100);
        values.put(DbHelper.COLUMN_LATITUDE, Math.random()*100);
        values.put( DbHelper.COLUMN_TIME_STAMP, format);
        sqLiteDatabase.insert(DbHelper.DB_NAME,null,values);

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.android.firstassignment","com.example.android.firstassignment.Activity2");
                startActivity(intent);
            }
        });
    }
}
