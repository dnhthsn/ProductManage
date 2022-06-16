package com.example.productmanage.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.productmanage.Model.Products;

public class ProductSQLite extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "products_db";

    private static String TABLE_PRODUCT = "product";
    private static String ID_PRODUCT = "idproduct";
    private static String IMAGE_PRODUCT = "imgproduct";
    private static String NAME_PRODUCT = "nameproduct";
    private static String DESCRIPTION_PRODUCT = "desproduct";
    private static String PRICE_PRODUCT = "priceproduct";

    private String SQLQuery = "CREATE TABLE "+ TABLE_PRODUCT +" ( "+ID_PRODUCT+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +IMAGE_PRODUCT+" INTEGER, "
            +NAME_PRODUCT+" TEXT, "
            +DESCRIPTION_PRODUCT+" TEXT, "
            + PRICE_PRODUCT+" TEXT) ";

    private static int VERSION = 1;

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

    public Cursor getProduct(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_PRODUCT, null);
        return  res;
    }

    public void addProduct(Products products){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IMAGE_PRODUCT, products.getProductImg());
        values.put(NAME_PRODUCT, products.getProductName());
        values.put(DESCRIPTION_PRODUCT, products.getProductDes());
        values.put(PRICE_PRODUCT, products.getProductPrice());

        db.insert(TABLE_PRODUCT, null, values);
        db.close();
    }
}
