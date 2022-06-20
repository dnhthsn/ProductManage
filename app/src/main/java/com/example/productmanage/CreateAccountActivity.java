package com.example.productmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.productmanage.database.UserSQLite;
import com.example.productmanage.model.Users;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText edtNameCr, edtPasswordCr, edtPhoneCr;
    private Button btnCreateAccount;
    private UserSQLite userSQLite;
    private String nameDB;
    private TextView txtCreateAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        edtNameCr = findViewById(R.id.edt_name);
        edtPasswordCr = findViewById(R.id.edt_password);
        edtPhoneCr = findViewById(R.id.edt_phone);
        btnCreateAccount = findViewById(R.id.btn_create);
        txtCreateAdmin = findViewById(R.id.txt_create_admin);

        userSQLite = new UserSQLite(this);
        Cursor cursor = userSQLite.getUser();
        while (cursor.moveToNext()){
            nameDB = cursor.getString(1);
        }

        cursor.moveToFirst();
        cursor.close();

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtNameCr.getText().toString();
                String password = edtPasswordCr.getText().toString();
                String phone = edtPhoneCr.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone)){
                    Dialog dialog = new Dialog(CreateAccountActivity.this);
                    dialog.setContentView(R.layout.dialog_fill_all_editext);

                    Button btnOk = dialog.findViewById(R.id.btn_ok);
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }else if (nameDB.equals(name)){
                    Toast.makeText(CreateAccountActivity.this, "Username existed", Toast.LENGTH_SHORT).show();
                }else {
                    Users users = new Users(name, password, phone);
                    userSQLite.addUser(users);
                    Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("password", password);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                }

            }
        });

        txtCreateAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this, AdminCreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}