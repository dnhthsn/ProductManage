package com.example.productmanage.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.productmanage.database.ProductSQLite;
import com.example.productmanage.model.Products;

public class ProductRepository {
    private Context context;
    private ProductSQLite productSQLite;

    public ProductRepository(Context context) {
        this.context = context;
        this.productSQLite = new ProductSQLite(context);
    }

    public Cursor getProduct(){
        SQLiteDatabase db = productSQLite.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + ProductSQLite.TABLE_PRODUCT, null);
        return  res;
    }

    public void addProduct(Products products){
        SQLiteDatabase db = productSQLite.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductSQLite.IMAGE_PRODUCT, products.getProductImg());
        values.put(ProductSQLite.NAME_PRODUCT, products.getProductName());
        values.put(ProductSQLite.DESCRIPTION_PRODUCT, products.getProductDes());
        values.put(ProductSQLite.PRICE_PRODUCT, products.getProductPrice());

        db.insert(ProductSQLite.TABLE_PRODUCT, null, values);
        db.close();
    }

    public void updateProduct(Products products, int i){
        SQLiteDatabase db = productSQLite.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductSQLite.TABLE_PRODUCT, products.getProductImg());
        values.put(ProductSQLite.NAME_PRODUCT, products.getProductName());
        values.put(ProductSQLite.DESCRIPTION_PRODUCT, products.getProductDes());
        values.put(ProductSQLite.PRICE_PRODUCT, products.getProductPrice());

        db.update(ProductSQLite.TABLE_PRODUCT, values, ProductSQLite.ID_PRODUCT +" = " + i, null);
        db.close();
    }


    public int deleteProduct(int i){
        SQLiteDatabase db = productSQLite.getReadableDatabase();
        int res = db.delete(ProductSQLite.TABLE_PRODUCT, ProductSQLite.ID_PRODUCT+" = " + i, null);
        return res;
    }

    public Cursor searchProduct(){
        SQLiteDatabase db = productSQLite.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + ProductSQLite.TABLE_PRODUCT , null);
        return  res;
    }
}
