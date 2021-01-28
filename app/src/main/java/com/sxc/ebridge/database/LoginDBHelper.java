package com.sxc.ebridge.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sxc.ebridge.utils.LoginResult;

public class LoginDBHelper extends SQLiteOpenHelper {

    Context context;
    StrudentsDBHelper studentDB;

    public LoginDBHelper(@Nullable Context context) {
        super(context, "Ebridge.db", null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Login(id TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Login");
    }

    public boolean insertStudentsData(String id, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("password", password);

        long result = db.insert("Login", null, contentValues);

        Toast.makeText(context, "" + result, Toast.LENGTH_SHORT).show();

        return result != -1;

    }

    public String login(String id, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor getId = db.rawQuery("Select id from Login where id = ?", new String[]{id});
        Cursor getPassword = db.rawQuery("Select password from Login where id = ?", new String[]{id});
        String result;
        if (id.equals(getId.toString())) {
            if (password.equals(getPassword.toString())) {
                result = LoginResult.OK;
            } else {
                result = LoginResult.WRONG_PASSWORD;
            }
        } else {
            result = LoginResult.WRONG_ID;
        }
        return result;
    }

    public String signup(String id, String password) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            studentDB = new StrudentsDBHelper(context);

            Cursor res = db.rawQuery("Select id from StudentDetails where id=?", new String[]{id});
            String studentId = res.getString(0);

            Cursor getId = db.rawQuery("Select id from Login where id = ?", new String[]{id});
            String result = "";
            if (studentId.equals(id)) {
                result = LoginResult.FAILED;
            } else if (id.equals(getId.toString())) {
                result = LoginResult.WRONG_ID;
            } else {
                insertStudentsData(id, password);
                result = LoginResult.OK;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "NULL";
        }

    }
}
