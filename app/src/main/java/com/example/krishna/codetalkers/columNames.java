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
}
