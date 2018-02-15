package ru.ssermakov.medicalhistory;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by btb_wild on 14.02.2018.
 */

class PatientsTable {


    public static final String TABLE_PATIENTS = "patients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ILLNESS_ID = "illness_id";
    public static final String COLUMN_CURRENT_STATE = "current_state";
    public static final String COLUMN_IMAGE_URL = "image_url";

    public static final String PATIENTS_CREATE = "CREATE TABLE IF NOT EXISTS patients (_id integer primary key autoincrement, name, illness_id, current_state, image_url)";


    public static void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PATIENTS_CREATE);
        populate(sqLiteDatabase);
    }

    private static void populate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("   INSERT INTO patients (name, illness_id, current_state, image_url) VALUES ('Катя', '0', '1', 'no image')   "   );
            sqLiteDatabase.execSQL("   INSERT INTO patients (name, illness_id, current_state, image_url) VALUES ('Катя', '0', '1', 'no image')   "   );
            sqLiteDatabase.execSQL("   INSERT INTO patients (name, illness_id, current_state, image_url) VALUES ('Катя', '0', '1', 'no image')   "   );
            sqLiteDatabase.execSQL("   INSERT INTO patients (name, illness_id, current_state, image_url) VALUES ('Катя', '0', '1', 'no image')   "   );
    }


    public static void onUpgrade(SQLiteDatabase sqLiteDatabase) {
    }
}
