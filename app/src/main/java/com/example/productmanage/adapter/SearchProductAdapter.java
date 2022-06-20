package com.example.productmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanage.interfaces.OnClickInterface;
import com.example.productmanage.model.Products;
import com.example.productmanage.R;

import java.util.ArrayList;
import java.util.List;

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.SearchProductHolder> {
    private Context context;
    private List<Products> productsList;
    private OnClickInterface onClickInterface;

    public void setOnClickInterface(OnClickInterface onClickInterface) {
        this.onClickInterface = onClickInterface;
    }

    public SearchProductAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public SearchProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchProductHolder(LayoutInflater.from(context).inflate(R.layout.search_product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchProductHolder holder, int position) {
        holder.imgProduct.setImageResource(productsList.get(position).getProductImg());
        holder.txtProductName.setText(productsList.get(position).getProductName());
        holder.txtProductDes.setText(productsList.get(position).getProductDes());
        holder.txtProductPrice.setText(String.valueOf(productsList.get(position).getProductPrice()) + "$");
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void filterList(ArrayList<Products> filteredList) {
        productsList = filteredList;
        notifyDataSetChanged();
    }

    public class SearchProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgProduct;
        private TextView txtProductName, txtProductDes, txtProductPrice;

        public SearchProductHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product_search);
            txtProductName = itemView.findViewById(R.id.txt_product_name_search);
            txtProductDes = itemView.findViewById(R.id.txt_product_description_search);
            txtProductPrice = itemView.findViewById(R.id.txt_price_product_search);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(onClickInterface != null){
                onClickInterface.onClick(view, getAdapterPosition(), false);
            }
        }
    }
}
