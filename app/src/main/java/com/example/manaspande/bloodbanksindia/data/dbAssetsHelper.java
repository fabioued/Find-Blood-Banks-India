package com.example.manaspande.bloodbanksindia.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by manaspande on 2017-01-12.
 */

public class dbAssetsHelper extends SQLiteAssetHelper {

    static final String DATABASE_NAME = "bbdirectory.db.sqlite";
    static final Integer DATABASE_VERSION = 1;

    public dbAssetsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
