package com.example.productmanage.Model;

import java.io.Serializable;

public class Products implements Serializable {
    private int id;
    private int productImg;
    private String productName, productDes;
    private float productPrice;

    public Products() {
    }

    public Products(int productImg, String productName, String productDes, float productPrice) {
        this.productImg = productImg;
        this.productName = productName;
        this.productDes = productDes;
        this.productPrice = productPrice;
    }

    public Products(int id, int productImg, String productName, String productDes, float productPrice) {
        this.id = id;
        this.productImg = productImg;
        this.productName = productName;
        this.productDes = productDes;
        this.productPrice = productPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductImg() {
        return productImg;
    }

    public void setProductImg(int productImg) {
        this.productImg = productImg;
    }
}
