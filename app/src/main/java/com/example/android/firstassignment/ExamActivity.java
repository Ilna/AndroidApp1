package com.example.android.firstassignment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        DbHelper dbHelper = new DbHelper(ExamActivity.this);
        ListView listView = findViewById(R.id.listview);
        ArrayList<String> resultList = new ArrayList<>();
        Intent intent = getIntent();
        Cursor cursor = dbHelper.examquery();
        while (cursor.moveToNext()){
            String LISTA = cursor.getString(0)+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getString(4)+"\n";
            resultList.add(LISTA);

            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,resultList);
            listView.setAdapter(listAdapter);
        }

    }
}
