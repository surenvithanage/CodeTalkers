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

    //Store
    private static final String TABLE_NAME = "item";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SECTION = "section";
    private static final String COLUMN_JOIN_DATE = "joiningdate";
    private static final String COLUMN_PRICE = "price";


    private static final String User_TABLE_CREATE =
            "CREATE TABLE " + columNames.userTable.User_Table + " (" +
                    columNames.userTable.User_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    columNames.userTable.User_Name + " TEXT, " +
                    columNames.userTable.User_Address + " TEXT, " +
                    columNames.userTable.User_email + " TEXT, " +
                    columNames.userTable.User_Password + " TEXT, " + columNames.userTable.User_Mobile + " Integer)";

    private static final String Stock_TABLE_CREATE =
            "CREATE TABLE " + columNames.stockTable.Stock_Table+ " (" +
                    columNames.stockTable.Stock_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    columNames.stockTable.Stock_Name + " TEXT, " +
                    columNames.stockTable.Stock_Description + " TEXT, " +
                    columNames.stockTable.Stock_Price + " INTEGER )";

    //Store
//    String sql = "CREATE TABLE " + TABLE_NAME + " (\n" +
//            "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT item_pk PRIMARY KEY AUTOINCREMENT,\n" +
//            "    " + COLUMN_NAME + " varchar(200) NOT NULL,\n" +
//            "    " + COLUMN_SECTION + " varchar(200) NOT NULL,\n" +
//            "    " + COLUMN_JOIN_DATE + " datetime NOT NULL,\n" +
//            "    " + COLUMN_PRICE + " double NOT NULL\n" +
//            ");";

    String sql = "create table item ( id INTEGER NOT NULL CONSTRAINT item_pk PRIMARY KEY AUTOINCREMENT ,name varchar(200) NOT NULL, section varchar(200) NOT NULL , joiningdate datetime NOT NULL , price NOT NULL )";


    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Stock Table
        db.execSQL(Stock_TABLE_CREATE);
        //Item Table
        db.execSQL(sql);
        //User Table
        db.execSQL(User_TABLE_CREATE);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + stockTable.Stock_Table);
        db.execSQL("DROP TABLE IF EXISTS " + columNames.userTable.User_Table);
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
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

        long result = db.insert(columNames.stockTable.Stock_Table, null, contentValues);

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

    public columNames getUserDetails(String id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from user where uid = ?",new String[]{id});

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


    //Store
    boolean addItem(String name, String section, String joiningdate, double price) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_SECTION, section);
        contentValues.put(COLUMN_JOIN_DATE, joiningdate);
        contentValues.put(COLUMN_PRICE, price);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert("item", null, contentValues) != -1;
    }

    Cursor getAllItem() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    boolean updateItem(int id, String name, String section, double price) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_SECTION, section);
        contentValues.put(COLUMN_PRICE, price);
        return db.update(TABLE_NAME, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 1;
    }

    boolean deleteItem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 1;
    }
}

