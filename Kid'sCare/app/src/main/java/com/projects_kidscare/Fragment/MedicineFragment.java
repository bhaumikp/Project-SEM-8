package com.projects_kidscare.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.projects_kidscare.Adapter.AdapterMedicine;
import com.projects_kidscare.Bean.Bean_DosesMedicine;
import com.projects_kidscare.Bean.Bean_Medicine;
import com.projects_kidscare.DBHelper.DB_Medicine;
import com.projects_kidscare.Design.AddMedicineActivity;
import com.projects_kidscare.Design.MedicineDoseActivity;
import com.projects_kidscare.Design.MainActivity;
import com.projects_kidscare.R;

import java.util.ArrayList;


public class MedicineFragment extends Fragment {
    FloatingActionButton btnAddnew;
    TextView tvID;
    ListView lst;
    DB_Medicine dbmed;
    ArrayList<Bean_DosesMedicine> arrayMed;
    Bean_Medicine bmed;
    ArrayList<Bean_Medicine> arrayMedTemp;
    int DiseasesID = 0;
    int RegID = 0;
    String strFrom = "";
    Activity act;

    public MedicineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);
        act = getActivity();

        RegID = Integer.parseInt(act.getIntent().getStringExtra("RegID"));
        lst = (ListView) view.findViewById(R.id.med_lst);
        dbmed = new DB_Medicine(getContext());
        arrayMedTemp = dbmed.selectBYMedicine(RegID);
        strFrom = getActivity().getIntent().getStringExtra("From");
        registerForContextMenu(lst);
       // lst.setAdapter(new AdapterMedicine(getActivity(), arrayMedTemp));


        btnAddnew = (FloatingActionButton) view.findViewById(R.id.fab_add);
        btnAddnew.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in = new Intent(getActivity(), AddMedicineActivity.class);
                        in.putExtra("RegID", RegID + "");
                        in.putExtra("Update", "0");
                        in.putExtra("DisID", "");
                        startActivity(in);
                    }
                });


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent in = new Intent(getActivity(), MedicineDoseActivity.class);
                in.putExtra("DisID", arrayMedTemp.get(position).getDiseasesID() + "");
                TextView tvId = (TextView) view.findViewById(R.id.listmed_tv_id);
                DiseasesID = Integer.parseInt(tvId.getText().toString());
                startActivity(in);


            }
        });

        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView tvId = (TextView) view.findViewById(R.id.listmed_tv_id);
                DiseasesID = Integer.parseInt(tvId.getText().toString());
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
            Intent in = new Intent(getActivity(), AddMedicineActivity.class);
            in.putExtra("DisID", DiseasesID + "");
            in.putExtra("Update", 1 + "");
            in.putExtra("RegID", RegID + "");
           // Toast.makeText(getContext(), "YY=" + DiseasesID, Toast.LENGTH_SHORT).show();

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

                                    dbmed.deleteMedicine(DiseasesID, RegID);
                                    arrayMedTemp = dbmed.selectBYMedicine(Integer.parseInt(ID));
                                    lst.setAdapter(new AdapterMedicine(getActivity(), arrayMedTemp));

                                }
                            }).setNegativeButton("No",
                            null).show();
        }
        return true;
    }


    @Override
    public void onResume() {
        super.onResume();

        arrayMedTemp = dbmed.selectBYMedicine(RegID);
        lst.setAdapter(new AdapterMedicine(getActivity(), arrayMedTemp));

    }
}
