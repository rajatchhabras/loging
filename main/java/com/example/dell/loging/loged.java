package com.example.dell.loging;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class loged extends AppCompatActivity {
Button logout;
ListView listView;
ArrayAdapter<String>arrayAdapter;
ArrayList<String> datas=new ArrayList<>();
    String st;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loged);
         st=getIntent().getExtras().getString("value");
        db = new DatabaseHelper(this);
        logout=(Button)findViewById(R.id.btn);
        listView=(ListView)findViewById(R.id.listview);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(loged.this, loging.class);
                startActivity(in);
                finish();
            }
        });
        displaydata();
    }
   public void displaydata()
    {
datas=db.getdata(st);
if(!datas.isEmpty())
{
    arrayAdapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,datas);
    listView.setAdapter(arrayAdapter);
}
else
{
    Toast.makeText(getApplicationContext(),"no data found",Toast.LENGTH_SHORT).show();
}
    }
}
