package com.projects_kidscare.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projects_kidscare.Bean.Bean_Registration;
import com.projects_kidscare.Utility.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;



public class DB_Registration extends SQLiteAssetHelper {

    public DB_Registration(Context context) {
        super(context, Constant.DB_Name, null, Constant.DB_Version);
    }

    public void insertRegDetail(Bean_Registration br, String str) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues(0);
        cv.put("Name", br.getName());
        cv.put("DOB", br.getDOB());
        cv.put("PicturePath", br.getPicurePath());
        cv.put("isGallery", br.getIsGallery());
        db.insert("MST_Registration", null, cv);
        db.close();

    }

    public void updateRegDetail(Bean_Registration br) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues(0);
        cv.put("Name", br.getName());
        cv.put("DOB", br.getDOB());
        cv.put("PicturePath", br.getPicurePath());
        cv.put("isGallery", br.getIsGallery());
        db.update("MST_Registration", cv, "RegID=" + br.getRegID(), null);
        db.close();

    }


    public ArrayList<Bean_Registration> selectAllReg() {
        ArrayList<Bean_Registration> arrayReg = new ArrayList<Bean_Registration>();
        SQLiteDatabase db = getReadableDatabase();


        String strQuery = "Select RegID,Name,PicturePath ,isGallery " +
                "from MST_Registration";

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                Bean_Registration br = new Bean_Registration();
                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setName(cur.getString(cur.getColumnIndex("Name")));
                br.setPicurePath(cur.getString(cur.getColumnIndex("PicturePath")));
                br.setIsGallery(cur.getInt(cur.getColumnIndex("isGallery")));
                arrayReg.add(br);


            } while (cur.moveToNext());
        }
        db.close();
        return arrayReg;
    }

    public String selectNameReg(int regid) {
        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "Select Name from MST_Registration where RegID=" + regid;
        String name = "";
        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {

                name = cur.getString(cur.getColumnIndex("Name"));


            } while (cur.moveToNext());
        }
        db.close();
        return name;
    }
    public String selectDOBReg(int regid) {
        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "Select DOB from MST_Registration where RegID=" + regid;
        String DOB = "";
        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {

                DOB = cur.getString(cur.getColumnIndex("DOB"));


            } while (cur.moveToNext());
        }
        db.close();
        return DOB;
    }

    public void deleteReg(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String strQuery = "delete from MST_Registration where RegID=" + id;
        db.execSQL(strQuery);
        db.close();
    }


    public Bean_Registration selectbyIDReg(int id) {
        Bean_Registration br = new Bean_Registration();
        SQLiteDatabase db = getReadableDatabase();
        String strQuery = "Select RegID,Name,DOB,PicturePath,isGallery" +
                " from MST_Registration mr where RegID=" + id;

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {


            br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
            //br.setImgID(cur.getString(cur.getColumnIndex("ImgID")));
            br.setName(cur.getString(cur.getColumnIndex("Name")));
            br.setDOB(cur.getString(cur.getColumnIndex("DOB")));
            br.setPicurePath(cur.getString(cur.getColumnIndex("PicturePath")));
            br.setPicurePath(cur.getString(cur.getColumnIndex("isGallery")));

        }
        db.close();
        return br;
    }

    public ArrayList<Bean_Registration> selectBYReg(int cityID, String strGender) {
        ArrayList<Bean_Registration> arrayReg = new ArrayList<Bean_Registration>();
        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "select " +
                "mr.RegID," +
                "mr.Name," +
                "mr.DOB," +
                "from MST_Registration mr ";

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                Bean_Registration br = new Bean_Registration();
                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setName(cur.getString(cur.getColumnIndex("Name")));
                br.setDOB(cur.getString(cur.getColumnIndex("DOB")));
                arrayReg.add(br);

            } while (cur.moveToNext());
        }

        db.close();
        return arrayReg;
    }

}
