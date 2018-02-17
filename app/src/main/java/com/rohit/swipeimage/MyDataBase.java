package com.rohit.swipeimage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rohit Kumar on 17-02-2018.
 */

public class MyDataBase extends SQLiteOpenHelper {

    public static String db="my.db";
    public static int version=1;

    public MyDataBase(Context context) {
        super(context, db, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ImageTable.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public List<Integer> getAllImageIds(){
        List<Integer> ids= new ArrayList<>();
        SQLiteDatabase db= getReadableDatabase();
        String [] col= new String[] {ImageTable._id};
        Cursor cursor= db.query(ImageTable.TABLE,col,null,null,null,null,null);
        if(cursor!=null&&cursor.getCount()>0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex(ImageTable._id));
                ids.add(id);
            }
        }
        Log.d("total ids ","size "+ids.size());
        return ids;
    }
    public Bitmap getImageById(int id){
        Log.d("getImageById",id+"");
        Bitmap image=null;
        SQLiteDatabase db= getWritableDatabase();
        String [] selArg= new String[] {String.valueOf(id)};
        String selection = ImageTable._id+"=?";
        Cursor cursor= db.query(ImageTable.TABLE,null,selection,selArg,null,null,null);
        if(cursor!=null&&cursor.getCount()>0){
            while (cursor.moveToNext()){
                    byte[] blob= cursor.getBlob(cursor.getColumnIndex(ImageTable.image));
                    return image= DbBitmapUtility.getImage(blob);
            }
        }
        if(cursor!=null){
            cursor.close();
        }
        return image;
    }

    public long insertImage(Bitmap image){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ImageTable.image,DbBitmapUtility.getBytes(image));
        long insert=db.insert(ImageTable.TABLE,null,contentValues);
        Log.d("insert ",insert+"");
        return insert;
    }


}
