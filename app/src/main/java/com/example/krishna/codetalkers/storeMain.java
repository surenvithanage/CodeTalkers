package com.example.krishna.codetalkers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class storeMain extends AppCompatActivity implements View.OnClickListener {
    EditText editTextName, editTextPrice;
    Spinner spinnerSection;

    //We creating our DatabaseManager object
    DatabaseHelper mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_main);

        mDatabase = new DatabaseHelper(this);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        spinnerSection = (Spinner) findViewById(R.id.spinnerSection);

        findViewById(R.id.buttonAddItem).setOnClickListener(this);
        findViewById(R.id.textViewViewEmployees).setOnClickListener(this);
    }

    private void addEmployee() {
        String name = editTextName.getText().toString().trim();
        String price = editTextPrice.getText().toString().trim();
        String session = spinnerSection.getSelectedItem().toString();


        if (name.isEmpty()) {
            editTextName.setError("Name can't be empty");
            editTextName.requestFocus();
            return;
        }

        if (price.isEmpty()) {
            editTextPrice.setError("Price can't be empty");
            editTextPrice.requestFocus();
            return;
        }

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String joiningDate = sdf.format(cal.getTime());

        //adding the employee with the DatabaseManager instance
        if (mDatabase.addItem(name, session, joiningDate, Double.parseDouble(price)))
            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Could not add Item", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddItem:
                addEmployee();
                break;
            case R.id.textViewViewEmployees:
                startActivity(new Intent(this, item.class));
                break;
        }
    }
}
