package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText =(EditText) findViewById(R.id.idText);
        final EditText passwordText =(EditText) findViewById(R.id.passwordText);

        Button loginButton =(Button) findViewById(R.id.loginButton);

        Button registerButton = (Button) findViewById(R.id.registerButton);

        db = new DBHelper(this);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = idText.getText().toString();
                String password = passwordText.getText().toString();

                Boolean Chkidpass = db.idpassword(id,password);
                if(Chkidpass==true) {
                    Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityForResult(intent, 1);
                }else
                    Toast.makeText(getApplicationContext(),"아이디와 비밀번호를 확인 해주세요.",Toast.LENGTH_SHORT).show();


            }
        });
    }
}
