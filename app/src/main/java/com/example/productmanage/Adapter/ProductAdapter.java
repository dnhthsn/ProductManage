package com.example.productmanage.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanage.Database.ProductSQLite;
import com.example.productmanage.Interface.OnClickInterface;
import com.example.productmanage.Model.Products;
import com.example.productmanage.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    List<Products> productsList;
    private OnClickInterface onClickInterface;
    private ProductSQLite productSQLite;

    public void setOnClickInterface(OnClickInterface onClickInterface) {
        this.onClickInterface = onClickInterface;
    }

    public ProductAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.product_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(position != 0)
        holder.imgProduct.setImageResource(productsList.get(position).getProductImg());
        holder.txtProductName.setText(productsList.get(position).getProductName());
        holder.txtProductDes.setText(productsList.get(position).getProductDes());
        holder.txtProductPrice.setText(String.valueOf(productsList.get(position).getProductPrice()));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("You want to delete " +"'"+ productsList.get(position).getProductName() +"'" + " ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        productSQLite = new ProductSQLite(context);
                        productSQLite.deleteProduct(productsList.get(position).getId());
                        productsList.remove(position);


                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void addProduct(Products products){
        productsList.add(products);
        notifyDataSetChanged();
    }

    public Products getProductItem(int position){
        return productsList.get(position);
    }

    public void updateProduct(int pcurr, Products products){
        productsList.set(pcurr, products);
        notifyDataSetChanged();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgProduct;
        private TextView txtProductName, txtProductDes, txtProductPrice;
        private Button btnDelete;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            txtProductName = itemView.findViewById(R.id.txt_product_name);
            txtProductDes = itemView.findViewById(R.id.txt_product_description);
            txtProductPrice = itemView.findViewById(R.id.txt_price);
            btnDelete = itemView.findViewById(R.id.btn_delete);
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
