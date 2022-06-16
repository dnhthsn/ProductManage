package com.example.productmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private ImageView imgProduct;
    private TextView txtNameProduct, txtDesProduct, txtPriceProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgProduct = findViewById(R.id.img_detail_product);
        txtNameProduct = findViewById(R.id.txt_name_product_detail);
        txtDesProduct = findViewById(R.id.txt_description_product_detail);
        txtPriceProduct = findViewById(R.id.txt_price_product_detail);

        Intent intent = getIntent();
        imgProduct.setImageResource(intent.getIntExtra("imgPro", R.color.teal_200));
        txtNameProduct.setText(intent.getStringExtra("namePro"));
        txtDesProduct.setText(intent.getStringExtra("desPro"));
        txtPriceProduct.setText(String.valueOf(intent.getFloatExtra("pricePro", 0)) + "$");
    }
}