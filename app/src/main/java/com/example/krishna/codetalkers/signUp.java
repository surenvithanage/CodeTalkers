package com.example.krishna.codetalkers;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class signUp extends AppCompatActivity {


    DatabaseHelper db;
    Button reg;
    TextView fname;
    TextView femail;
    TextView faddress;
    TextView fpwd;
    TextView frpwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        db = new DatabaseHelper(this);

        reg = (Button) findViewById(R.id.sUp);
        fname = (TextView) findViewById(R.id.uname);
        faddress = (TextView) findViewById(R.id.uAddress);
        femail = (TextView) findViewById(R.id.email);
        fpwd = (TextView) findViewById(R.id.pwd);
        frpwd = (TextView) findViewById(R.id.rpwd);
        RegUser();
    }
        public void RegUser(){

            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String uname = fname.getText().toString();
                    String email = femail.getText().toString();
                    String address = faddress.getText().toString();
                    String pwd = fpwd.getText().toString();
                    String rpwd = frpwd.getText().toString();

                    if (pwd.equals(rpwd)){
                        if (uname.isEmpty()) {
                            fname.setError("Name can't be empty");
                            fname.requestFocus();
                            return;
                        }
                        if (email.isEmpty()) {
                            femail.setError("Email can't be empty");
                            femail.requestFocus();
                            return;
                        }
                        if (address.isEmpty()) {
                            faddress.setError("Address can't be empty");
                            faddress.requestFocus();
                            return;
                        }
                        if (pwd.isEmpty()) {
                            frpwd.setError("Password can't be empty");
                            frpwd.requestFocus();
                            return;
                        }
                        boolean isInserted = db.userinsertdata(uname, email, address, pwd);
                        if (isInserted == true) {
                            Toast.makeText(getApplicationContext(), "Registration Successfull", Toast.LENGTH_LONG).show();
                            Intent j = new Intent(signUp.this, signIn.class);
                            startActivity(j);

                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_LONG).show();
                            Intent k = new Intent(signUp.this, signUp.class);
                            startActivity(k);
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Password Doesn't Match", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }






}
