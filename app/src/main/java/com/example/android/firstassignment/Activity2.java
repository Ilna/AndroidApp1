package com.example.android.firstassignment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        final DbHelper dbHelper = new DbHelper(Activity2.this);//new DbHelper Object

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText =findViewById(R.id.editText);
                String userid = editText.getText().toString();

                EditText editText1 = findViewById(R.id.editText2);
                String timestamp = editText1.getText().toString();

                Log.d("INPUT","INPUT "+userid);
                Log.d("INPUT2","INPUT2 "+timestamp);

                String result = dbHelper.getRowByUserid(userid,timestamp);

                Intent intent = new Intent();
                intent.setClassName("com.example.android.firstassignment","com.example.android.firstassignment.Activity3");
                intent.putExtra("Result",result);
                startActivity(intent);


            }
        });

//        Button button = findViewById(R.id.button2);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClassName("com.example.android.firstassignment","com.example.android.firstassignment.Activity3");
//                startActivity(intent);
//            }
//        });
    }
}
