package com.example.user.servicehub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 4/17/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Customer.db";
    public static final String TABLE_NAME = "customer_table";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Password";
    public static final String COL_3 = "Address";
    public static final String COL_4 = "Location";
    public static final String COL_5 = "Contact_No";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (Name TEXT,Password TEXT,Address TEXT,Location TEXT,Contact_No TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Name,String Password,String Address,String Location,String Contact_No) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Name);
        contentValues.put(COL_2,Password);
        contentValues.put(COL_3,Address);
        contentValues.put(COL_4,Location);
        contentValues.put(COL_5,Contact_No);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(String x,String y) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select Contact_No,Password from "+TABLE_NAME+" Where Contact_No"+"=?"+ "And Password"+"=?" ,new String[] {x,y});
        return res;
    }

    public Cursor getAllFromContact(String x) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" Where Contact_No"+"=?" ,new String[] {x});
        return res;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    /*
    public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }
*/
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}
