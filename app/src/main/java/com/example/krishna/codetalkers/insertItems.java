package com.example.krishna.codetalkers;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

public class insertItems extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SQLiteDatabase db;
    public EditText iname;
    public EditText ides;
    public EditText iprice;
    public Button insert;
    DatabaseHelper dbhelper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       // db = dbhelper.getWritableDatabase();

        iname = findViewById(R.id.itName);
        ides = findViewById(R.id.itDescription);
        iprice = findViewById(R.id.itprice);
        insert = findViewById(R.id.insertItem);
        //addItem();


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
                Intent h = new Intent(insertItems.this, home.class);
                startActivity(h);
                break;
            case R.id.shop:
                Intent i = new Intent(insertItems.this, store.class);
                startActivity(i);
                break;
            case R.id.uprofile:
                Intent g = new Intent(insertItems.this, userProfile.class);
                startActivity(g);
                break;
            case R.id.cart:
                Intent s = new Intent(insertItems.this, cart.class);
                startActivity(s);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void addItem(){
//
//        insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = iname.getText().toString();
//                String des = ides.getText().toString();
//                Integer price = Integer.parseInt(iprice.getText().toString());
//
//                if(name.length() == 0 || des.length() == 0 || price == 0){
//                    Toast.makeText(getApplicationContext(), "Complete The Fields", Toast.LENGTH_LONG).show();
//                }
//                else{
//                    boolean isInserted = dbhelper.addStoreItems(name,des,price);
//                    if (isInserted) {
//                        Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_LONG).show();
//                        Intent j = new Intent(insertItems.this, store.class);
//                        startActivity(j);
//
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Adding Failed", Toast.LENGTH_LONG).show();
//                        Intent k = new Intent(insertItems.this, insertItems.class);
//                        startActivity(k);
//                    }
//
//
//                }
//
//            }
//        });

//    }
}
