package com.example.krishna.codetalkers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<ItemBean> {
    Context mCtx;
    int layoutRes;
    List<ItemBean> employeeList;

    //the databasemanager object
    DatabaseHelper mDatabase;

    //modified the constructor and we are taking the DatabaseManager instance here
    public ItemAdapter(Context mCtx, int layoutRes, List<ItemBean> employeeList, DatabaseHelper mDatabase) {
        super(mCtx, layoutRes, employeeList);

        this.mCtx = mCtx;
        this.layoutRes = layoutRes;
        this.employeeList = employeeList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(layoutRes, null);

        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewDept = view.findViewById(R.id.textViewDepartment);
        TextView textViewSalary = view.findViewById(R.id.textViewSalary);
        TextView textViewJoinDate = view.findViewById(R.id.textViewJoiningDate);

        final ItemBean item = employeeList.get(position);

        textViewName.setText(item.getName());
        textViewDept.setText(item.getSection());
        textViewSalary.setText(String.valueOf(item.getPrice()));
        textViewJoinDate.setText(item.getJoiningdate());

        view.findViewById(R.id.buttonDeleteItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteEmployee(item);
            }
        });

        view.findViewById(R.id.buttonEditItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateItem(item);
            }
        });

        return view;
    }

    private void updateItem(final ItemBean item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_dialog_update_item, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText editTextName = view.findViewById(R.id.editTextName);
        final EditText editTextSalary = view.findViewById(R.id.editTextPrice);
        final Spinner spinner = view.findViewById(R.id.spinnerSection);

        editTextName.setText(item.getName());
        editTextSalary.setText(String.valueOf(item.getPrice()));


        view.findViewById(R.id.buttonUpdateItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editTextName.getText().toString().trim();
                String salary = editTextSalary.getText().toString().trim();
                String dept = spinner.getSelectedItem().toString().trim();

                if (name.isEmpty()) {
                    editTextName.setError("Name can't be empty");
                    editTextName.requestFocus();
                    return;
                }

                if (salary.isEmpty()) {
                    editTextSalary.setError("Price can't be empty");
                    editTextSalary.requestFocus();
                    return;
                }


                //calling the update method from database manager instance
                if (mDatabase.updateItem(item.getId(), name, dept, Double.valueOf(salary))) {
                    Toast.makeText(mCtx, "Item Updated", Toast.LENGTH_SHORT).show();
                    loadItemFromDatabaseAgain();
                }
                alertDialog.dismiss();
            }
        });
    }

    private void deleteEmployee(final ItemBean employee) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Are you sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //calling the delete method from the database manager instance
                if (mDatabase.deleteItem(employee.getId()))
                    loadItemFromDatabaseAgain();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadItemFromDatabaseAgain() {
        //calling the read method from database instance
        Cursor cursor = mDatabase.getAllItem();

        employeeList.clear();
        if (cursor.moveToFirst()) {
            do {
                employeeList.add(new ItemBean(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getDouble(4)
                ));
            } while (cursor.moveToNext());
        }
        notifyDataSetChanged();
    }
}
