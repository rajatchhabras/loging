package com.example.dell.loging;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loging extends AppCompatActivity {
    EditText emails, passs;
    Button login;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);
        db = new DatabaseHelper(this);
        emails = (EditText) findViewById(R.id.editText1);
        passs = (EditText) findViewById(R.id.editext2);
        login = (Button) findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emails.getText().toString();
                String pass = passs.getText().toString();
                Boolean chckdata = db.verfydetails(email, pass);
                if (chckdata == true) {
                    Toast.makeText(getApplicationContext(), "login Successfully", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(loging.this, loged.class);

                    in.putExtra( "value",email);
                    startActivity(in);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Incorrect Email-id or Password", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}