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
import android.widget.Toast;

import java.util.List;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //new DbHelper Object
        final DbHelper dbHelper = new DbHelper(Activity2.this);//new DbHelper Object

        //Find Timestamps button
        findViewById(R.id.find).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get the userid input from the user and convert it to string
                EditText editText = findViewById(R.id.editText);
                String userid = editText.getText().toString();

                //put inside the spinner the timestamplist
                //(https://inducesmile.com/android/populating-android-spinner-from-sqlite-database/)
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                List columnoftimestamps = dbHelper.getAllLabels(userid);

                Log.d("TIMESTAMPS", "TIMESTAMPS " + columnoftimestamps);

                //Creating adapter for spinner
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(Activity2.this, android.R.layout.simple_spinner_item, columnoftimestamps);

                //Drop down layout list view
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //attaching data adapter to spinner
                spinner.setAdapter(spinnerAdapter);

                //Get the spinner current value (https://stackoverflow.com/questions/1947933/how-to-get-spinner-value)
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                        //item has the current value
                        final Object item = parent.getItemAtPosition(pos);
                        final String timestamp = item.toString();
                        Log.d("SHMANTIKOOOOOO", "SHMANTIKO" + item);

                        //Make the query and go to the 3Activity for the results button
                        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //Get the userid input from the user and convert it to string
                                EditText editText = findViewById(R.id.editText);
                                String userid = editText.getText().toString();

                                Log.d("INPUT", "INPUT " + userid);
                                Log.d("INPUT2", "INPUT2 " + timestamp);

                                //get the query result from the DB and put it inside "result" variable
                                String result = dbHelper.getRowByUseridTimestamp(userid, timestamp);

                                Intent intent = new Intent();
                                intent.setClassName("com.example.android.firstassignment", "com.example.android.firstassignment.Activity3");

                                //sent result to the 3Activity
                                intent.putExtra("Result", result);
                                startActivity(intent);


                            }
                        });

                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(Activity2.this, "Nothing is selected from the DB", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}