package com.example.contacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_CONTACT =
            "create table contact(" +
                    "_id integer primary key autoincrement," +
                    "contactname text not null," +
                    "streetaddress text," +
                    "city text," +
                    "state text," +
                    "zipcode text," +
                    "phonenumber text," +
                    "cellnumber text," +
                    "email text," +
                    "birthday text);";

    public ContactDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
