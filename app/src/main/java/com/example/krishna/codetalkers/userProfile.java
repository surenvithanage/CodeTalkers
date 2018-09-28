package com.example.krishna.codetalkers;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class userProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper db;
    public EditText name;
    public EditText email;
    public EditText phone;
    public EditText addr;
    public Button update;
    public Button delete;
    public  Integer pid ; //Get Id from the Session

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("User Profile");




        name = findViewById(R.id.uname);
        email = findViewById(R.id.uEmail);
        phone = findViewById(R.id.uPhone);
        addr = findViewById(R.id.uAddress);
        update();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_home_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {

            case R.id.home:
                Intent h = new Intent(userProfile.this, home.class);
                startActivity(h);
                break;
            case R.id.shop:
                Intent i = new Intent(userProfile.this, storeMain.class);
                startActivity(i);
                break;
            case R.id.uprofile:
                Intent g = new Intent(userProfile.this, userProfile.class);
                startActivity(g);
                displayDetails();
                break;
            case R.id.cart:
                Intent s = new Intent(userProfile.this, cart.class);
                startActivity(s);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void displayDetails(){



        columNames us = db.getUserDetails(pid);

            name.setText("");
            name.append(us.getName());
            email.setText("");
            email.append(us.getEmail());
            phone.setText("");
            phone.append(us.getPhone());
            addr.append(us.getAddress());




    }
    public  void update(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.updateUser(pid,name.getText().toString(),email.getText().toString(),addr.getText().toString(),Integer.valueOf(phone.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(getApplicationContext(), "Updated Failed", Toast.LENGTH_LONG).show();



            }
        });
    }
    public  void delete(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.deleteUser(pid)) {
                    Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Delete Failed", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
