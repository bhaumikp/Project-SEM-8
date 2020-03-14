package com.projects_kidscare.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projects_kidscare.Bean.Bean_HW;
import com.projects_kidscare.Utility.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;


public class DB_HW extends SQLiteAssetHelper {
    public DB_HW(Context context) {
        super(context, Constant.DB_Name, null, Constant.DB_Version);
    }


    public void insertHWDetail(Bean_HW br) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues(0);
        cv.put("HeightFt", br.getHeightFt());
        cv.put("HeightIn", br.getHeightIn());
        cv.put("Weight", br.getWeight());
        cv.put("AgeYr", br.getAgeYr());
        cv.put("AgeM", br.getAgeM());
        cv.put("Date", br.getDate());
        cv.put("RegID", br.getRegID());

        db.insert("MST_HW", null, cv);
        db.close();

    }

    public void updateHWDetail(Bean_HW br) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues(0);

        cv.put("HeightFt", br.getHeightFt());
        cv.put("HeightIn", br.getHeightIn());
        cv.put("Weight", br.getWeight());
        cv.put("AgeYr", br.getAgeYr());
        cv.put("AgeM", br.getAgeM());
        cv.put("Date", br.getDate());


        db.update("MST_HW", cv, "HWID=" + br.getRegID(), null);
        db.close();

    }


    public void delete(int id, int regid) {
        SQLiteDatabase db = getWritableDatabase();
        String strQuery = "delete from MST_HW where HWID=" + id + " and RegID=" + regid;
        ;
        db.execSQL(strQuery);
        db.close();
    }


    public Bean_HW selectbyHwID(int id) {
        Bean_HW br = new Bean_HW();
        SQLiteDatabase db = getReadableDatabase();
        String strQuery = "Select  HWID,RegID,HeightFt,HeightIn,Weight,AgeYr,AgeM,Date " +

                " from MST_HW where HWID=" + id;

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {


            br.setHWID(cur.getInt(cur.getColumnIndex("HWID")));
            br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
            br.setHeightFt(cur.getString(cur.getColumnIndex("HeightFt")));
            br.setHeightIn(cur.getString(cur.getColumnIndex("HeightIn")));
            br.setWeight(cur.getString(cur.getColumnIndex("Weight")));
            br.setAgeYr(cur.getString(cur.getColumnIndex("AgeYr")));
            br.setAgeM(cur.getString(cur.getColumnIndex("AgeM")));
            br.setDate(cur.getString(cur.getColumnIndex("Date")));


        }
        db.close();
        return br;
    }

    public ArrayList<Bean_HW> selectBYHW(int id) {
        ArrayList<Bean_HW> arrayHW = new ArrayList<Bean_HW>();
        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "select * from MST_HW where RegID=" + id;

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                Bean_HW br = new Bean_HW();
                br.setHWID(cur.getInt(cur.getColumnIndex("HWID")));
                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setHeightFt(cur.getString(cur.getColumnIndex("HeightFt")));
                br.setHeightIn(cur.getString(cur.getColumnIndex("HeightIn")));
                br.setWeight(cur.getString(cur.getColumnIndex("Weight")));
                br.setAgeYr(cur.getString(cur.getColumnIndex("AgeYr")));
                br.setAgeM(cur.getString(cur.getColumnIndex("AgeM")));
                br.setDate(cur.getString(cur.getColumnIndex("Date")));

                arrayHW.add(br);

            } while (cur.moveToNext());
        }

        db.close();
        return arrayHW;
    }
}
