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

import com.example.productmanage.database.AdminSQlite;

public class AdminLoginActivity extends AppCompatActivity {
    private EditText edtNameLog, edtPassLog;
    private Button btnLogin;
    private AdminSQlite adminSQlite;
    private String nameDB, passwordDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        edtNameLog = findViewById(R.id.edt_name_log_admin);
        edtPassLog = findViewById(R.id.edt_password_log_admin);
        btnLogin = findViewById(R.id.btn_signin_admin);

        Intent intent = getIntent();
        edtNameLog.setText(intent.getStringExtra("name"));

        adminSQlite = new AdminSQlite(this);
        Cursor cursor = adminSQlite.getAdmin();
        while (cursor.moveToNext()){
            nameDB = cursor.getString(1);
            passwordDB = cursor.getString(2);
        }

        cursor.moveToFirst();
        cursor.close();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameDB.equals(edtNameLog.getText().toString()) && passwordDB.equals(edtPassLog.getText().toString())){
                    Intent intent1 = new Intent(AdminLoginActivity.this, UsersManageActivity.class);
                    startActivity(intent1);
                }else if(TextUtils.isEmpty(edtNameLog.getText().toString()) || TextUtils.isEmpty(edtPassLog.getText().toString())){
                    Dialog dialog = new Dialog(AdminLoginActivity.this);
                    dialog.setContentView(R.layout.dialog_fill_all_editext);

                    Button btnOk = dialog.findViewById(R.id.btn_ok);
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }else {
                    Toast.makeText(AdminLoginActivity.this, "Wrong information, please type again...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}