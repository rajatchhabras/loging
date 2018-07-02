package com.example.dell.note_register;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class userlogin extends AppCompatActivity {
    EditText user,pass;
    Button login;
    preferences preferences;
sqldb sqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        user=(EditText)findViewById(R.id.editText1);
        pass=(EditText)findViewById(R.id.editext2);
        login=(Button)findViewById(R.id.button);

        preferences=new preferences(getApplicationContext());
       sqldb=new sqldb(getApplicationContext());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.createshareddata(user.getText().toString(),pass.getText().toString());
                if(!sqldb.checktable(user.getText().toString()))
                {
                    sqldb.create(user.getText().toString());
                }
                Intent intent=new Intent(userlogin.this,note_making.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
