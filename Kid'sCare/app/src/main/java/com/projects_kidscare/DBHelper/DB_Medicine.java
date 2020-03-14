package com.projects_kidscare.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projects_kidscare.Bean.Bean_DosesMedicine;
import com.projects_kidscare.Bean.Bean_Medicine;
import com.projects_kidscare.Utility.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;


public class DB_Medicine extends SQLiteAssetHelper {
    public DB_Medicine(Context context) {
        super(context, Constant.DB_Name, null, Constant.DB_Version);
    }


    public void insertMedicineDetail(Bean_Medicine br) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues(0);
        cv.put("DiseasesName", br.getDiseasesName());
        cv.put("Date", br.getDate());
        cv.put("RegID", br.getRegID());

        db.insert("MST_Diseases", null, cv);
        db.close();

    }

    public void updateMedicineDetail(Bean_Medicine br) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues(0);

        cv.put("DiseasesName", br.getDiseasesName());
        cv.put("Date", br.getDate());

        db.update("MST_Diseases", cv, "DiseasesID=" + br.getDiseasesID(), null);
        db.close();

    }


    public ArrayList<Bean_Medicine> selectMedicineAll() {
        ArrayList<Bean_Medicine> arrayMed = new ArrayList<Bean_Medicine>();
        SQLiteDatabase db = getReadableDatabase();


        String strQuery = "Select DiseasesID,RegID,DiseasesName,Date from MST_Diseases ";

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                Bean_Medicine br = new Bean_Medicine();
                br.setDiseasesID(cur.getInt(cur.getColumnIndex("DiseasesID")));
                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setDiseasesName(cur.getString(cur.getColumnIndex("DiseasesName")));
                br.setDate(cur.getString(cur.getColumnIndex("Date")));

                arrayMed.add(br);


            } while (cur.moveToNext());
        }
        db.close();
        return arrayMed;
    }

    public void deleteMedicine(int id, int regid) {
        SQLiteDatabase db = getWritableDatabase();
        String strQuery = "delete from MST_Diseases where DiseasesID=" + id + " and RegID=" + regid;
        db.execSQL(strQuery);
        db.close();
    }


    public ArrayList<Bean_Medicine> selectBYMedicine(int id) {
        ArrayList<Bean_Medicine> arrayMed = new ArrayList<Bean_Medicine>();
        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "select * from MST_Diseases where RegID=" + id;

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                Bean_Medicine br = new Bean_Medicine();
                br.setDiseasesID(cur.getInt(cur.getColumnIndex("DiseasesID")));
                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setDiseasesName(cur.getString(cur.getColumnIndex("DiseasesName")));
                br.setDate(cur.getString(cur.getColumnIndex("Date")));

                arrayMed.add(br);

            } while (cur.moveToNext());
        }

        db.close();
        return arrayMed;
    }

    public ArrayList<Bean_DosesMedicine> selectAllDosesBY(int id) {
        ArrayList<Bean_DosesMedicine> arrayMed = new ArrayList<Bean_DosesMedicine>();
        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "Select d.DiseasesName,d.Date,d.DiseasesID,d.RegID,m.MedicineName,m.MedicineID,m.Morning,m.Afternoon,m.Night,m.Doses,m.DiseasesID as ID from MST_Diseases d left join MST_Medicine m on d.DiseasesID=m.DiseasesID where d.RegID=" + id;

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                Bean_DosesMedicine br = new Bean_DosesMedicine();
                br.setDiseasesID(cur.getInt(cur.getColumnIndex("DiseasesID")));
                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setDiseasesName(cur.getString(cur.getColumnIndex("DiseasesName")));
                br.setDate(cur.getString(cur.getColumnIndex("Date")) + "");
                br.setMedicineID(cur.getInt(cur.getColumnIndex("MedicineID")));
                br.setMedicineName(cur.getString(cur.getColumnIndex("MedicineName")) + "");
                br.setMorning(cur.getString(cur.getColumnIndex("Morning")) + "");
                br.setAfternoon(cur.getString(cur.getColumnIndex("Afternoon")) + "");
                br.setNight(cur.getString(cur.getColumnIndex("Night")) + "");
                br.setDoses(cur.getString(cur.getColumnIndex("Doses")) + "");

                arrayMed.add(br);

            } while (cur.moveToNext());
        }

        db.close();
        return arrayMed;
    }

    public Bean_Medicine selectBYMedicineID(int id) {

        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "select * from MST_Diseases where DiseasesID=" + id;
        Bean_Medicine br = null;
        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                br = new Bean_Medicine();
                br.setDiseasesID(cur.getInt(cur.getColumnIndex("DiseasesID")));
                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setDiseasesName(cur.getString(cur.getColumnIndex("DiseasesName")));
                br.setDate(cur.getString(cur.getColumnIndex("Date")));


            } while (cur.moveToNext());
        }

        db.close();
        return br;
    }
}
