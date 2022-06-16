package com.example.productmanage.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.productmanage.R;

public class ProductSpinnerAdapter extends BaseAdapter {
    private int imgs[] = {R.color.teal_200,
            R.color.teal_700,
            R.color.purple_200,
            R.color.purple_500,
            R.color.purple_700};

    private Context context;

    public ProductSpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.product_item_image, viewGroup, false);
        ImageView imageView = view1.findViewById(R.id.img_item_product);
        imageView.setImageResource(imgs[i]);
        return view1;
    }
}
