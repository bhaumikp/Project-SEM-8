package com.projects_kidscare.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projects_kidscare.Bean.Bean_VaccineTable;
import com.projects_kidscare.Utility.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;



public class DB_VaccineDetail extends SQLiteAssetHelper {

    public DB_VaccineDetail(Context context) {
        super(context, Constant.DB_Name, null, Constant.DB_Version);
    }



    public ArrayList<Bean_VaccineTable> selectAllVaccineDetail(int id) {
        ArrayList<Bean_VaccineTable> arrayVac = new ArrayList<Bean_VaccineTable>();
        SQLiteDatabase db = getReadableDatabase();


        String strQuery = "Select  VaccineDetailId,VaccineName,Description " +
                "from MST_VaccineDetails  where VaccineDetailId=" + id;

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                Bean_VaccineTable br = new Bean_VaccineTable();
                br.setVaccineDetailId(cur.getInt(cur.getColumnIndex("VaccineDetailId")));
                br.setVaccineName(cur.getString(cur.getColumnIndex("VaccineName")));
                br.setDescription(cur.getString(cur.getColumnIndex("Description")));


                arrayVac.add(br);


            } while (cur.moveToNext());
        }
        db.close();
        return arrayVac;
    }
    public Bean_VaccineTable getallvaccineInfo(int id) {
        ArrayList<Bean_VaccineTable> arrayvaccine = new ArrayList<Bean_VaccineTable>();
        SQLiteDatabase db = getReadableDatabase();
        String strQuery = "Select MST_VaccineTable.VaccineID,MST_VaccineDetails.VaccineDetailId,MST_VaccineDetails.VaccineName,MST_VaccineDetails.Description " +
                "from MST_VaccineDetails inner join MST_VaccineTable " +
                "On MST_VaccineDetails.VaccineDetailId =MST_VaccineTable.VaccineDetailId " +
                " where MST_VaccineDetails.VaccineDetailId="+id;



        Cursor cur = db.rawQuery(strQuery, null);
        cur.moveToFirst();
        Bean_VaccineTable bc = new Bean_VaccineTable();
        bc.setVaccineID(cur.getInt(cur.getColumnIndex("VaccineID")));
        bc.setVaccineDetailId(cur.getInt(cur.getColumnIndex("VaccineDetailId")));
        bc.setVaccineName(cur.getString(cur.getColumnIndex("VaccineName")));
        bc.setDescription(cur.getString(cur.getColumnIndex("Description")));
        db.close();
        return bc;
    }



}
