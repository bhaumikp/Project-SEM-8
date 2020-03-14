package com.projects_kidscare.Design;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.projects_kidscare.Adapter.AdapterMedicineDoses;
import com.projects_kidscare.Bean.Bean_MedicineDoses;
import com.projects_kidscare.DBHelper.DB_MedicineDoses;
import com.projects_kidscare.R;

import java.util.ArrayList;

public class MedicineDoseActivity extends AppCompatActivity {

    ListView lst;
    DB_MedicineDoses dbmed1;
    ArrayList<Bean_MedicineDoses> arrayMed1;
    Bean_MedicineDoses bmed1;
    ArrayList<Bean_MedicineDoses> arrayMedTemp1;
    String strFrom = "";
    Activity act;
    int Medid;
    int disid=0;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_dose);
        act = this;

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle("Add Medicine");

        disid=Integer.parseInt(act.getIntent().getStringExtra("DisID"));
        lst = (ListView) findViewById(R.id.med1_lst);
        fab=(FloatingActionButton)findViewById(R.id.fab_addmedicine);
        registerForContextMenu(lst);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MedicineDoseActivity.this, AddMedicineDosesActivity.class);
                in.putExtra("From", "New");
                in.putExtra("DisID",disid+"");
                startActivity(in);
            }
        });

        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView tvID=(TextView)view.findViewById(R.id.listmed1_tv_id);
                Medid=Integer.parseInt(tvID.getText().toString());
                return false;
            }
        });



       dbmed1 = new DB_MedicineDoses(this);
        arrayMed1 = dbmed1.selectAllMedicineDoses(disid);

        lst.setAdapter(new AdapterMedicineDoses(MedicineDoseActivity.this, arrayMed1));


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add("Edit");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String str=item.getTitle().toString();

        if (str.equalsIgnoreCase("Edit"))
        {
            Intent in=new Intent(MedicineDoseActivity.this,AddMedicineDosesActivity.class);
            in.putExtra("MedicineID",disid );
            // in.putExtra("Update", 1 );
            in.putExtra("From","Edit");
            // in.putExtra("DiaseasesID", DiaseasesID + "");

            startActivity(in);
        }
        else
        {
            AlertDialog Yes = new AlertDialog.Builder(new ContextThemeWrapper(MedicineDoseActivity.this,
                    android.R.style.Theme_Holo_Light_Dialog))
                    .setIcon(android.R.drawable.ic_dialog_alert)

                    .setMessage("Are you sure want to Delete")
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {

                                    dbmed1.deleteMedicineDoses(Medid,disid);
                                    arrayMed1=dbmed1.selectAllMedicineDoses(disid);
                                    lst.setAdapter(new AdapterMedicineDoses(MedicineDoseActivity.this,arrayMed1));



                                }
                            }).setNegativeButton("No",
                            null).show();
        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        arrayMed1 = dbmed1.selectAllMedicineDoses(disid);
        lst.setAdapter(new AdapterMedicineDoses(MedicineDoseActivity.this, arrayMed1));
    }
}
