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

public class StrudentsDBHelper extends SQLiteOpenHelper {

    Context context;
    public StrudentsDBHelper(@Nullable Context context) {
        super(context, "Ebridge.db",null,1);
        this.context=context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table StudentDetails(id TEXT primary key, name TEXT, department TEXT, contact TEXT, dob TEXT, batch TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists StudentDetails");
        db.execSQL("alter Table StudentDetails add password");
    }

    public boolean insertStudentsData(String id,String name, String department, String contact, String dob, String batch){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id", id);
        contentValues.put("department", department);
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        contentValues.put("batch", batch);


        long result=db.insert("StudentDetails",null,contentValues);

        Toast.makeText(context, ""+result, Toast.LENGTH_SHORT).show();

        return result != -1;

    }

    public boolean updateStudentsData(String id,String name, String department, String contact, String dob, String batch){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("department", department);
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        contentValues.put("batch", batch);
        Cursor cursor = db.rawQuery("Select * from StudentDetails where id = ?", new String[]{id});

        if (cursor.getCount()>0) {
            long result = db.update("StudentDetails", contentValues, "id=?", new String[]{id});
            return result != -1;
        }else {
            return false;
        }
    }
    public boolean deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from StudentDetails where id = ?", new String[]{id});

        if (cursor.getCount()>0) {
            long result = db.delete("StudentDetails", "id=?", new String[]{id});
            return result != -1;
        }else {
            return false;
        }
    }

    public  ArrayList<Users> getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from StudentDetails",null);
        ArrayList<Users> studentsList = new ArrayList<>();
        while(res.moveToNext()) {
            String id = res.getString(0);   //0 is the number of id column in your database table
            String name = res.getString(1);
            String dob = res.getString(2);
            String contact = res.getString(3);
            String department = res.getString(4);
            String batch = res.getString(5);

            Users std = new Users(id, name,dob,contact,department,batch);
            studentsList.add(std);
        }
        return studentsList;
    }
}
