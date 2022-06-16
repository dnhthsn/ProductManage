package com.example.productmanage.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanage.Adapter.ProductAdapter;
import com.example.productmanage.Database.ProductSQLite;
import com.example.productmanage.Interface.OnClickInterface;
import com.example.productmanage.Model.Products;
import com.example.productmanage.R;
import com.example.productmanage.UpdateProduct;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements OnClickInterface {
    private RecyclerView recyclerAllProduct;
    public static List<Products> productsList;
    private ProductAdapter productAdapter;
    private Button btnRefresh;
    public static int pcurr;
    private ProductSQLite productSQLite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerAllProduct = view.findViewById(R.id.rec_product);
        btnRefresh = view.findViewById(R.id.btn_refresh);

        productSQLite = new ProductSQLite(getContext());

        productsList = new ArrayList<>();




        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        Cursor cursor = productSQLite.getProduct();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int imgProduct = cursor.getInt(1);
            String nameProduct = cursor.getString(2);
            String desProduct = cursor.getString(3);
            String priceProduct = cursor.getString(4);
            productsList.add(new Products(id, imgProduct, nameProduct, desProduct, Float.parseFloat(priceProduct)));

        }
        productAdapter = new ProductAdapter(getContext(), productsList);
        productAdapter.setOnClickInterface(this);


        recyclerAllProduct.setLayoutManager(layoutManager);
        recyclerAllProduct.setAdapter(productAdapter);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().setFragmentResultListener("dataFromAdd", (LifecycleOwner) getContext(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        int img = result.getInt("imgPro");
                        String name = result.getString("namePro");
                        String des = result.getString("desPro");
                        String price = result.getString("pricePro");
                        productsList.add(new Products(img, name, des, Float.parseFloat(price)));
                    }
                });
                productAdapter = new ProductAdapter(getContext(), productsList);
                recyclerAllProduct.setLayoutManager(layoutManager);
                recyclerAllProduct.setAdapter(productAdapter);
            }
        });


    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {
        Intent intent = new Intent(getContext(), UpdateProduct.class);
        pcurr = position;
        intent.putExtra("imgPro", productsList.get(position).getProductImg());
        intent.putExtra("namePro", productsList.get(position).getProductName());
        intent.putExtra("desPro", productsList.get(position).getProductDes());
        intent.putExtra("pricePro", String.valueOf(productsList.get(position).getProductPrice()));
        startActivity(intent);
    }

}
