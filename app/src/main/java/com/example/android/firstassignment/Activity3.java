package com.example.android.firstassignment;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        DbHelper dbHelper = new DbHelper(Activity3.this);//new DbHelper Object

        Cursor cursor = dbHelper.getReadableDatabase().query(DbHelper.DB_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Toast.makeText(this,""+cursor.getCount(),Toast.LENGTH_SHORT).show();
            }while (cursor.moveToNext());
        }
    }
}
