package com.example.productmanage.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.productmanage.fragments.FragmentAddProduct;
import com.example.productmanage.fragments.FragmentHome;
import com.example.productmanage.fragments.FragmentSearchProduct;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int pageNum;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNum = 3;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentAddProduct();
            case 2:
                return new FragmentSearchProduct();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}
