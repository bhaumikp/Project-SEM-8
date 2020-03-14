package com.projects_kidscare.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.projects_kidscare.Adapter.AdapterVaccineFragment;
import com.projects_kidscare.Adapter.AdapterVaccineSedual;
import com.projects_kidscare.Bean.Bean_Vaccine;
import com.projects_kidscare.Bean.Bean_VaccineTable;
import com.projects_kidscare.Bean.Bean_Vaccine_Sedual;
import com.projects_kidscare.DBHelper.DB_VaccineSedualTable;
import com.projects_kidscare.Design.AddVaccineActivity;
import com.projects_kidscare.R;
import com.projects_kidscare.kidscare.Design.ExpandableListViewActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class VaccineFragment extends Fragment {

    LinearLayout btnAddnew;
    LinearLayout btnVaccineTable;


    DB_VaccineSedualTable db_vst;
    public static ListView lst;
    public static RecyclerView recyclerView;
    public static ArrayList<Bean_Vaccine_Sedual> arrayVaccineSedual;
    Bean_Vaccine bv;
    ArrayList<Bean_VaccineTable> arrayVaccineTemp;
    int VaccineID = 0;
    int RegID = 0;
    String strFrom = "";
    public static AdapterVaccineFragment adapterVaccineFragment;
    Activity act;
    EditText etDate;
    DatePickerDialog dp;
    SimpleDateFormat sdf;

    public VaccineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vaccine, container, false);
        act = getActivity();


        RegID = Integer.parseInt(act.getIntent().getStringExtra("RegID"));


        lst = (ListView) view.findViewById(R.id.Vaccine_lst);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        db_vst = new DB_VaccineSedualTable(getContext());
        arrayVaccineSedual = db_vst.selectBY(RegID);
        adapterVaccineFragment = new AdapterVaccineFragment(getActivity());
        strFrom = getActivity().getIntent().getStringExtra("From");
        registerForContextMenu(lst);
        lst.setAdapter(new AdapterVaccineSedual(getActivity(), arrayVaccineSedual));

//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapterVaccineFragment);

        btnAddnew = (LinearLayout) view.findViewById(R.id.vac_btn_addnew);
        btnAddnew.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in = new Intent(getActivity(), AddVaccineActivity.class);
                        in.putExtra("regid", RegID + "");
                        startActivity(in);
                    }
                });

        btnVaccineTable = (LinearLayout) view.findViewById(R.id.vac_btn_vaccinetable);
        btnVaccineTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getActivity(), ExpandableListViewActivity.class);
                startActivity(in);
            }
        });


        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView tvId = (TextView) view.findViewById(R.id.listvac_tv_id);
                RegID = Integer.parseInt(tvId.getText().toString());
                return false;
            }
        });


        return view;
    }

    @Override
    public void onResume() {


        adapterVaccineFragment = new AdapterVaccineFragment(getActivity());
        lst.setAdapter(new AdapterVaccineSedual(getActivity(), arrayVaccineSedual));
        lst.invalidate();
        adapterVaccineFragment.notifyDataSetChanged();
        super.onResume();
    }


}

