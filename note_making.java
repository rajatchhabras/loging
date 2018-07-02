package com.example.dell.note_register;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class note_making extends AppCompatActivity {
    Button logout;
    ListView listView;
    sqldb sqldb;

    preferences preferences;
    ArrayList<String> datas = new ArrayList<>();

    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_making);
        sqldb = new sqldb(getApplicationContext());
        preferences = new preferences(getApplicationContext());


        listView = (ListView) findViewById(R.id.listview);


        if (!preferences.login()) {
            Intent intent = new Intent(note_making.this, userlogin.class);
            startActivity(intent);
            finish();
            return;
        }
        displaynotes(preferences.getUsername());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.main_menu,menu);
      return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.addnode)
        {
openDialogbox(preferences.getUsername());

        }else if(id==R.id.logout)
        {
            preferences.logout();
            Intent intent=new Intent(note_making.this,userlogin.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
String p;
    EditText edt;
    private void openDialogbox( String tablename ) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_box, null);
        dialogBuilder.setView(dialogView);
p=tablename;
        edt = (EditText) dialogView.findViewById(R.id.edit1);
final String a=edt.getText().toString();
        dialogBuilder.setTitle("Custom dialog");
        dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                sqldb.insert(p,a);
                displaynotes(p);
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    void displaynotes(String username) {
        datas = sqldb.getnotes(username);
        if (!datas.isEmpty()) {
            arrayAdapter = new ArrayAdapter<>(note_making.this, android.R.layout.simple_list_item_1, datas);
            listView.setAdapter(arrayAdapter);
        } else {
            Toast.makeText(note_making.this," notes list is empty ", Toast.LENGTH_SHORT).show();
        }


    }

}
