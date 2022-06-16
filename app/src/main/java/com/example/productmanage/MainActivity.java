package com.example.productmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.productmanage.Adapter.ViewPagerAdapter;
import com.example.productmanage.Fragments.FragmentHome;
import com.example.productmanage.Interface.SendDataProductInterface;
import com.example.productmanage.Model.Products;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements SendDataProductInterface {
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        viewPager = findViewById(R.id.view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: bottomNavigationView.getMenu().findItem(R.id.home_product).setCheckable(true);
                        break;
                    case 1: bottomNavigationView.getMenu().findItem(R.id.add_product).setCheckable(true);
                        break;
                    case 2: bottomNavigationView.getMenu().findItem(R.id.search_product).setCheckable(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_product:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.add_product:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.search_product:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void sendData(Products products) {
        @SuppressLint("ResourceType") FragmentHome fragmentHome = (FragmentHome) getSupportFragmentManager().findFragmentById(R.layout.fragment_home);
        fragmentHome.receiveDataFromFragmentAdd(products);
    }
}