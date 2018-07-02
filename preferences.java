package com.example.dell.note_register;

import android.content.Context;
import android.content.SharedPreferences;

public class preferences {
    Context context;
    SharedPreferences sharedPreferences;
    private final String username="username";
    private final String password="password";
    private final String islogin ="islogin";
    preferences(Context con)
    {
        this.context=con;
        sharedPreferences=context.getSharedPreferences("preps",Context.MODE_PRIVATE);
    }
    void createshareddata(String user,String pass)
    {
        sharedPreferences.edit().putString(username,user).apply();
        sharedPreferences.edit().putString(password,pass).apply();
        sharedPreferences.edit().putBoolean(islogin,true).apply();
    }
    void logout()
    {
        sharedPreferences.edit().putBoolean(islogin,false).apply();
    }
    boolean  login()
    {
        return sharedPreferences.getBoolean(islogin,false);
    }
    String getUsername()
    {
        return sharedPreferences.getString(username,"");
    }
}
