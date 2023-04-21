package com.github.cptzee.zmoney.Data.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.Nullable;

import com.github.cptzee.zmoney.Data.Credential;

import java.util.ArrayList;
import java.util.List;

public class CredentialHelper extends SQLiteOpenHelper {
    private static CredentialHelper instance;

    private CredentialHelper(@Nullable Context context) {
        super(context, "db_Credential", null, 1);
    }

    public static CredentialHelper getInstance(Context context) {
        if(instance == null)
            instance = new CredentialHelper(context);
        return instance;
    }

    private final String TABLENAME = "tbl_Credentials";

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLENAME + " (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT," +
                    "encryptedPassword TEXT," +
                    "pin TEXT," +
                    "archived INTEGER)");
        }catch (SQLiteException e){
            Log.e("DBHelper", "Unable to create the " + TABLENAME, e.getCause());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("DROP TABLE " + TABLENAME);
            onCreate(db);
        }catch (SQLiteException e){
            Log.e("DBHelper", "Unable to upgrade the " + TABLENAME, e.getCause());
        }
    }

    public void insert(Credential data){
        ContentValues contentValues = prepareData(data);
        new insertTask().execute(new PreparedData(getWritableDatabase(), data, contentValues));
    }

    public List<Credential> get(){
        List<Credential> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("SELECT ID, username, encryptedPassword, pin FROM " + TABLENAME + " WHERE archived = 0", null);
            while (cursor.moveToNext())
                list.add(prepareData(cursor));
        }catch (SQLiteException e){
            Log.e("DBHelper", "Unable to retrieve from the " + TABLENAME, e.getCause());
        }
        return list;
    }

    public Credential get(int ID){
        Credential data = new Credential();
        SQLiteDatabase db = getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("SELECT ID, username, encryptedPassword, pin FROM " + TABLENAME + " WHERE ID = ?", new String[]{String.valueOf(ID)});
            while (cursor.moveToNext())
                data = prepareData(cursor);

        }catch (SQLiteException e){
            Log.e("DBHelper", "Unable to retrieve from the " + TABLENAME, e.getCause());
        }
        return data;
    }

    public void update(Credential data){
        ContentValues contentValues = prepareData(data);
        new updateTask().execute(new PreparedData(getWritableDatabase(), data, contentValues));
    }

    public void remove(Credential data){
        ContentValues contentValues = prepareData(data);
        new removeTask().execute(new PreparedData(getWritableDatabase(), data, contentValues));
    }

    public int getNextID(){
        int data = 0;
        SQLiteDatabase db = getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("SELECT MAX(ID) FROM " + TABLENAME, null);
            while (cursor.moveToNext())
                data = cursor.getInt(0);
            data++;
        }catch (SQLiteException e){
            Log.e("DBHelper", "Unable to retrieve from the " + TABLENAME, e.getCause());
        }
        return data;
    }

    private ContentValues prepareData(Credential data){
        ContentValues content = new ContentValues();
        if(data.getUsername() != null)
            content.put("username", data.getUsername());
        if(data.getEncryptedPassword() != null)
            content.put("encryptedPassword", data.getEncryptedPassword());
        if(data.getPin() != null)
            content.put("pin", data.getPin());
        content.put("archived", 0);
        return  content;
    }

    private Credential prepareData(Cursor cursor){
        Credential data = new Credential();
        data.setID(cursor.getInt(0));
        data.setUsername(cursor.getString(1));
        data.setEncryptedPassword(cursor.getString(2));
        data.setPin(cursor.getString(3));
        return data;
    }

    private class PreparedData{
        private SQLiteDatabase db;
        private Credential data;
        private ContentValues values;

        public PreparedData(SQLiteDatabase db, Credential data, ContentValues values) {
            this.db = db;
            this.data = data;
            this.values = values;
        }
    }

    private class insertTask extends AsyncTask<PreparedData, Void, Void> {
        @Override
        protected Void doInBackground(PreparedData... preparedData) {
            SQLiteDatabase db = preparedData[0].db;
            ContentValues values = preparedData[0].values;
            try{
                db.insert(TABLENAME, null, values);
            }catch (SQLiteException e){
                Log.e("DBHelper", "Unable to insert into the " + TABLENAME, e.getCause());
            }
            return null;
        }
    }

    private class updateTask extends AsyncTask<PreparedData, Void, Void>{
        @Override
        protected Void doInBackground(PreparedData... preparedData) {
            SQLiteDatabase db = preparedData[0].db;
            ContentValues values = preparedData[0].values;
            Credential data = preparedData[0].data;
            try{
                db.update(TABLENAME, values, "ID = ?", new String[]{String.valueOf(data.getID())});
            }catch (SQLiteException e){
                Log.e("DBHelper", "Unable to insert into the " + TABLENAME, e.getCause());
            }
            return null;
        }
    }

    private class removeTask extends AsyncTask<PreparedData, Void, Void>{
        @Override
        protected Void doInBackground(PreparedData... preparedData) {
            SQLiteDatabase db = preparedData[0].db;
            ContentValues values = new ContentValues();
            Credential data = preparedData[0].data;
            values.put("archived", 1);
            try{
                db.update(TABLENAME, values, "ID = ?", new String[]{String.valueOf(data.getID())});
            }catch (SQLiteException e){
                Log.e("DBHelper", "Unable to insert into the " + TABLENAME, e.getCause());
            }
            return null;
        }
    }
}
