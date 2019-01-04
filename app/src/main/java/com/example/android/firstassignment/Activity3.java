package com.example.android.firstassignment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Intent intent = getIntent();

        //Get the result variable from 2Activity
        String result = intent.getStringExtra("Result");
        if(result != null){

            TextView textView = findViewById(R.id.textview);

            //Display the result on the textView
            textView.setText(result);
        }
    }
}
