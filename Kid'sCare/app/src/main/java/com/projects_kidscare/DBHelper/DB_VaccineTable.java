package com.projects_kidscare.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projects_kidscare.Bean.Bean_VaccineTable;
import com.projects_kidscare.Utility.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;



public class DB_VaccineTable extends SQLiteAssetHelper {

    public DB_VaccineTable(Context context) {
        super(context, Constant.DB_Name, null, Constant.DB_Version);
    }


    public ArrayList<Bean_VaccineTable> selectAllVaccineTable() {
        ArrayList<Bean_VaccineTable> arrayVactbl = new ArrayList<Bean_VaccineTable>();
        SQLiteDatabase db = getReadableDatabase();


        String strQuery = "Select VaccineID,MST_VaccineDetails.VaccineName,DoseNo,AfterMonth,BeforeMonth,OnMonth \n" +
                "        from MST_VaccineTable inner join MST_VaccineDetails On MST_VaccineTable.VaccineDetailId =MST_VaccineDetails.VaccineDetailId ";

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                String onMonth = cur.getString(cur.getColumnIndex("OnMonth")) + "";
                String afterMonth = cur.getString(cur.getColumnIndex("AfterMonth")) + "";
                String beforeMonth = cur.getString(cur.getColumnIndex("BeforeMonth")) + "";
                Bean_VaccineTable br = new Bean_VaccineTable();
                br.setVaccineName(cur.getString(cur.getColumnIndex("VaccineName")));
                br.setDoseNo(cur.getString(cur.getColumnIndex("DoseNo")));
                br.setVaccineID(cur.getInt(cur.getColumnIndex("VaccineID")));
                //br.setAfterMonth(cur.getString(cur.getColumnIndex("AfterMonth")));
                if (afterMonth.equalsIgnoreCase("null"))
                    br.setAfterMonth(" ");
                else
                    br.setAfterMonth(afterMonth);
                //br.setBeforeMonth(cur.getString(cur.getColumnIndex("BeforeMonth")));
                if (beforeMonth.equalsIgnoreCase("null"))
                    br.setBeforeMonth(" ");
                else
                    br.setBeforeMonth(beforeMonth);


                if (onMonth.equalsIgnoreCase("null"))
                    br.setOnMonth(" ");
                else
                    br.setOnMonth(onMonth);


                arrayVactbl.add(br);


            } while (cur.moveToNext());
        }
        db.close();
        return arrayVactbl;
    }

    public List<String> selectAllVaccineTableHeader() {
        ArrayList<Bean_VaccineTable> arrayVactbl = new ArrayList<Bean_VaccineTable>();
        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "Select VaccineID,MST_VaccineDetails.VaccineName,DoseNo,AfterMonth,BeforeMonth,OnMonth \n" +
                "        from MST_VaccineTable inner join MST_VaccineDetails On MST_VaccineTable.VaccineDetailId =MST_VaccineDetails.VaccineDetailId  group by VaccineName";
        List listDataHeader = new ArrayList<String>();
        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {


              listDataHeader.add(cur.getString(cur.getColumnIndex("VaccineName")));


            } while (cur.moveToNext());
        }
        db.close();
        return listDataHeader;
    }

    public ArrayList<Bean_VaccineTable> selectAllVaccineTableChildData(String name) {
        ArrayList<Bean_VaccineTable> arrayVactbl = new ArrayList<Bean_VaccineTable>();
        SQLiteDatabase db = getReadableDatabase();

        String strQuery = "Select VaccineID,MST_VaccineDetails.VaccineName,DoseNo,AfterMonth,BeforeMonth,OnMonth,MST_VaccineDetails.VaccineDetailId \n" +
                "        from MST_VaccineTable inner join MST_VaccineDetails " +
                "On MST_VaccineTable.VaccineDetailId =MST_VaccineDetails.VaccineDetailId " +
                " where VaccineName='"+name+"'";
        ArrayList<Bean_VaccineTable> listDataChild = new ArrayList<Bean_VaccineTable>();

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                String onMonth = cur.getString(cur.getColumnIndex("OnMonth")) + "";
                String afterMonth = cur.getString(cur.getColumnIndex("AfterMonth")) + "";
                String beforeMonth = cur.getString(cur.getColumnIndex("BeforeMonth")) + "";
                Bean_VaccineTable br = new Bean_VaccineTable();
                br.setVaccineName(cur.getString(cur.getColumnIndex("VaccineName")));
                br.setDoseNo(cur.getString(cur.getColumnIndex("DoseNo")));
                br.setVaccineID(cur.getInt(cur.getColumnIndex("VaccineID")));
                br.setVaccineDetailId(cur.getInt(cur.getColumnIndex("VaccineDetailId")));
                //br.setAfterMonth(cur.getString(cur.getColumnIndex("AfterMonth")));
                if (afterMonth.equalsIgnoreCase("null"))
                    br.setAfterMonth(" - ");
                else
                    br.setAfterMonth(afterMonth);
                //br.setBeforeMonth(cur.getString(cur.getColumnIndex("BeforeMonth")));
                if (beforeMonth.equalsIgnoreCase("null"))
                    br.setBeforeMonth(" - ");
                else
                    br.setBeforeMonth(beforeMonth);


                if (onMonth.equalsIgnoreCase("null"))
                    br.setOnMonth(" - ");
                else
                    br.setOnMonth(onMonth);


                arrayVactbl.add(br);


            } while (cur.moveToNext());
        }
        db.close();
        return arrayVactbl;

    }


}
