package com.example.krishna.codetalkers;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class item extends AppCompatActivity {
    List<ItemBean> itemBeanList;
    ListView listView;

    //The databasemanager object
    DatabaseHelper mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //Instantiating the database manager object
        mDatabase = new DatabaseHelper(this);

        itemBeanList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listViewEmployees);

        loadEmployeesFromDatabase();
    }

    private void loadEmployeesFromDatabase() {
        //we are here using the DatabaseManager instance to get all employees
        Cursor cursor = mDatabase.getAllItem();

        if (cursor.moveToFirst()) {
            do {
                itemBeanList.add(new ItemBean(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getDouble(4)
                ));
            } while (cursor.moveToNext());

            //passing the databasemanager instance this time to the adapter
            ItemAdapter adapter = new ItemAdapter(this, R.layout.activity_layout_item, itemBeanList, mDatabase);
            listView.setAdapter(adapter);
        }
    }
}
