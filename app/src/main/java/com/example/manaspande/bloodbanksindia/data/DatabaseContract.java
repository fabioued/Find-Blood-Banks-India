package com.example.manaspande.bloodbanksindia.data;

import android.provider.BaseColumns;

/**
 * Created by manaspande on 2017-01-10.
 */

public class DatabaseContract {

    private DatabaseContract() {}

    public static class dbContract implements BaseColumns {

        public static String databaseName = "bbdirectory.db.sqlite";
        public static String tableName = "bbdirectory";
        public static String state = "state";
        public static String city = "city";
        public static String district = "district";
        public static String bloodBankName = "h_name";
        public static String address = "address";
        public static String pincode = "pincode";
        public static String contact = "contact";
        public static String helpline = "helpline";
        public static String fax = "fax";
        public static String category = "category";
        public static String website = "website";
        public static String email = "email";
        public static String serviceTime = "service_time";
        public static String latitude = "latitude";
        public static String longitude = "longitude";
    }
}
