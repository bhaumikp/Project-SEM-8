package com.projects_kidscare.kidscare.Design;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.projects_kidscare.Adapter.ExpandableListAdapter;
import com.projects_kidscare.Bean.Bean_VaccineTable;
import com.projects_kidscare.DBHelper.DB_VaccineTable;
import com.projects_kidscare.Design.VaccineDetailActivity;
import com.projects_kidscare.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<Bean_VaccineTable>> listDataChild, listDataChild1, listDataChild2;
    int Vacid = 0;
    DB_VaccineTable dbVaccineTable;
    List<String> a[];
    TextView textbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Vaccine Table");

        setContentView(R.layout.activity_expandable_list_view);


        dbVaccineTable = new DB_VaccineTable(this);


        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Intent in=new Intent(getApplication(),VaccineDetailActivity.class);
                in.putExtra("VaccineDetailId",listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getVaccineDetailId());
                in.putExtra("VaccineName",listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getVaccineName());
                startActivity(in);

                return false;

            }
        });



     /*   expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });*/


    }

    /*
     * Preparing the list data
     */

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Bean_VaccineTable>>();


        // Adding child data

        listDataHeader = dbVaccineTable.selectAllVaccineTableHeader();


        // Adding child data

        for (int i = 0; i < listDataHeader.size(); i++) {

            ArrayList<Bean_VaccineTable> a = new ArrayList<Bean_VaccineTable>();
            a = dbVaccineTable.selectAllVaccineTableChildData(listDataHeader.get(i));

            List<Bean_VaccineTable> b = new ArrayList<Bean_VaccineTable>();

            //Toast.makeText(getApplicationContext(),"Size="+i+"="+a.size(),Toast.LENGTH_SHORT).show();

            for (int j = 0; j < a.size(); j++) {
                b.add(a.get(j));

            }


            listDataChild.put(listDataHeader.get(i), b);
        }


    }

    public void myDataPrepare() {

    }
}