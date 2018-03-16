package br.com.lincolnv.androidcrud;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import entities.Contact;
import database.*;

import android.view.View;
import android.widget.*;
import android.content.*;

public class AddContactActivity extends AppCompatActivity {

    private Button buttonBack;
    private Button buttonSave;
    private EditText editTextName;
    private EditText editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        this.editTextName = (EditText) findViewById(R.id.editTextName);
        this.editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        this.buttonBack = (Button) findViewById(R.id.buttonBack);
        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddContactActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });
        this.buttonSave = (Button) findViewById(R.id.buttonSave);
        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ContactDB contactDB = new ContactDB(getBaseContext());
                Contact contact = new Contact();
                contact.setName(editTextName.getText().toString());
                contact.setPhone(editTextPhone.getText().toString());
                if(contactDB.create(contact)) {
                    Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Fail");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                    });
                    builder.create().show();
                }
            }
        });
    }
}