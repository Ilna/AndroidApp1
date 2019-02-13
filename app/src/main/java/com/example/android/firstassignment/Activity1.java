package com.example.android.firstassignment;




import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
                intent.setClassName("com.example.android.firstassignment", "com.example.android.firstassignment.Activity2");
                startActivity(intent);
            }
        });

        Button button1 = findViewById(R.id.button11);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new DbHelper Object
                DbHelper dbHelper = new DbHelper(Activity1.this);

                //take the current timestamp ( https://stackoverflow.com/questions/16864128/how-to-insert-time-stamp-into-an-sqlite-database-column-using-the-function-time)
                SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy,hh:mm:ss");
                String timestamp = s.format(new Date());
                Log.d("DATEEEEEEEEEE", "DATEEEEEEE: " + timestamp);

                //Get the userid input from the user and convert it to string
                EditText editText = findViewById(R.id.editText);
                String userid = editText.getText().toString();

                //Get the longtitude input from the user and convert it to string
                //and afterwards convert it to double so you can isert it to the DB
                EditText editText2 = findViewById(R.id.editText2);
                String longtitude_s = editText2.getText().toString();
                Double longtitude = Double.parseDouble(longtitude_s);


                //Get the latitude input from the user and convert it to string
                //and afterwards convert it to double so you can isert it to the DB
                EditText editText3 = findViewById(R.id.editText3);
                String latitude_s = editText2.getText().toString();
                Double latitude = Double.parseDouble(latitude_s);

                //New pojo class object constructed with the input values
                DataTable dataTable = new DataTable(userid, longtitude, latitude, timestamp);

                //insert the object to the DB
                if (dataTable != null) {//den to exw teleiwsei auto
                    long test = dbHelper.insert(dataTable);
                    Toast.makeText(Activity1.this, "Insert Completed!", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(Activity1.this, "Insert Incomplete!", Toast.LENGTH_SHORT).show();
            }


        });

        findViewById(R.id.button_exam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                DbHelper dbHelper = new DbHelper(Activity1.this);
                List list =dbHelper.examQuery();
                String listString = list.toString();
                Toast.makeText(Activity1.this, "Insert Incomplete!", Toast.LENGTH_SHORT).show();
                //Log.d("LIST","LIST"+list);
                //intent.putExtra("result",listString);
                //intent.setAction("com.example.android.firstassignment.exam");
                intent.setClassName("com.example.android.firstassignment","com.example.android.firstassignment.ExamActivity");
                startActivityForResult(intent,7);
            }
        });


    }
}
