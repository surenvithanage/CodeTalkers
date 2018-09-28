package com.example.krishna.codetalkers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.krishna.codetalkers.columNames.stockTable;

import java.sql.Blob;




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
            "CREATE TABLE " + stockTable.Stock_Name + " (" +
                    stockTable.Stock_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    stockTable.Stock_Name + " TEXT, " +
                    stockTable.Stock_Description + " TEXT, " +
                    stockTable.Stock_Price + " TEXT)";


    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Stock Table
        db.execSQL(Stock_TABLE_CREATE);

        //User Table
        db.execSQL(User_TABLE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + stockTable.Stock_Table);
        db.execSQL("DROP TABLE IF EXISTS " + columNames.userTable.User_Table);
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

    public boolean addStoreItems(String sName, String sDescription, Integer price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columNames.stockTable.Stock_Name, sName);
        contentValues.put(columNames.stockTable.Stock_Description, sDescription);
        contentValues.put(columNames.stockTable.Stock_Price, price);
        //contentValues.put(columNames.stockTable.Stock_Photo, pic);

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
}

