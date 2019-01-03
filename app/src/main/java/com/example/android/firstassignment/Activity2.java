package com.example.android.firstassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        final DbHelper dbHelper = new DbHelper(Activity2.this);//new DbHelper Object

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        List columnoftimestamps = dbHelper.getAllLabels();
        Log.d("TIMESTAMPS","TIMESTAMPS "+columnoftimestamps);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(Activity2.this,android.R.layout.simple_spinner_item, columnoftimestamps);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                final Object item = parent.getItemAtPosition(pos);
                final String timestamp = item.toString();
                Log.d("SHMANTIKOOOOOO","SHMANTIKO"+item);

                findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editText =findViewById(R.id.editText);
                        String userid = editText.getText().toString();

                        //EditText editText1 = findViewById(R.id.editText2);
                        //String timestamp = editText1.getText().toString();

                        Log.d("INPUT","INPUT "+userid);
                        Log.d("INPUT2","INPUT2 "+timestamp);

                        String result = dbHelper.getRowByUserid(userid,timestamp);

                        Intent intent = new Intent();
                        intent.setClassName("com.example.android.firstassignment","com.example.android.firstassignment.Activity3");
                        intent.putExtra("Result",result);
                        startActivity(intent);


                    }
                });

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //String text = spinner.getSelectedItem().toString();




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
