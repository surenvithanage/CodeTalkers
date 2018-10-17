package com.example.krishna.codetalkers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.krishna.codetalkers.columNames.stockTable;

import java.sql.Blob;
import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Database_Name = "Store.db";
    public static final String Cart_Table_Name = "cart";



    private static final String User_TABLE_CREATE =
            "CREATE TABLE " + columNames.userTable.User_Table + " (" +
                    columNames.userTable.User_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    columNames.userTable.User_Name + " TEXT, " +
                    columNames.userTable.User_Address + " TEXT, " +
                    columNames.userTable.User_email + " TEXT, " +
                    columNames.userTable.User_Password + " TEXT, " + columNames.userTable.User_Mobile + " Integer)";

    private static final String Stock_TABLE_CREATE =
            "CREATE TABLE " + columNames.itemTable.Item_Table+ " (" +
                    columNames.itemTable.Item_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    columNames.itemTable.Item_Name + " TEXT, " +
                    columNames.itemTable.Item_Section + " TEXT, " +
                    columNames.itemTable.Item_JDate + " DATETIME, " +
                    columNames.itemTable.Item_Price + " INTEGER )";


    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Stock Table
        db.execSQL(Stock_TABLE_CREATE);
//        //Item Table
//        db.execSQL(sql);
        //User Table
        db.execSQL(User_TABLE_CREATE);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
        String query_1 = "DROP TABLE IF EXISTS " + columNames.itemTable.Item_Table;
        String query_2 = "DROP TABLE IF EXISTS " + columNames.userTable.User_Table;

        db.execSQL(query_1);
        db.execSQL(query_2);
        onCreate(db);


    }

    public boolean userinsertdata(String uname, String email, String address, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columNames.userTable.User_Name, uname);
        contentValues.put(columNames.userTable.User_email, email);
        contentValues.put(columNames.userTable.User_Address, address);
        contentValues.put(columNames.userTable.User_Password, pwd);

        long result = db.insert(columNames.userTable.User_Table, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean valUser(String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(" SELECT * FROM " + columNames.userTable.User_Table + " WHERE " + columNames.userTable.User_email + " =? AND " + columNames.userTable.User_Password+ " =? ", new String[]{email,pass} );
        int cCount = cursor.getCount();
        if (cCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    public columNames getUserDetails(Integer id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from user where uid = ?",new String[]{String.valueOf(id)});

        if (cursor != null)
            cursor.moveToFirst();

        columNames u = new columNames(
                cursor.getString(cursor.getColumnIndex(columNames.userTable.User_Name)),
                cursor.getString(cursor.getColumnIndex(columNames.userTable.User_email)),
                cursor.getString(cursor.getColumnIndex(columNames.userTable.User_Address)),
                cursor.getString(cursor.getColumnIndex(columNames.userTable.User_Mobile)));

        // close the db connection
        cursor.close();

        return u;
    }

    boolean updateUser(int id, String name, String email, String address, int phone) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columNames.userTable.User_Name, name);
        contentValues.put(columNames.userTable.User_email, email);
        contentValues.put(columNames.userTable.User_Address, address);
        contentValues.put(columNames.userTable.User_Mobile, phone);
        return db.update(columNames.userTable.User_Table, contentValues, columNames.userTable.User_Id + "=?", new String[]{String.valueOf(id)}) == 1;
    }

    boolean deleteUser(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(columNames.userTable.User_Table, columNames.userTable.User_Table + "=?", new String[]{String.valueOf(id)}) == 1;
    }


    //Store
    boolean addItem(String name, String section, String joiningdate, double price) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(columNames.itemTable.Item_Name, name);
        contentValues.put(columNames.itemTable.Item_Section, section);
        contentValues.put(columNames.itemTable.Item_JDate, joiningdate);
        contentValues.put(columNames.itemTable.Item_Price, price);
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(columNames.itemTable.Item_Table, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    Cursor getAllItem() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + columNames.itemTable.Item_Table, null);
    }

    boolean updateItem(int id, String name, String section, double price) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columNames.itemTable.Item_Name, name);
        contentValues.put(columNames.itemTable.Item_Section, section);
        contentValues.put(columNames.itemTable.Item_Price, price);
        return db.update(columNames.itemTable.Item_Table, contentValues, columNames.itemTable.Item_Id + "=?", new String[]{String.valueOf(id)}) == 1;
    }
    
    public ArrayList<ClassName> searchResult(int ID){
        ArrayList<ClassName> result = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Select * from <TableName> ";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst(){
            do{
                //process here
            }while(cursor.moveToNext());
        }
           
           return result;
        
    }

    boolean deleteItem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(columNames.itemTable.Item_Table, columNames.itemTable.Item_Id + "=?", new String[]{String.valueOf(id)}) == 1;
    }
}

