package com.example.productmanage.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.productmanage.Model.Admins;
public class AdminSQlite extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "admins_db";

    private static String TABLE_ADMIN = "admin";
    private static String ID_ADMIN = "idadmin";
    private static String NAME_ADMIN = "nameadmin";
    private static String PASSWORD_ADMIN = "passwordadmin";
    private static String PHONE_ADMIN = "phoneadmin";

    private String SQLQuery = "CREATE TABLE "+ TABLE_ADMIN +" ( "+ID_ADMIN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +NAME_ADMIN+" TEXT UNIQUE, "
            +PASSWORD_ADMIN+" TEXT, "
            + PHONE_ADMIN+" TEXT) ";

    private static int VERSION = 2;

    public AdminSQlite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getAdmin(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_ADMIN , null);
        return  res;
    }

    public void addAdmin(Admins admins){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_ADMIN, admins.getAdminName());
        values.put(PASSWORD_ADMIN, admins.getAdminPassword());
        values.put(PHONE_ADMIN, admins.getAdminPhone());

        db.insert(TABLE_ADMIN, null, values);
        db.close();
    }
}
