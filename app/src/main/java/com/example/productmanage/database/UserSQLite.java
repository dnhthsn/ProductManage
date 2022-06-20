package com.example.productmanage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.productmanage.model.Users;

public class UserSQLite extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "users_db";

    private static String TABLE_USER = "user";
    private static String ID_USER = "iduser";
    private static String NAME_USER = "nameuser";
    private static String PASSWORD_USER = "passworduser";
    private static String PHONE_USER = "phoneuser";

    private String SQLQuery = "CREATE TABLE "+ TABLE_USER +" ( "+ID_USER+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +NAME_USER+" TEXT UNIQUE, "
            +PASSWORD_USER+" TEXT, "
            + PHONE_USER+" TEXT) ";

    private static int VERSION = 1;

    public UserSQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_USER , null);
        return  res;
    }

    public void addUser(Users users){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_USER, users.getUserName());
        values.put(PASSWORD_USER, users.getPasswordUser());
        values.put(PHONE_USER, users.getPhoneUser());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public int deleteUser(int i){
        SQLiteDatabase db = this.getReadableDatabase();
        int res = db.delete(TABLE_USER, ID_USER+ " LIKE " + i, null);
        return res;
    }
}
