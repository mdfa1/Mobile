package com.example.contacts;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ContactDataSource {
    SQLiteDatabase database;
    ContactDBHelper dbHelper;

    public ContactDataSource(Context context){
        dbHelper = new ContactDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }
}
