package com.example.moveinsync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper2 extends SQLiteOpenHelper {
    public DBHelper2(Context context)
    {
        super(context,params.DB_NAME,null,params.DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String create="CREATE TABLE "+params.TABLE_NAME+"("+params.COUNT_NAME+" INTEGER "+")";
        Log.d("dbvishal","Query being run is : "+create);
        db.execSQL(create);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists "+params.TABLE_NAME);
    }
    public  void insertData(Contact2 contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(params.COUNT_NAME,contact.getCount());
        db.insert(params.TABLE_NAME,null,values);
        Log.d("dbvishal1","successfully inserted");
        //db.close();
    }
    public boolean check(int c){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("Select * from "+params.TABLE_NAME+" where"+params.COUNT_NAME+" = "+c,null);
        Log.d("ab2", "check"+String.valueOf(c));
        if(cursor.getCount()>0)
            return  true;
        else
            return false;
    }
    public int getCount(){
        SQLiteDatabase MyDB= this.getReadableDatabase();
        Cursor cursor= MyDB.rawQuery("Select MAX"+"("+params.COUNT_NAME+")"+" from "+params.TABLE_NAME,null);
       int g=0;
       if(cursor!=null)
       {
           if(cursor.moveToFirst()){
               g=cursor.getInt(0);
               Log.d("sam", String.valueOf(g));
           }
       }
        return g;
    }
}
