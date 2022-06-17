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
import android.widget.Toast;

import com.example.productmanage.Database.AdminSQlite;
import com.example.productmanage.Model.Admins;
import com.example.productmanage.Model.Users;

public class AdminCreateAccountActivity extends AppCompatActivity {
    private EditText edtNameAdmin, edtPasswordAdmin, edtPhoneAdmin;
    private Button btnCreate;
    private AdminSQlite adminSQlite;
    private String nameDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_account);

        edtNameAdmin = findViewById(R.id.edt_name_admin);
        edtPasswordAdmin = findViewById(R.id.edt_password_admin);
        edtPhoneAdmin = findViewById(R.id.edt_phone_admin);
        btnCreate = findViewById(R.id.btn_create_admin);

        adminSQlite = new AdminSQlite(this);

        Cursor cursor = adminSQlite.getAdmin();

        while (cursor.moveToNext()){
            nameDB = cursor.getString(1);
        }

        cursor.moveToFirst();
        cursor.close();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtNameAdmin.getText().toString();
                String password = edtPasswordAdmin.getText().toString();
                String phone = edtPhoneAdmin.getText().toString();
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone)){
                    Dialog dialog = new Dialog(AdminCreateAccountActivity.this);
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
                    Toast.makeText(AdminCreateAccountActivity.this, "Username existed", Toast.LENGTH_SHORT).show();
                }else {
                    Admins admins = new Admins(name, password, phone);
                    adminSQlite.addAdmin(admins);
                    Intent intent = new Intent(AdminCreateAccountActivity.this, AdminLoginActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("password", password);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                }
            }
        });
    }
}