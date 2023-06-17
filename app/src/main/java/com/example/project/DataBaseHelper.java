package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project.models.User;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create User table
        sqLiteDatabase.execSQL("CREATE TABLE USER(EMAIL TEXT PRIMARY KEY, FIRST_NAME TEXT, LAST_NAME TEXT," +
                " PASSWORD TEXT\n )");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertUser(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("FIRST_NAME", user.getFirstName());
        contentValues.put("LAST_NAME", user.getLastName());
        contentValues.put("PASSWORD", user.getPassword());
        sqLiteDatabase.insert("USER", null, contentValues);
    }



    public Boolean checkusernamepassword(String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from USER where EMAIL = ? and PASSWORD = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                System.out.println(cursor.getString(1));
            }

            return true;
        } else
            return false;
    }


    public Cursor getUserData(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        System.out.println(email);
        return sqLiteDatabase.rawQuery("Select * from USER where EMAIL = ? ", new String[] {email});

    }
    public void updateUserInfo(String email, String firstName, String secondName, String password){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("FIRST_NAME", firstName);
        values.put("LAST_NAME", secondName);
        values.put("PASSWORD", password);
        db.update("USER", values, "EMAIL = ?", new String[]{ email});
        db.close();
    }

    public Cursor getAllUsers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USER", null);
    }



    public Boolean checkIFUserExists(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from USER where EMAIL = ? ", new String[] {email});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }


}