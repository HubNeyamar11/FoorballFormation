package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context,"Member.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(id text primary key, password text, name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }
    //데이터 삽입
    public boolean insert(String id, String password, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //칼럼 생성
        contentValues.put("id",id);
        contentValues.put("password",password);
        contentValues.put("name",name);
        long ins = db.insert("user", null,contentValues);
        if (ins==1) return  false;
        else return true;
    }
    // 아이디 존재 하는지 체크
    public boolean chkid(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select*from user where id=?",new String[]{id});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    // 아이디 패스워드 검사
    public boolean idpassword(String id, String passowrd){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where id=? and password=?", new String[]{id,passowrd});
        if (cursor.getCount()>0) return true;
        else return false;
    }


}
