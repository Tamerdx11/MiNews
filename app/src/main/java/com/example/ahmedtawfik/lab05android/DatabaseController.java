package com.example.ahmedtawfik.lab05android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseController {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public DatabaseController(Context context) {
        databaseHelper =new DatabaseHelper(context);
    }
    public void open(){
        database=databaseHelper.getWritableDatabase();
    }

    public long insertdetails(String title,String description,String link){
        ContentValues values=new ContentValues();
        values.put(DatabaseHelper.TABLE01_COL02,title);
        values.put(DatabaseHelper.TABLE01_COL03,description);
        values.put(DatabaseHelper.TABLE01_COL04,link);
        return database.insert(DatabaseHelper.TABLE01_NAME,null,values);

    }
    public long insertsource(String sourceName){
        ContentValues values=new ContentValues();
        values.put(DatabaseHelper.TABLE01_COL02,sourceName);
        return database.insert(DatabaseHelper.TABLE01_NAME,null,values);

    }

    public int deletedetails(int id,String title,String description,String link){

        return database.delete(DatabaseHelper.TABLE01_NAME,DatabaseHelper.TABLE01_COL01+"="+id,null);

    }

    public Details selectdetail(int id){

        Cursor cursor=database.query(DatabaseHelper.TABLE01_NAME,new String[]{DatabaseHelper.TABLE01_COL01,DatabaseHelper.TABLE01_COL02,DatabaseHelper.TABLE01_COL03,DatabaseHelper.TABLE01_COL04},DatabaseHelper.TABLE01_COL01+"="+id,null,null,null,null);
        cursor.moveToFirst();
        return  cursorToDetails(cursor);
    }

    public Source selectSource(int id){

        Cursor cursor=database.query(DatabaseHelper.TABLE01_NAME,new String[]{DatabaseHelper.TABLE01_COL01,DatabaseHelper.TABLE01_COL02,DatabaseHelper.TABLE01_COL03,DatabaseHelper.TABLE01_COL04},DatabaseHelper.TABLE01_COL01+"="+id,null,null,null,null);
        cursor.moveToFirst();
        return  cursorToSource(cursor);
    }

    public ArrayList<Details> selectdetails(int id){
        ArrayList<Details> details=new ArrayList<>();
        Cursor cursor=database.query(DatabaseHelper.TABLE01_NAME,new String[]{DatabaseHelper.TABLE01_COL01,DatabaseHelper.TABLE01_COL02,DatabaseHelper.TABLE01_COL03,DatabaseHelper.TABLE01_COL04},null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            details.add(cursorToDetails(cursor));
            cursor.moveToNext();
        }
        return  details;
    }

    private Details cursorToDetails(Cursor cursor) {
        return new Details(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3));
    }

    private Source cursorToSource(Cursor cursor) {
        return new Source(cursor.getInt(0), cursor.getString(1));
    }

    public void close(){
        if(databaseHelper!=null){
            databaseHelper.close();
        }
    }
}
