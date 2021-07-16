package com.example.app2.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.app2.app.App2app;

import java.io.File;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static DbHelper dbHelper;

    public static final String DB_1 = "/db1/bancomovel";
    public static final String DB_2 = "/db2/bancomovel";
    public static final String DB_3 = "/db3/bancomovel";


    public DbHelper(String dbPath){
        super(App2app.getInstance(),dbPath, null, VERSION );
    }

    public synchronized static DbHelper getInstance(String bdName){
        if (dbHelper == null){
            dbHelper = new DbHelper(
                    App2app.getInstance().getExternalFilesDir(null).getPath().concat(bdName));
            File[] file = (App2app.getInstance()
                    .getExternalFilesDir(null)).listFiles(File::isDirectory);
        }
        return dbHelper;
    }

    public void setNull(){
        dbHelper = null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
