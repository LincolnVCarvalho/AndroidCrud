package database;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;

import java.util.ArrayList;
import java.util.List;

import entities.*;

/**
 * Created by LincolnV on 16/03/2018.
 */

public class ContactDB extends SQLiteOpenHelper {

    private static String dbName = "ContactDB";
    private static String tableName = "contact";
    private static String idColumn = "id";
    private static String nameColumn = "name";
    private static String phoneColumn = "phone";

    public ContactDB(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table" + tableName + "(" +
                idColumn + "integer primary key autoincrement, " +
                nameColumn + "text, " +
                phoneColumn + "text  " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exist" + tableName);
        onCreate(sqLiteDatabase);
    }

    public List<Contact> findAll() {
        try {
            List<Contact> contacts = new ArrayList<Contact>();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " +
                    tableName, null);
            if (cursor.moveToFirst()) {
                do {
                    Contact contact = new Contact();
                    contact.setId(cursor.getInt(0));
                    contact.setName(cursor.getString(1));
                    contact.setPhone(cursor.getString(2));
                    contacts.add(contact);
                } while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return contacts;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(Contact contact) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nameColumn, contact.getName());
            contentValues.put(phoneColumn, contact.getPhone());
            long rows = sqLiteDatabase.insert(tableName, null, contentValues);

            sqLiteDatabase.close();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }
}