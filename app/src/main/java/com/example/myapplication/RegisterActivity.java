package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText idText, passwordText, confirmPassword, nameText;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

         idText =(EditText) findViewById(R.id.idText);
         passwordText =(EditText) findViewById(R.id.passwordText);
         confirmPassword =(EditText) findViewById(R.id.confirmPassword);
         nameText =(EditText) findViewById(R.id.nameText);

        db = new DBHelper(this);

        Button registerButton =(Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idText.getText().toString();
                String password = passwordText.getText().toString();
                String passwordConfirm = confirmPassword.getText().toString();
                String name = nameText.getText().toString();
                //유효성 검사
                if(id.equals("")){
                    Toast.makeText(getApplicationContext(),"아이디를 입력 해주세요",Toast.LENGTH_SHORT).show();
                }else if ( password.equals("") ||  passwordConfirm.equals("")){
                    Toast.makeText(getApplicationContext(),"비밀번호를 입력 해주세요",Toast.LENGTH_SHORT).show();
                }else if ( name.equals("")){
                    Toast.makeText(getApplicationContext(),"이름을 입력 해주세요",Toast.LENGTH_SHORT).show();
                }else{
                    if (password.equals(passwordConfirm)){
                        Boolean chkid = db.chkid(id);

                        if (chkid==true){
                            Boolean insert = db.insert(id,password,name);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"회원가입 완료",Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"존재하는 이메일 입니다",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"비밀번호를 확인 해주세요",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
