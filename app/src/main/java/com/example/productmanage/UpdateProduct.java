package com.example.productmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.productmanage.adapter.ProductSpinnerAdapter;
import com.example.productmanage.database.ProductSQLite;
import com.example.productmanage.model.Products;
import com.example.productmanage.repository.ProductRepository;

public class UpdateProduct extends AppCompatActivity {
    private Spinner spnImgUpdate;
    private EditText edtNamePro, edtDesPro, edtPricePro;
    private Button btnUpdate;
    private int imgs[] = {R.color.teal_200,
            R.color.teal_700,
            R.color.purple_200,
            R.color.purple_500,
            R.color.purple_700};

    private ProductRepository productRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        spnImgUpdate = findViewById(R.id.spn_img_product_update);
        edtNamePro = findViewById(R.id.edt_product_name_update);
        edtDesPro = findViewById(R.id.edt_product_description_update);
        edtPricePro = findViewById(R.id.edt_product_price_update);
        btnUpdate = findViewById(R.id.btn_update_product);

        productRepository = new ProductRepository(getApplicationContext());

        ProductSpinnerAdapter productSpinnerAdapter = new ProductSpinnerAdapter(this);
        spnImgUpdate.setAdapter(productSpinnerAdapter);

        Intent intent = getIntent();



        edtNamePro.setText(intent.getStringExtra("namePro"));
        edtDesPro.setText(intent.getStringExtra("desPro"));
        edtPricePro.setText(intent.getStringExtra("pricePro"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(UpdateProduct.this, MainActivity.class);
                String namePro = edtNamePro.getText().toString();
                String desPro = edtDesPro.getText().toString();
                String pricePro = edtPricePro.getText().toString();
                Products products = new Products();
                String itemImg = spnImgUpdate.getSelectedItem().toString();
                int imgProduct = R.color.teal_200;
                try {
                    imgProduct = imgs[Integer.parseInt(itemImg)];
                }catch (NumberFormatException e){
                }
                products.setProductImg(imgProduct);
                products.setProductName(namePro);
                products.setProductDes(desPro);
                products.setProductPrice(Float.parseFloat(pricePro));

                productRepository.updateProduct(products, Integer.parseInt(intent.getStringExtra("idPro")));
                startActivity(intent1);
            }
        });
    }


}