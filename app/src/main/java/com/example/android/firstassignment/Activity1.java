package com.example.android.firstassignment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;



public class Activity1 extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        DbHelper dbHelper = new DbHelper(Activity1.this);//new DbHelper Object

        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy,hh:mm:ss");
        String format = s.format(new Date());
        Log.d("DATEEEEEEEEEE", "DATEEEEEEE: " + format);

        DataTable dataTable = new DataTable("it214166",134.33,172.33,format);
        dbHelper.insert(dataTable);



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
