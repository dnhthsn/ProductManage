package com.example.productmanage.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanage.Adapter.ProductAdapter;
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

        productsList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        productAdapter = new ProductAdapter(getContext(), productsList);
        productAdapter.setOnClickInterface(this);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    public void receiveDataFromFragmentAdd(Products products){
        productsList = new ArrayList<>();
        productsList.add(products);
    }
}
