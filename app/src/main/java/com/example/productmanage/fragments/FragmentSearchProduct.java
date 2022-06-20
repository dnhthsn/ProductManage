package com.example.productmanage.fragments;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.productmanage.adapter.SearchProductAdapter;
import com.example.productmanage.database.ProductSQLite;
import com.example.productmanage.DetailActivity;
import com.example.productmanage.interfaces.OnClickInterface;
import com.example.productmanage.model.Products;
import com.example.productmanage.R;
import java.util.ArrayList;
import java.util.List;

public class FragmentSearchProduct extends Fragment implements OnClickInterface {
    private RecyclerView recSearch;
    private SearchProductAdapter searchProductAdapter;
    private List<Products> productsList;
    private List<Products> list;
    private SearchView searchView;
    private ProductSQLite productSQLite;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.searchView);
        recSearch = view.findViewById(R.id.rec_search);

        productsList = new ArrayList<>();

        list = new ArrayList<>();

        productSQLite = new ProductSQLite(getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        Cursor cursor = productSQLite.searchProduct();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int imgProduct = cursor.getInt(1);
            String nameProduct = cursor.getString(2);
            String desProduct = cursor.getString(3);
            String priceProduct = cursor.getString(4);
            productsList.add(new Products(id, imgProduct, nameProduct, desProduct, Float.parseFloat(priceProduct)));
        }
        cursor.moveToFirst();
        cursor.close();



        searchProductAdapter = new SearchProductAdapter(getContext(), productsList);
        searchProductAdapter.setOnClickInterface(this);


        recSearch.setLayoutManager(layoutManager);
        recSearch.setAdapter(searchProductAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return true;
            }
        });

    }

    private void filter(String text){
        ArrayList<Products> filteredList = new ArrayList<>();

        for(Products item:productsList){
            if(item.getProductName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
                list.add(item);
            }
        }

        searchProductAdapter.filterList(filteredList);
    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        int idPro = productsList.get(position).getId();
        int imgPro = productsList.get(position).getProductImg();
        String namePro = productsList.get(position).getProductName();
        String desPro = productsList.get(position).getProductDes();
        float pricePro = productsList.get(position).getProductPrice();
        intent.putExtra("idPro", idPro);
        intent.putExtra("imgPro", imgPro);
        intent.putExtra("namePro", namePro);
        intent.putExtra("desPro", desPro);
        intent.putExtra("pricePro", pricePro);
        startActivity(intent);
    }
}
