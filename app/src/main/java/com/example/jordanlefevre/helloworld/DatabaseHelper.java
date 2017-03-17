package com.example.jordanlefevre.helloworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jordanlefevre on 17/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private final String CREATE_TABLE = "CREATE TABLE author_table (" + "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                                "NAME STRING," + "MESSAGE TEXT);" +
                                        "CREATE TABLE attachments_table (" + "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                                "URL STRING," + "MESSAGE TEXT);" +
                                        "CREATE TABLE category_table (" + "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                                "TITLE STRING," + "MESSAGE TEXT);" +
                                        "CREATE TABLE news_table (" + "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                                "TITLE STRING," +
                                                                "CONTENT STRING," +
                                                                "AUTHOR INTEGER," +
                                                                "DATE STRING," +
                                                                "ATTACHMENTS INTEGER," +
                                                                "COMMENT_COUNT INTEGER," +
                                                                "CATEGORY INTEGER," +
                                                                "FOREIGN KEY (AUTHOR) REFERENCES author_table(ID)," +
                                                                "FOREIGN KEY (ATTACHMENTS) REFERENCES attachments_table(ID)," +
                                                                "FOREIGN KEY (CATEGORY) REFERENCES category_table(ID)" + "MESSAGE TEXT);";
    private static DatabaseHelper sInstance;


    public static DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context);
        }
        return sInstance;
    }
    private DatabaseHelper(Context context) {
        super(context, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int
            oldVersion, int newVersion) {
        // gérer ce cas
    }
}