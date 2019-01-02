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
                Log.d("INPUT","INPUT "+userid);
                String result = dbHelper.getRowByUserid(userid);
                Log.d("RESULT","RESULT    "+result);
                if(result != null){
                   TextView textView = findViewById(R.id.textview);
                   textView.setText(result.toString());
                    Toast.makeText(Activity2.this,result.toString(),Toast.LENGTH_SHORT).show();
                }
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
