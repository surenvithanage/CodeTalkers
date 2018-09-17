package com.example.krishna.codetalkers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class main extends AppCompatActivity {

    public Button psin;
    public Button psup;
    public TextView homee;

    public void goLog(){
        psin = (Button)findViewById(R.id.logi);

        psin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logs = new Intent(main.this,signIn.class);
                startActivity(logs);
            }
        });
    }


    public void goSup(){
        psup = (Button)findViewById(R.id.sup);

        psup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sUp = new Intent(main.this,signUp.class);
                startActivity(sUp);
            }
        });
    }


    public void thome(){
        homee = (TextView)findViewById(R.id.test);

        homee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeel = new Intent(main.this,home.class);
                startActivity(homeel);
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goLog();
        goSup();
        thome();
    }



}
