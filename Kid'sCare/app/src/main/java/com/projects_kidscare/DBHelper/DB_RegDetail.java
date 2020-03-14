package com.projects_kidscare.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import com.projects_kidscare.Bean.Bean_Registration;
import com.projects_kidscare.Utility.Constant;



public class DB_RegDetail extends SQLiteAssetHelper {
    public DB_RegDetail(Context context) {
        super(context, Constant.DB_Name, null, Constant.DB_Version);
    }

    public Bean_Registration selectByRegID(int id) {

        Bean_Registration bm = new Bean_Registration();
        SQLiteDatabase db = getReadableDatabase();
        String query = "Select * from MST_Registration where RegID=" + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())
            bm.setName(cursor.getString(cursor.getColumnIndex("Name")));
        bm.setRegID(cursor.getInt(cursor.getColumnIndex("RegID")));

        db.close();
        return bm;


    }


}
