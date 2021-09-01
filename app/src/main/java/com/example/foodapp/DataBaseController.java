package com.example.foodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DataBaseController {
    DataBaseHelper dataBaseHelper;
    private SQLiteDatabase dataBase;

    public DataBaseController(Context context) {

        dataBaseHelper = new DataBaseHelper(context);
    }

    public void open(){
        dataBase = dataBaseHelper.getWritableDatabase();
    }

    public Long insertMeal(String name , String price, String image){
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.TABLE01_Col02, name);
        values.put(DataBaseHelper.TABLE01_Col03, price);
        values.put(DataBaseHelper.TABLE01_Col04, image);
        return dataBase.insert(DataBaseHelper.TABLE01_NAME,null, values);// nullColumnHack = Column that allow null
    }

    public int updateMeal ( int id, String name){
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.TABLE01_Col02,name);
        return dataBase.update(DataBaseHelper.TABLE01_NAME, values , DataBaseHelper.TABLE01_Col01+ "=" + id, null);
    }



    public int deleteMeal (String name){
        return dataBase.delete(DataBaseHelper.TABLE01_NAME, DataBaseHelper.TABLE01_Col02+ "= + '" + name + "';" , null);
    }

    public boolean chkDB(){
        boolean chk = false;
        Cursor mCursor = dataBase.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE01_NAME, null);
        if (mCursor != null){
            mCursor.moveToFirst();
            if(mCursor.getInt(0) == 0){
                chk = false;
            }
        }
        else {
            chk =true;
        }
        return chk;
    }

    public Meal selectMeal (String name){
        Cursor cursor = dataBase.query(DataBaseHelper.TABLE01_NAME, new String[]{DataBaseHelper.TABLE01_Col01, DataBaseHelper.TABLE01_Col02, DataBaseHelper.TABLE01_Col03, DataBaseHelper.TABLE01_Col04}, DataBaseHelper.TABLE01_Col02+ "= + '" + name + "';",null,null,null,null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursorToMeal(cursor);
        }
        else {
            return null;
        }
    }


    public ArrayList<Meal> selectMeals (){
        ArrayList <Meal> Meals = new ArrayList<>();
        Cursor cursor = dataBase.query(DataBaseHelper.TABLE01_NAME, new String[]{DataBaseHelper.TABLE01_Col01,DataBaseHelper.TABLE01_Col02,  DataBaseHelper.TABLE01_Col03, DataBaseHelper.TABLE01_Col04}, null,null,null,null,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Meals.add(cursorToMeal(cursor));
            cursor.moveToNext();
        }
        return Meals;
    }


    private Meal cursorToMeal(Cursor cursor) {
        return new Meal(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3));
    }


    public void close(){
        if (dataBaseHelper != null) dataBaseHelper.close();
    }
}
