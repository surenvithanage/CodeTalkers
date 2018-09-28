package com.example.krishna.codetalkers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class signIn extends AppCompatActivity {
    private TextView pemail;
    private TextView ppass;
    private Button Sin;
    private int count = 5;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setTitle("Log In");
        db = new DatabaseHelper(this);

        pemail = findViewById(R.id.email);
        ppass = findViewById(R.id.pwd);
        Sin = findViewById(R.id.logi);
        lIn();

    }
        public void lIn (){
            Sin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = pemail.getText().toString();
                    String pwd = ppass.getText().toString();

                    System.out.println("Email : " + email);

                    //if(db.checkUser(email)) {
                        if (db.valUser(email, pwd)) {
                            Intent i = new Intent(signIn.this, home.class);
                            startActivity(i);
                        }
                    //}
                    else {
                            count--;
                            Toast.makeText(getApplicationContext(), "Password Doesn't Match\n" + count + "  Attempt Remain", Toast.LENGTH_SHORT).show();
                            if (count == 0) ;
                            Sin.setEnabled(false);
                        }
                }
            });


        }
    }





