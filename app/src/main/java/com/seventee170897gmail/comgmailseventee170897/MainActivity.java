package com.seventee170897gmail.comgmailseventee170897;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: Kondisi awal activity diciptakan yang dilakukan suatu inisialisasi pada activity_main

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        // TODO: pendeklarasian suatu variabel yang dilakukan view/widget sesuai id pada kelas xml

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameKey = username.getText().toString();
                String passwordKey = password.getText().toString();

                if (usernameKey.equals("Bilal") && passwordKey.equals("17")) {
                    // TODO: kondisi yang dilakukan jika login berhasil
                    Toast.makeText(getApplicationContext(), "LOGIN SUKSES",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, masuk.class);
                    MainActivity.this.startActivity(intent);
                    finish();
                } else {
                    // TODO: yang dilakukan jika login gagal
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Username atau Password Anda salah!")
                            .setNegativeButton("Retry", null).create().show();
                }
            }
            // TODO: Method pada btnLogin yang dilakukan sebuah kondisi yang dilakukan Intent

        });
    }

}