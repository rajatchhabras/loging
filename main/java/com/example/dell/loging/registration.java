package com.example.dell.loging;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends AppCompatActivity {

    EditText txt1,txt2,txt3,txt4,txt5;
    Button save,login;
    String a,b,c,d,e;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db=new DatabaseHelper(this);
        txt1=(EditText)findViewById(R.id.editText);
        txt2=(EditText)findViewById(R.id.pass);
        txt3=(EditText)findViewById(R.id.email);
        txt4=(EditText)findViewById(R.id.phone);
        txt5=(EditText)findViewById(R.id.confirm);
        save=(Button)findViewById(R.id.btn);
        login=(Button)findViewById(R.id.button);


        e=txt5.getText().toString();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(registration.this,loging.class);
                startActivity(in);
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=txt1.getText().toString();
                b=txt2.getText().toString();
                c=txt3.getText().toString();
                e=txt5.getText().toString();
                d=txt4.getText().toString();

               if(a.equals("")||b.equals("")||c.equals("")||e.equals("")) {
                   Toast.makeText(getApplicationContext(), "Filed are empty", Toast.LENGTH_LONG).show();
               }
               else
               {
                   if(b.equals(e))
                   {
                       Boolean checkemail=db.checkemail(c);
                       if(checkemail==true) {
                           Boolean insert = db.insert(a, b, c, d);
                           if (insert == true) {
                               Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                           }
                           else
                           {
                               Toast.makeText(getApplicationContext(), " Not Registered ", Toast.LENGTH_SHORT).show();
                           }
                       }
                       else
                       {
                           Toast.makeText(getApplicationContext(),"Email is already Ocupied",Toast.LENGTH_SHORT).show();
                       }
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(),"Password doesnot match",Toast.LENGTH_SHORT).show();
                   }
               }

            }
        });


    }
    }

