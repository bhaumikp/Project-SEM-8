package com.projects_kidscare.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projects_kidscare.Bean.Bean_Vaccine;
import com.projects_kidscare.Bean.Bean_Vaccine_Sedual;
import com.projects_kidscare.Utility.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;


public class DB_Vaccine extends SQLiteAssetHelper {

    public DB_Vaccine(Context context) {
        super(context, Constant.DB_Name, null, Constant.DB_Version);
    }


    public void insertDetail(Bean_Vaccine br) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues(0);
        cv.put("VaccineName", br.getVaccineName());
        cv.put("DoseNo", br.getDoseNo());
        cv.put("Status", br.getStatus());
        cv.put("Date", br.getDate());
        cv.put("RegID", br.getRegID());


        db.insert("MST_Vaccine", null, cv);
        db.close();

    }


    public ArrayList<Bean_Vaccine> selectAll() {
        ArrayList<Bean_Vaccine> arrayVaccine = new ArrayList<Bean_Vaccine>();
        SQLiteDatabase db = getReadableDatabase();


        String strQuery = "Select VaccineID,RegID,VaccineName,DoseNo,Status,Date " +
                "from MST_Vaccine ";

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                Bean_Vaccine br = new Bean_Vaccine();
                br.setVaccineID(cur.getInt(cur.getColumnIndex("VaccineID")));
                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setVaccineName(cur.getString(cur.getColumnIndex("VaccineName")));
                br.setDoseNo(cur.getString(cur.getColumnIndex("DoseNo")));
                br.setStatus(cur.getString(cur.getColumnIndex("Status")));
                br.setDate(cur.getString(cur.getColumnIndex("Date")));

                arrayVaccine.add(br);


            } while (cur.moveToNext());
        }
        db.close();
        return arrayVaccine;
    }

    public void delete(int id, int regid) {
        SQLiteDatabase db = getWritableDatabase();
        String strQuery = "delete from MST_Vaccine where VaccineID=" + id + " and RegID=" + regid;
        ;
        db.execSQL(strQuery);
        db.close();
    }


    public ArrayList<Bean_Vaccine_Sedual> selectBY(int id) {
        ArrayList<Bean_Vaccine_Sedual> arrayVaccine = new ArrayList<Bean_Vaccine_Sedual>();
        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "select * from MST_Vaccine where RegID=" + id;

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                Bean_Vaccine_Sedual br = new Bean_Vaccine_Sedual();

                br.setVaccineID(cur.getInt(cur.getColumnIndex("VaccineID")));
                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setVaccineName(cur.getString(cur.getColumnIndex("VaccineName")));
                br.setDoseNo(cur.getString(cur.getColumnIndex("DoseNo")));
                br.setStatus(cur.getInt(cur.getColumnIndex("Status")));
                br.setDate(cur.getString(cur.getColumnIndex("Date")));


                arrayVaccine.add(br);

            } while (cur.moveToNext());
        }

        db.close();
        return arrayVaccine;
    }
}

