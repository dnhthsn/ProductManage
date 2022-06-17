package com.example.productmanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.productmanage.Adapter.UserAdapter;
import com.example.productmanage.Database.UserSQLite;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_manage);
        recUsers = findViewById(R.id.rec_users);

        usersList = new ArrayList<>();
        userSQLite = new UserSQLite(this);
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



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        userAdapter = new UserAdapter(this, usersList);

        recUsers.setLayoutManager(layoutManager);
        recUsers.setAdapter(userAdapter);
    }
}