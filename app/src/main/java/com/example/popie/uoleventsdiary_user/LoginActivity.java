package com.example.popie.uoleventsdiary_user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etId, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etId = findViewById(R.id.etId);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etId.getText().toString().equals(etPassword.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), ViewAllActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Welcome" + etId.getText().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
