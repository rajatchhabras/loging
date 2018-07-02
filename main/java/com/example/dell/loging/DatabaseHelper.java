package com.example.dell.loging;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    //private  final  Context context;

    public DatabaseHelper(Context context) {
        super(context,"login.db", null, 1);
       // this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table user(username text,password text, email text primary key, phone text)");
}


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
db.execSQL("drop table if exists user");
    }
    public  boolean insert(String user,String pass,String email,String phone)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",pass);
        contentValues.put("email",email);

        contentValues.put("phone",phone);
        long ins=db.insert("user",null,contentValues);
        if(ins==-1)return false;
        else
            return true;
    }public boolean checkemail(String emails)
    {
SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user where email=?",new String[]{emails});
        if(cursor.getCount()>0) return false;
        else return  true;
    }
    public Boolean verfydetails(String emails,String passs)
    {
SQLiteDatabase db=this.getReadableDatabase();
Cursor cursor=db.rawQuery("select * from user where email=? and password=?",new String[]{emails,passs});
        if(cursor.getCount()>0) return true;
        else return  false;
    }
  public ArrayList<String> getdata(String emails)
   {
       ArrayList<String> data=new ArrayList<>();
       SQLiteDatabase db= this.getReadableDatabase();
       Cursor cursor=db.rawQuery("select * from user where email=?",new String[]{emails});
       cursor.moveToFirst();
       if(cursor.getCount()>0)
       {
           int index=cursor.getColumnIndex("username");
          int index1=cursor.getColumnIndex("password");
           int index3=cursor.getColumnIndex("email");
           int index4=cursor.getColumnIndex("phone");
           String userd=cursor.getString(index);
           String passed=cursor.getString(index1);
           String emailed=cursor.getString(index3);
           String phoned=cursor.getString(index4);
           data.add(userd);
           data.add(passed);
           data.add(emailed);
           data.add(phoned);


       }
       return data;
   }


}
