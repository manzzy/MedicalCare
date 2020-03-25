package com.example.medicalcare.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Medicare.db";
    public static final String TABLE_NAME= "disease";
    public static final String symptom= "symptom";
    public static final String diseaseName= "name";
    public static final String description= "discription";
    public static final String shortDescription= "short_description";

    public static final String medicine= "medicine";
    public static final String hour= "hour";
    public static final String min= "minute";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDiseaseTable = "CREATE TABLE " + TABLE_NAME +
                " ( "
                    + symptom + " varchar(50) ,"
                    + diseaseName + " varchar(50) ,"
                    + description + " varchar(10000) ,"
                    + shortDescription + " varchar(200) "
                    + " ) ";
        String createAlarmTable = "CREATE TABLE alarm "+
                " ( "
                    + medicine + " varchar(50) ,"
                    + hour + " int ,"
                    + min + " int "
                    + " ) ";
        db.execSQL(createDiseaseTable);
        db.execSQL(createAlarmTable);

    }


    //called when the database needs to upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if( oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(db);
        }
    }

    public boolean insert(String symptom, String disease, String description, String shortDescription){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("symptom",symptom);
        contentValues.put("name",disease);
        contentValues.put("discription",description);
        contentValues.put("short_description",shortDescription);



        long result= db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean insertalarm(String name, int hr, int mn){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("medicine",name);
        contentValues.put("hour",hr);
        contentValues.put("minute",mn);




        long result= db.insert("alarm",null,contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getAlarm(){
        SQLiteDatabase db= getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+"alarm",null);
        return res;
    }

    public Cursor getAllData() {
        SQLiteDatabase db= getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }
    public Cursor getDataForRecycler(){
        SQLiteDatabase db = getWritableDatabase();
        String filter =   diseaseName + " , "  + shortDescription ;

        Cursor res = db.rawQuery("SELECT "+ filter + " From "+ TABLE_NAME,null);

        return res;
    }
    public Cursor getDataForPopUp(String disease){
        SQLiteDatabase db = getWritableDatabase();
        String filter =   diseaseName + " , "  + description ;
        String condition = " WHERE name  =  '" + disease + "' ";

        Cursor res = db.rawQuery("SELECT "+ filter + " From "+ TABLE_NAME + condition,null);

        return res;
    }

    public Cursor getDataForDiagnosed(String msymptom){
        SQLiteDatabase db = getWritableDatabase();
        String filter =   diseaseName + " , "  + description ;
        String condition = " WHERE symptom  =  '" + msymptom + "' ";

        Cursor res = db.rawQuery("SELECT "+ filter + " From "+ TABLE_NAME + condition,null);

        return res;
    }




}
