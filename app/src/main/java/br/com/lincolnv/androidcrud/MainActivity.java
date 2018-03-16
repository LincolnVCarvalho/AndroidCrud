package br.com.lincolnv.androidcrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import adapters.ContactListAdapter;
import entities.Contact;
import database.*;

import android.view.View;
import android.widget.*;
import android.content.*;

public class MainActivity extends AppCompatActivity {

    private Button buttonAdd;
    private ListView listViewContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.buttonAdd = (Button) findViewById(R.id.buttonAdd);
        this.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });

        ContactDB contactDB = new ContactDB(this);
        this.listViewContacts = (ListView) findViewById(R.id.listViewContacts);
        this.listViewContacts.setAdapter(new ContactListAdapter(this,
                contactDB.findAll())
        );
    }
}
