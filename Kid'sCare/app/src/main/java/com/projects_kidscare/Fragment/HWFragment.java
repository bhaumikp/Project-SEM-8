package com.projects_kidscare.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.projects_kidscare.Adapter.AdapterHW;
import com.projects_kidscare.Bean.Bean_HW;
import com.projects_kidscare.DBHelper.DB_HW;
import com.projects_kidscare.Design.AddHWActivity;
import com.projects_kidscare.Design.GraphActivity;
import com.projects_kidscare.Design.IdealTableActivity;
import com.projects_kidscare.Design.MainActivity;
import com.projects_kidscare.R;
import com.projects_kidscare.Utility.Constant;

import java.util.ArrayList;

public class HWFragment extends Fragment {
    LinearLayout btnAddnew;
    LinearLayout btnIdealTable;
    LinearLayout btnGraph;
    ListView lst;
    DB_HW dbhw;
    ArrayList<Bean_HW> arrayHW;
    ArrayList<Bean_HW> arrayHWTemp;
    int HWID = 0;
    int RegID = 0;
    String strFrom = "";
    Activity act;
    int p;
    String ID;


    public HWFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_hw, container, false);
        act = getActivity();

        RegID = Integer.parseInt(act.getIntent().getStringExtra("RegID"));
        Constant.RegIdNew = RegID;
        lst = (ListView) view.findViewById(R.id.hw_lst);
        dbhw = new DB_HW(getActivity());
        strFrom = getActivity().getIntent().getStringExtra("From");
        arrayHW = dbhw.selectBYHW(RegID);
        registerForContextMenu(lst);


        if(arrayHW.size()==0)
        {

            Intent in = new Intent(getActivity(), AddHWActivity.class);
            in.putExtra("RegID", RegID);
            in.putExtra("Update", 0);
            startActivity(in);

        }else lst.setAdapter(new AdapterHW(getActivity(), arrayHW));


        btnAddnew = (LinearLayout) view.findViewById(R.id.hw_btn_addnew);
        btnAddnew.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in = new Intent(getActivity(), AddHWActivity.class);
                        in.putExtra("RegID", RegID);
                        in.putExtra("Update", 0);
                        startActivity(in);
                    }
                });

        btnIdealTable = (LinearLayout) view.findViewById(R.id.hw_btn_idealtable);
        btnIdealTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getActivity(), IdealTableActivity.class);

                startActivity(in);
            }
        });


        btnGraph = (LinearLayout) view.findViewById(R.id.hw_btn_graph);
        btnGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), GraphActivity.class);
                in.putExtra("RegID", Constant.RegIdNew);
                startActivity(in);
            }
        });


        MainActivity activity = (MainActivity) getActivity();
        ID = activity.getMyData();

        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView tvId = (TextView) view.findViewById(R.id.listhw_tv_id);
                RegID = Integer.parseInt(tvId.getText().toString());

                return false;
            }
        });


        return view;

    }


    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        menu.add("Edit");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final String str = item.getTitle().toString();
        MainActivity activity = (MainActivity) getActivity();
        final String ID = activity.getMyData();


        if (str.equalsIgnoreCase("Edit")) {
            Intent in = new Intent(getActivity(), AddHWActivity.class);
            in.putExtra("RegID", RegID);
            in.putExtra("Update", 1);
            startActivity(in);
        } else {
            AlertDialog Yes = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(),
                    android.R.style.Theme_Holo_Light_Dialog))
                    .setIcon(android.R.drawable.ic_dialog_alert)

                    .setMessage("Are you sure want to Delete")
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {

                                    dbhw.delete(RegID, Integer.parseInt(ID));
                                    arrayHW = dbhw.selectBYHW(Integer.parseInt(ID));
                                    lst.setAdapter(new AdapterHW(getActivity(), arrayHW));
                                }
                            }).setNegativeButton("No",
                            null).show();
        }
        return true;
    }


    public void onResume() {
        super.onResume();


        arrayHW = dbhw.selectBYHW(Integer.parseInt(ID));
        lst.setAdapter(new AdapterHW(getActivity(), arrayHW));


    }




}

