package com.projects_kidscare.Design;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.projects_kidscare.Adapter.AdapterIdealTable;
import com.projects_kidscare.Bean.Bean_Ideal;
import com.projects_kidscare.Bean.Bean_Registration;
import com.projects_kidscare.DBHelper.DB_Ideal;
import com.projects_kidscare.R;

import java.util.ArrayList;

public class IdealTableActivity extends AppCompatActivity {


    Bean_Registration br;
    DB_Ideal dbI;
    Button btnCancle;
    ListView lst;
    ArrayList<Bean_Ideal> arrayIdeal;
    ArrayList<Bean_Ideal> arrayIdealTemp;
    AdapterIdealTable ai;
    Activity act;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_table);
        setTitle("Ideal Height-Weight With Age");

        lst = (ListView) findViewById(R.id.ideal_lst);

        dbI = new DB_Ideal(this);
        arrayIdeal = dbI.selectHWIdealAll();
        ai = new AdapterIdealTable(this, arrayIdeal);

        lst.setAdapter(ai);
        lst.setAdapter(new AdapterIdealTable(IdealTableActivity.this, arrayIdeal));


    }

}




