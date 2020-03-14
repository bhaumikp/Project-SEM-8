package com.projects_kidscare.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projects_kidscare.Bean.Bean_Vaccine;
import com.projects_kidscare.Bean.Bean_VaccineTable;
import com.projects_kidscare.Bean.Bean_Vaccine_Sedual;
import com.projects_kidscare.Utility.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;


public class DB_VaccineSedualTable extends SQLiteAssetHelper {
    public DB_VaccineSedualTable(Context context) {
        super(context, Constant.DB_Name, null, Constant.DB_Version);
    }


    public void insertDetailFromTable(ArrayList<Bean_VaccineTable> bst, Integer reg) {
        SQLiteDatabase db = getWritableDatabase();

        for (int i = 0; i < bst.size(); i++) {
            ContentValues cv = new ContentValues(0);
            cv.put("VaccineName", bst.get(i).getVaccineName());
            cv.put("DoseNo", bst.get(i).getDoseNo());
            cv.put("AfterDate", "");
            cv.put("BeforeDate", "");
            cv.put("OnDate", "");
            cv.put("VaccineIDTable", bst.get(i).getVaccineID());
            //cv.put("ActualDate","");
            //cv.put("Status","");
            cv.put("RegID", reg);
            cv.put("VaccineIDTable", bst.get(i).getVaccineID());
            db.insert("MST_VaccineSedualTable", null, cv);

        }


        db.close();

    }

    public void insertDetail(Bean_Vaccine bst) {
        SQLiteDatabase db = getWritableDatabase();


        ContentValues cv = new ContentValues(0);
        cv.put("VaccineName", bst.getVaccineName());
        cv.put("DoseNo", bst.getDoseNo());
        cv.put("AfterDate", "");
        cv.put("BeforeDate", "");
        cv.put("OnDate", bst.getDate());
        cv.put("VaccineIDTable", bst.getVaccineID());
        //cv.put("ActualDate","");
        //cv.put("Status","");
        cv.put("RegID", bst.getRegID());
        cv.put("VaccineIDTable", bst.getVaccineID());
        db.insert("MST_VaccineSedualTable", null, cv);


        db.close();

    }


    public void updateDetail(int VaccineSedualID, int s) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Status", s);
        db.update("MST_VaccineSedualTable", cv, "VaccineSedualID=" + VaccineSedualID, null);
        db.close();

    }

    public int selectMaxRegID() {
        SQLiteDatabase db = getReadableDatabase();
        String strQuery = "Select MAX(RegID) from MST_Registration";
        Integer id = 0;
        Cursor cur = db.rawQuery(strQuery, null);
        if (cur.moveToFirst()) {

            id = cur.getInt(cur.getColumnIndex("MAX(RegID)"));

        }
        db.close();
        return id;
    }

    public int selectStatus(int vacid) {
        SQLiteDatabase db = getReadableDatabase();
        String strQuery = "Select Status from MST_VaccineSedualTable where VaccineSedualID=" + vacid;
        Integer id = 0;
        Cursor cur = db.rawQuery(strQuery, null);
        if (cur.moveToFirst()) {

            id = cur.getInt(cur.getColumnIndex("Status"));

        }
        db.close();
        return id;
    }


    public ArrayList<Bean_Vaccine_Sedual> selectAll() {
        ArrayList<Bean_Vaccine_Sedual> arrayVacSedual = new ArrayList<Bean_Vaccine_Sedual>();
        SQLiteDatabase db = getReadableDatabase();


        String strQuery = "Select VaccineSedualID,VaccineName,DoseNo,AfterDate,BeforeDate,OnDate from MST_VaccineSedualTable";

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                String onDate = cur.getString(cur.getColumnIndex("OnDate")) + "";
                String afterDate = cur.getString(cur.getColumnIndex("AfterDate")) + "";
                String beforeDate = cur.getString(cur.getColumnIndex("BeforeDate")) + "";
                Bean_Vaccine_Sedual bvt = new Bean_Vaccine_Sedual();

                bvt.setVaccineName(cur.getString(cur.getColumnIndex("VaccineName")));
                bvt.setDoseNo(cur.getString(cur.getColumnIndex("DoseNo")));

                if (afterDate.equalsIgnoreCase("null"))
                    bvt.setAfterDate("-");
                else
                    bvt.setAfterDate(afterDate);

                if (beforeDate.equalsIgnoreCase("null"))
                    bvt.setBeforeDate("-");
                else
                    bvt.setBeforeDate(beforeDate);


                if (onDate.equalsIgnoreCase("null"))
                    bvt.setOnDate("-");
                else
                    bvt.setOnDate(onDate);


                arrayVacSedual.add(bvt);


            } while (cur.moveToNext());
        }
        db.close();
        return arrayVacSedual;
    }


    public ArrayList<Bean_Vaccine_Sedual> selectBY(int id) {
        ArrayList<Bean_Vaccine_Sedual> arrayVacSedual = new ArrayList<Bean_Vaccine_Sedual>();
        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "select  Status,VaccineSedualID,RegID,VaccineName,DoseNo,AfterDate,BeforeDate,OnDate from MST_VaccineSedualTable where RegID=" + id;

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                String onDate = cur.getString(cur.getColumnIndex("OnDate")) + "";
                String afterDate = cur.getString(cur.getColumnIndex("AfterDate")) + "";
                String beforeDate = cur.getString(cur.getColumnIndex("BeforeDate")) + "";
                Bean_Vaccine_Sedual bvt = new Bean_Vaccine_Sedual();
                bvt.setVaccineName(cur.getString(cur.getColumnIndex("VaccineName")));
                bvt.setDoseNo(cur.getString(cur.getColumnIndex("DoseNo")));
                bvt.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                bvt.setStatus(cur.getInt(cur.getColumnIndex("Status")));
                bvt.setVaccineID(cur.getInt(cur.getColumnIndex("VaccineSedualID")));

                if (afterDate.equalsIgnoreCase("null"))
                    bvt.setAfterDate(" ");
                else
                    bvt.setAfterDate(afterDate);

                if (beforeDate.equalsIgnoreCase("null"))
                    bvt.setBeforeDate(" ");
                else
                    bvt.setBeforeDate(beforeDate);


                if (onDate.equalsIgnoreCase("null"))
                    bvt.setOnDate(" ");
                else
                    bvt.setOnDate(onDate);


                arrayVacSedual.add(bvt);

            } while (cur.moveToNext());
        }

        db.close();
        return arrayVacSedual;
    }

    public ArrayList<Bean_Vaccine_Sedual> selectBYDattee() {
        ArrayList<Bean_Vaccine_Sedual> arrayVacSedual = new ArrayList<Bean_Vaccine_Sedual>();
        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "select VaccineSedualID,RegID,VaccineName,DoseNo,AfterDate,BeforeDate,OnDate from MST_VaccineSedualTable";

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                String onDate = cur.getString(cur.getColumnIndex("OnDate")) + "";
                Bean_Vaccine_Sedual bvt = new Bean_Vaccine_Sedual();
                bvt.setVaccineName(cur.getString(cur.getColumnIndex("VaccineName")));
                bvt.setRegID(cur.getInt(cur.getColumnIndex("RegID")));

                if (onDate.equalsIgnoreCase("null"))
                    bvt.setOnDate(" ");
                else
                    bvt.setOnDate(onDate);


                arrayVacSedual.add(bvt);

            } while (cur.moveToNext());
        }

        db.close();
        return arrayVacSedual;
    }


    public String tempdb(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String strQuery = "Select AfterDate " + " from MST_VaccineSedualTable where RegID=" + id;
        String st_temp = "";

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {

            st_temp = cur.getString(cur.getColumnIndex("AfterDate"));

        }
        db.close();
        return st_temp;
    }

    public void updateOnTimeRegID(String date, Integer regid, String type, Integer vid) {

        if (type.equalsIgnoreCase("On")) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("OnDate", date);
            db.update("MST_VaccineSedualTable", cv, "RegID=" + regid + " and VaccineIDTable=" + vid, null);
            db.close();
        }

        if (type.equalsIgnoreCase("After")) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("AfterDate", date);
            db.update("MST_VaccineSedualTable", cv, "RegID=" + regid + " and VaccineIDTable=" + vid, null);
            db.close();
        }

        if (type.equalsIgnoreCase("Before")) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("BeforeDate", date);
            db.update("MST_VaccineSedualTable", cv, "RegID=" + regid + " and VaccineIDTable=" + vid, null);
            db.close();
        }


    }


}
