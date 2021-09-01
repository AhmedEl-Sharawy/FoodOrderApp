package com.example.foodapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "favourites";
    private final static int DATABASE_Version = 4;

    public final static String TABLE01_NAME = "Meal";
    public final static String TABLE01_Col01 = "_id";
    public final static String TABLE01_Col02 = "name";
    public final static String TABLE01_Col03 = "price";
    public final static String TABLE01_Col04 = "image";

    private static final String CREATE_TABLE01_STATEMENT = "CREATE TABLE " + TABLE01_NAME +
            " (" + TABLE01_Col01 + " INTEGER PRIMARY KEY AUTOINCREMENT," + TABLE01_Col02 + " TEXT," + TABLE01_Col03 + " TEXT," + TABLE01_Col04 + " TEXT NOT NULL" + " ); ";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE01_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE01_NAME);
        onCreate(sqLiteDatabase);
    }
}

