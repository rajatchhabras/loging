package com.example.dell.note_register;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class sqldb {
    Context context;
    SQLiteDatabase db;
    preferences preferences;
    sqldb(Context context)
    {
        this.context=context;
        db=context.openOrCreateDatabase("userdb",Context.MODE_PRIVATE,null);
    }
    void create(String tablenam)
    {
        try
        {
            db.execSQL("CREATE TABLE IF NOT EXISTS " +tablenam+ "(notes text)");

        }catch(Exception e) {
            throw e;
        }
    }
    void insert(String tablenam,String note)
    {
        db.execSQL("INSERT INTO " +tablenam+ " VALUES('" +note+ "')");
    }

    public ArrayList<String> getnotes(String tablenam)
    {
        Cursor c=db.rawQuery("select * from " +tablenam+ "",null);
        ArrayList<String> note=new ArrayList<>();

        if(c.moveToFirst())
        {
            do{
                int index=c.getColumnIndex("notes");
                String notee=c.getString(index);
                note.add(notee);
            }while(c.moveToNext());
        }
        return note;
    }

    boolean checktable(String tableman)
    {
        Cursor c = null;

        try
        {
            c = db.query(tableman, null,
                    null, null, null, null, null);

        }
        catch (Exception e) {
            return false;
        }


        return true;
    }


}


