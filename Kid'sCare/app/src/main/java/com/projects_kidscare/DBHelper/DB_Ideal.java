package com.projects_kidscare.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projects_kidscare.Bean.Bean_Ideal;
import com.projects_kidscare.Utility.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;


public class DB_Ideal extends SQLiteAssetHelper {
    public DB_Ideal(Context context) {
        super(context, Constant.DB_Name, null, Constant.DB_Version);
    }





    public ArrayList<Bean_Ideal> selectHWIdealAll() {
        ArrayList<Bean_Ideal> arrayIdeal = new ArrayList<Bean_Ideal>();
        SQLiteDatabase db = getReadableDatabase();


        String strQuery = "Select * from MST_Ideal";

        Cursor cur = db.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                Bean_Ideal br = new Bean_Ideal();
                br.setIdealID(cur.getInt(cur.getColumnIndex("IdealID")));
                br.setHeight(cur.getString(cur.getColumnIndex("Height")));
                br.setWeight(cur.getString(cur.getColumnIndex("Weight")));
                br.setAge(cur.getString(cur.getColumnIndex("Age")));

                arrayIdeal.add(br);


            } while (cur.moveToNext());
        }
        db.close();
        return arrayIdeal;
    }


}
