package com.example.productmanage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.productmanage.model.Products;

public class ProductSQLite extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "products_db";

    public static String TABLE_PRODUCT = "product";
    public static String ID_PRODUCT = "idproduct";
    public static String IMAGE_PRODUCT = "imgproduct";
    public static String NAME_PRODUCT = "nameproduct";
    public static String DESCRIPTION_PRODUCT = "desproduct";
    public static String PRICE_PRODUCT = "priceproduct";




    private String SQLQuery = "CREATE TABLE "+ TABLE_PRODUCT +" ( "+ID_PRODUCT+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +IMAGE_PRODUCT+" INTEGER, "
            +NAME_PRODUCT+" TEXT, "
            +DESCRIPTION_PRODUCT+" TEXT, "
            + PRICE_PRODUCT+" TEXT) ";



    private static int VERSION = 3;

    public ProductSQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
