package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button soccerButton;
    Button footsalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView idText =(TextView) findViewById(R.id.idText);
        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);

        soccerButton =(Button) findViewById(R.id.soccerButton);
        footsalButton = (Button) findViewById(R.id.footsalButton);

        Intent intent = getIntent();
        String id= intent.getStringExtra("id");

        welcomeMessage.append("환영합니다! " +id + "님");

        soccerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Soccer.class);
                startActivityForResult(intent,1);
            }
        });
        footsalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Footsal.class);
                startActivityForResult(intent,1);
            }
        });



    }
}
