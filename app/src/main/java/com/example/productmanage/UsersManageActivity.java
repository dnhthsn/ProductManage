package com.example.productmanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.productmanage.Adapter.UserAdapter;
import com.example.productmanage.Database.UserSQLite;
import com.example.productmanage.Model.Products;
import com.example.productmanage.Model.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersManageActivity extends AppCompatActivity {
    private RecyclerView recUsers;
    private UserAdapter userAdapter;
    private List<Users> usersList;
    private UserSQLite userSQLite;
    private int idDB;
    private String nameDB, passwordDB, phoneDB;
    private List<Users> list;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_manage);
        recUsers = findViewById(R.id.rec_users);
        searchView = findViewById(R.id.search_user);

        usersList = new ArrayList<>();
        userSQLite = new UserSQLite(this);
        list = new ArrayList<>();
        Cursor cursor = userSQLite.getUser();
        while (cursor.moveToNext()){
            idDB = cursor.getInt(0);
            nameDB = cursor.getString(1);
            passwordDB = cursor.getString(2);
            phoneDB = cursor.getString(3);

            Users users = new Users(idDB, nameDB, passwordDB, phoneDB);
            usersList.add(users);
        }

        cursor.moveToFirst();
        cursor.close();

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



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        userAdapter = new UserAdapter(this, usersList);

        recUsers.setLayoutManager(layoutManager);
        recUsers.setAdapter(userAdapter);
    }

    private void filter(String text){
        ArrayList<Users> filteredList = new ArrayList<>();

        for(Users item:usersList){
            if(item.getUserName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
                list.add(item);
            }
        }

        userAdapter.filterList(filteredList);
    }
}