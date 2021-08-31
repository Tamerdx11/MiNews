package com.example.ahmedtawfik.lab05android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String databaseName="newsDB";
    private final static int databaseVersion=1;
    //TABLE 1 COLUMN NAMES
    public final static String TABLE01_NAME="details";
    public final static String TABLE01_COL01="_detailsId";
    public final static String TABLE01_COL02="title";
    public final static String TABLE01_COL03="description";
    public final static String TABLE01_COL04="link";
    //public final static String TABLE01_COL05="image";
    ///////////////////////
    //TABLE 2 NAMES
    public final static String TABLE02_NAME="source";
    public final static String TABLE02_COL01="_sourceId";
    public final static String TABLE02_COL02="sourceName";
    /////


    private static final String CREATE_TABLE01_STATEMENT ="CREATE TABLE "+TABLE01_NAME+"("+TABLE01_COL01+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            ""+TABLE01_COL02+" TEXT NOT NULL ,"+TABLE01_COL03+" TEXT  NOT NULL,"+TABLE01_COL04+" TEXT NOT NULL," +
            ""+TABLE02_COL02+" REFERENCES "+TABLE02_NAME+"("+TABLE02_COL01+"));";
    private static final String CREATE_TABLE02_STATEMENT = "CREATE TABLE "+TABLE02_NAME+"("+TABLE02_COL01+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            ""+TABLE02_COL02+" TEXT NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE01_STATEMENT);
        sqLiteDatabase.execSQL(CREATE_TABLE02_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE01_NAME );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE02_NAME );
        onCreate(sqLiteDatabase);
    }

}
