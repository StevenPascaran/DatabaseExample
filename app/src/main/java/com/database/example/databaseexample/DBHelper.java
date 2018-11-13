package com.database.example.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "pupils.db";
    final static int VER = 1;
    final static String TABLE = "points";

    public DBHelper(Context context){
        super(context,DBNAME,null,VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table Creation
        String createTable = "CREATE TABLE points (ID INTEGER PRIMARY KEY AUTOINCREMENT, Fname TEXT, Lname TEXT, Point INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Changing Table or Datatype
        String dropTable = "DROP TABLE IF EXISTS points";
        db.execSQL(dropTable);
        onCreate(db);
    }

    public boolean insert(String fname, String lname, int point){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Fname", fname);
        values.put("Lname", lname);
        values.put("Point", point);
        long isInserted = db.insert(TABLE, null, values);
        if(isInserted == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor selectRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM points", null);
    }

    public boolean update(String id, String fname, String lname, int point){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Fname", fname);
        values.put("Lname", lname);
        values.put("Point", point);
        db.update(TABLE, values, "ID=?", new String[]{id});
        return true;
    }

    public Integer delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE, "ID=?",  new String[]{id});
    }
}
