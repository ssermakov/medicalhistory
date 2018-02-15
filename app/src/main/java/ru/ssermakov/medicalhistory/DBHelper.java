package ru.ssermakov.medicalhistory;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "medicalhistory.db";
    public static final int DB_VERSION = 1;
    private Context cnt;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        cnt = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        PatientsTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        PatientsTable.onUpgrade(sqLiteDatabase);
    }
}
