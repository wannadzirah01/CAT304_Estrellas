package com.example.cat304project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "Login.db";

    public DBHelper(Context context) {
        super(context, "LogIn.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(studentEmail TEXT primary key, studentName TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String studentEmail, String studentName, String password){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("studentEmail", studentEmail);
        contentValues.put("studentName", studentName);
        contentValues.put("password", password);
        long result = MyDb.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkStudentEmail(String studentEmail){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where studentEmail = ?", new String[] {studentEmail});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkStudentEmailPassword(String studentEmail, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where studentEmail = ? and password = ? ", new String[] {studentEmail, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
