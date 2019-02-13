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

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public Uri insert(Uri uri, ContentValues values) { //http://android-delight.blogspot.com/2016/07/how-to-sharing-data-between-apps-using.html

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri _uri = null;
                long rowID = db.insert(dbHelper.DB_NAME, null, values);
                System.out.println(rowID+"");
                if (rowID > 0) {
                    _uri = ContentUris.withAppendedId(Uri.parse("content://com.example.android.firstassignment/datatable"),rowID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                    System.out.println(_uri+"");
                    return _uri;
                }

                throw new SQLiteException("Failed to add a record into " + uri);


    }

    @Override
    public boolean onCreate() {

        dbHelper = new DbHelper(getContext());
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.android.firstassignment", "/datatable", 1);
        uriMatcher.addURI("com.example.android.firstassignment", "/datatable/#", 2);
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


    public MyContentProvider
            () {
    }

       @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

