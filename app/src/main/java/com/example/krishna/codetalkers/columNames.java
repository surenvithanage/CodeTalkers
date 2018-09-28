package com.example.krishna.codetalkers;

import android.provider.BaseColumns;

public final class columNames  {

    public static class userTable implements BaseColumns{

        public static final String User_Table = "user";
        public static final String User_Id = "uId";
        public static final String User_Name = "Name";
        public static final String User_email = "email";
        public static final String User_Address = "Address";
        public static final String User_Mobile= "MobileNo";
        public static final String User_Password = "Password";



    }

    public static class stockTable implements  BaseColumns{

        public static final String Stock_Table = "stock";
        public static final String Stock_Id = "sId";
        public static final String Stock_Name = "Name";
        public static final String Stock_Description = "Description";
        public static final String Stock_Price = " Price ";
        public static final String Stock_Photo = "Photo";
    }


    public static  class  itemTable implements BaseColumns{
        public static final String Item_Table = "item";
        public static final String Item_Id = "id";
        public static final String Item_Name = "name";
        public static final String Item_Section = "section";
        public static final String Item_JDate = " joiningdate  ";
        public static final String Item_Price = " price";

    }

    private String name;
    private String email;
    private String address;
    private String phone;

    public columNames(String name , String email, String address, String phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

}
