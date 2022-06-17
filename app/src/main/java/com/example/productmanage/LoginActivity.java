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

import com.example.productmanage.Database.UserSQLite;

public class LoginActivity extends AppCompatActivity {
    private EditText edtNameLog, edtPassLog;
    private Button btnLogin;
    private UserSQLite userSQLite;
    private String name, password;
    private TextView txtLoginAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtNameLog = findViewById(R.id.edt_name_log);
        edtPassLog = findViewById(R.id.edt_password_log);
        btnLogin = findViewById(R.id.btn_signin);
        txtLoginAdmin = findViewById(R.id.txt_signin_admin);

        Intent intent = getIntent();
        edtNameLog.setText(intent.getStringExtra("name"));

        userSQLite = new UserSQLite(this);
        Cursor cursor = userSQLite.getUser();

        while (cursor.moveToNext()){
            name = cursor.getString(1);
            password = cursor.getString(2);
        }
        cursor.moveToFirst();
        cursor.close();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.equals(edtNameLog.getText().toString()) && password.equals(edtPassLog.getText().toString())){
                    Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent1);
                }else if(TextUtils.isEmpty(edtNameLog.getText().toString()) || TextUtils.isEmpty(edtPassLog.getText().toString())){
                    Dialog dialog = new Dialog(LoginActivity.this);
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
                    Toast.makeText(LoginActivity.this, "Wrong information, please type again...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(LoginActivity.this, AdminLoginActivity.class);
                startActivity(intent1);
            }
        });
    }
}