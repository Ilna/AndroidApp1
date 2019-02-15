package com.example.android.firstassignment;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    private DbHelper dbHelper;
    private UriMatcher uriMatcher;
    static final String PROVIDER_NAME = "com.example.android.firstassignment.MyContentProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/datatable";
    static final Uri CONTENT_URI = Uri.parse(URL);

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override//Insert to the DB
    public Uri insert(Uri uri, ContentValues values) { //http://android-delight.blogspot.com/2016/07/how-to-sharing-data-between-apps-using.html

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Uri _uri = null;
        long rowID = sqLiteDatabase.insert(dbHelper.DB_NAME, null, values);
        if (rowID > 0) {
            _uri = ContentUris.withAppendedId(Uri.parse("content://com.example.android.firstassignment/datatable"),rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLiteException("Failed to add a record into " + uri);
    }
    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.android.firstassignment", "/datatable", 1);
        uriMatcher.addURI("com.example.android.firstassignment", "/datatable/userid", 2);
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case 1:
                cursor = dbHelper.getReadableDatabase().query(DbHelper.DB_NAME, null, null, null, null, null, null);
                break;
            case 2:
                cursor = dbHelper.getReadableDatabase().query(DbHelper.DB_NAME, null, selection, selectionArgs, null, null, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return  cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

