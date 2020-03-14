package com.projects_kidscare.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projects_kidscare.Bean.Bean_MedicineDoses;
import com.projects_kidscare.Utility.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;



public class DB_MedicineDoses extends SQLiteAssetHelper {

    public DB_MedicineDoses(Context context) {
        super(context, Constant.DB_Name, null, Constant.DB_Version);
    }


    public void insertDetailMedicineDoses(Bean_MedicineDoses br, String str) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues(0);
        cv.put("MedicineName", br.getMedicineName());
        cv.put("Morning", br.getMorning());
        cv.put("Afternoon", br.getAfternoon());
        cv.put("Evening", br.getEvening());
        cv.put("Night", br.getNight());
        cv.put("Doses", br.getDoses());
        cv.put("DiseasesID", br.getDiseasesID());
        //cv.put("MedicineID", br.getMedicineID());

        if (str.equalsIgnoreCase("Edit")) {
           // Log.d("hyyy", "" + br.getMedicineID());
            db.update("MST_Medicine", cv, "MedicineID=" + br.getMedicineID(), null);

        }
        else
        db.insert("MST_Medicine", null, cv);
        db.close();

    }

    public void insertMedicineDoseDetail(Bean_MedicineDoses br) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues(0);
        cv.put("MedicineName", br.getMedicineName());
        cv.put("Morning", br.getMorning());
        cv.put("Afternoon", br.getAfternoon());
        cv.put("Evening", br.getEvening());
        cv.put("Night", br.getNight());
        cv.put("Doses", br.getDoses());
        cv.put("DiseasesID", br.getDiseasesID());

        db.insert("MST_Medicine", null, cv);
        db.close();

    }

    public void updateMedicineDoseDetail(Bean_MedicineDoses br) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues(0);

        cv.put("MedicineName", br.getMedicineName());
        cv.put("Morning", br.getMorning());
        cv.put("Afternoon", br.getAfternoon());
        cv.put("Evening", br.getEvening());
        cv.put("Night", br.getNight());
        cv.put("Doses", br.getDoses());
        cv.put("DiseasesID", br.getDiseasesID());

        db.update("MST_Medicine", cv, "MedicineID=" + br.getMedicineID(), null);
        db.close();

    }


    public ArrayList<Bean_MedicineDoses> selectAllMedicineDoses(int did) {
        ArrayList<Bean_MedicineDoses> arrayMed1 = new ArrayList<Bean_MedicineDoses>();
        SQLiteDatabase db = getReadableDatabase();


        String strQuery = "Select DiseasesID,MedicineID,MedicineName,Morning,Afternoon,Evening,Night,Doses " +
                "from MST_Medicine where DiseasesID=" + did;

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                Bean_MedicineDoses br = new Bean_MedicineDoses();
                br.setDiseasesID(cur.getInt(cur.getColumnIndex("DiseasesID")));
                br.setMedicineID(cur.getInt(cur.getColumnIndex("MedicineID")));
                br.setMedicineName(cur.getString(cur.getColumnIndex("MedicineName")));
                br.setMorning(cur.getString(cur.getColumnIndex("Morning")));
                br.setAfternoon(cur.getString(cur.getColumnIndex("Afternoon")));
                br.setEvening(cur.getString(cur.getColumnIndex("Evening")));
                br.setNight(cur.getString(cur.getColumnIndex("Night")));
                br.setDoses(cur.getString(cur.getColumnIndex("Doses")));

                arrayMed1.add(br);


            } while (cur.moveToNext());
        }
        db.close();
        return arrayMed1;
    }

    public void deleteMedicineDoses(int id,int did) {
        SQLiteDatabase db = getWritableDatabase();
        String strQuery = "delete from MST_Medicine where MedicineID=" + id + " and DiseasesID=" + did;
        db.execSQL(strQuery);
        db.close();
    }

    public ArrayList<Bean_MedicineDoses> selectBYMedicineDoses(String str) {
        ArrayList<Bean_MedicineDoses> arrayMed1 = new ArrayList<Bean_MedicineDoses>();
        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "select " +
                "DiseasesID," +
                "MedicineID," +
                "MedicineName," +
                "Morning," +
                "Afternoon," +
                "Evening," +
                "Night," +
                "Doses," +
                "from MST_Medicine  ";

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                Bean_MedicineDoses br = new Bean_MedicineDoses();
                br.setDiseasesID(cur.getInt(cur.getColumnIndex("DiseasesID")));
                br.setMedicineID(cur.getInt(cur.getColumnIndex("MedicineID")));
                br.setMedicineName(cur.getString(cur.getColumnIndex("MedicineName")));
                br.setMorning(cur.getString(cur.getColumnIndex("Morning")));
                br.setAfternoon(cur.getString(cur.getColumnIndex("Afternoon")));
                br.setEvening(cur.getString(cur.getColumnIndex("Evening")));
                br.setNight(cur.getString(cur.getColumnIndex("Night")));
                br.setDoses(cur.getString(cur.getColumnIndex("Doses")));

                arrayMed1.add(br);

            } while (cur.moveToNext());
        }

        db.close();
        return arrayMed1;
    }


    public Bean_MedicineDoses selectbyMedicineDoseID(int id) {
        Bean_MedicineDoses br = new Bean_MedicineDoses();
        SQLiteDatabase db = getReadableDatabase();
        String strQuery = "Select DiseasesID,MedicineID,MedicineName,Morning,Afternoon,Evening,Night,Doses " +
                "from MST_Medicine where MedicineID=" + id;

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {


            br.setDiseasesID(cur.getInt(cur.getColumnIndex("DiseasesID")));
            br.setMedicineID(cur.getInt(cur.getColumnIndex("MedicineID")));
            br.setMedicineName(cur.getString(cur.getColumnIndex("MedicineName")));
            br.setMorning(cur.getString(cur.getColumnIndex("Morning")));
            br.setAfternoon(cur.getString(cur.getColumnIndex("Afternoon")));
            br.setEvening(cur.getString(cur.getColumnIndex("Evening")));
            br.setNight(cur.getString(cur.getColumnIndex("Night")));
            br.setDoses(cur.getString(cur.getColumnIndex("Doses")));


        }
        db.close();
        return br;
    }

}
