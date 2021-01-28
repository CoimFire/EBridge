package com.sxc.ebridge.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sxc.ebridge.domains.Users;

import java.util.ArrayList;

public class StaffDBHelper extends SQLiteOpenHelper {

    Context context;
    public StaffDBHelper(@Nullable Context context) {
        super(context, "Ebridge.db",null,1);
        this.context=context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table StaffDetails(id TEXT primary key, name TEXT, department TEXT, contact TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists StaffDetails");
        db.execSQL("alter Table StaffDetails add password");
    }

    public boolean insertStaffData(String id,String name, String department, String contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id", id);
        contentValues.put("department", department);
        contentValues.put("name", name);
        contentValues.put("contact", contact);


        long result=db.insert("StaffDetails",null,contentValues);

        Toast.makeText(context, ""+result, Toast.LENGTH_SHORT).show();

        return result != -1;

    }

    public boolean updateStaffsData(String id,String name, String department, String contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("department", department);
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        Cursor cursor = db.rawQuery("Select * from StaffDetails where id = ?", new String[]{id});

        if (cursor.getCount()>0) {
            long result = db.update("StaffDetails", contentValues, "id=?", new String[]{id});
            return result != -1;
        }else {
            return false;
        }
    }
    public boolean deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from StaffDetails where id = ?", new String[]{id});

        if (cursor.getCount()>0) {
            long result = db.delete("StaffDetails", "id=?", new String[]{id});
            return result != -1;
        }else {
            return false;
        }
    }

    public  ArrayList<Users> getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from StaffDetails",null);
        ArrayList<Users> staffList = new ArrayList<>();
        while(res.moveToNext()) {
            String id = res.getString(0);   //0 is the number of id column in your database table
            String name = res.getString(1);
            String contact = res.getString(2);
            String department = res.getString(3);

            Users std = new Users(id, name,contact,department);
            staffList.add(std);
        }
        return staffList;
    }
}
