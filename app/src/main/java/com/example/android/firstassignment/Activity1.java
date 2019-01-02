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
import android.widget.EditText;

import java.util.Date;



public class Activity1 extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.android.firstassignment","com.example.android.firstassignment.Activity2");
                startActivity(intent);
            }
        });

        Button button1 = findViewById(R.id.button11);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(Activity1.this);//new DbHelper Object

                //take the current timestamp
                SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy,hh:mm:ss");
                String format = s.format(new Date());
                Log.d("DATEEEEEEEEEE", "DATEEEEEEE: " + format);

                EditText editText =findViewById(R.id.editText);
                String userid = editText.getText().toString();

                EditText editText2 =findViewById(R.id.editText2);
                String longtitude_s = editText2.getText().toString();
                Double longtitude = Double.parseDouble(longtitude_s);

                EditText editText3 =findViewById(R.id.editText3);
                String latitude_s = editText2.getText().toString();
                Double latitude = Double.parseDouble(latitude_s);

                DataTable dataTable = new DataTable(userid,longtitude,latitude,format);
                dbHelper.insert(dataTable);
            }
        });
    }
}
