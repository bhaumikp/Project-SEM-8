package com.projects_kidscare.Design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.projects_kidscare.Bean.Bean_MedicineDoses;
import com.projects_kidscare.DBHelper.DB_MedicineDoses;
import com.projects_kidscare.R;

public class AddMedicineDosesActivity extends AppCompatActivity {

    EditText etMedicineName;
    EditText etMorning;
    EditText etAfternoon;
    EditText etEvening;
    EditText etNight;
    RadioButton rbAfterFood;
    RadioButton rbBeforeFood;
    TextView tv;
    Button btnSave;
    Button btnCancel;
    DB_MedicineDoses dbmed1;
    Bean_MedicineDoses bmed1;
    String strFrom = "";
    int disid=0;
    String st;
    int update,medID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine_doses);

        setTitle("Add MedicineDoses");


        init();
        bmed1 = new Bean_MedicineDoses();
        dbmed1 = new DB_MedicineDoses(this);
        strFrom = getIntent().getStringExtra("From");
        st = getIntent().getStringExtra("DisID");
       // Log.d("heloo",disid+"");



      //  st = getIntent().getStringExtra("DisID");



      /*  if (strFrom.equalsIgnoreCase("Edit")) {

            //btnSave.setText("Update");
            int MedID=getIntent().getIntExtra("MedicineID", 0);
            bmed1= dbmed1.selectbyMedicineDoseID(MedID);

            etMedicineName.setText(bmed1.getMedicineName());
            etMorning.setText(bmed1.getMorning());
            etAfternoon.setText(bmed1.getAfternoon());
            etEvening.setText(bmed1.getEvening());
            etNight.setText(bmed1.getNight());

            if (bmed1.getDoses().equals("AfterFood"))
                rbAfterFood.setChecked(true);
            else
                rbBeforeFood.setChecked(true);
        }*/

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int flag = 0;

                if (etMedicineName.getText().length() > 0)
                    bmed1.setMedicineName(etMedicineName.getText().toString());

                else {
                    flag = 1;
                    etMedicineName.setError("Enter MedicineName");
                }

                if (etMorning.getText().length() > 0)
                    bmed1.setMorning(etMorning.getText().toString());

                else {
                    flag = 1;
                    etMorning.setError("Enter Dose For Morning");
                }
                if (etAfternoon.getText().length() > 0)
                    bmed1.setAfternoon(etAfternoon.getText().toString());

                else {
                    flag = 1;
                    etAfternoon.setError("Enter Dose For Afternoon ");
                }
                if (etEvening.getText().length() > 0)
                    bmed1.setEvening(etEvening.getText().toString());

                else {
                    flag = 1;
                    etAfternoon.setError("Enter Dose For Afternoon ");
                }
                if (etNight.getText().length() > 0)
                    bmed1.setNight(etNight.getText().toString());

                else {
                    flag = 1;
                    etNight.setError("Enter Dose For Night");
                }

                if (rbAfterFood.isChecked())
                    bmed1.setDoses("AfterFood");
                else {
                    bmed1.setDoses("BeforeFood");
                }
               if (flag == 0) {

                   bmed1.setDiseasesID(Integer.parseInt(st));
                   dbmed1.insertDetailMedicineDoses(bmed1, strFrom);
                   finish();
                   Toast.makeText(AddMedicineDosesActivity.this, "Record Saved", Toast.LENGTH_LONG).show();


                    finish();
                }
            }
        });


    }

    void init() {

       // disid = getIntent().getIntExtra("DisID", 0);
       // update = getIntent().getIntExtra("Update", 0);

        etMedicineName = (EditText) findViewById(R.id.med_et_medicine);
        etMorning = (EditText) findViewById(R.id.med_et_morning);
        etAfternoon = (EditText) findViewById(R.id.med_et_afternoon);
        etEvening = (EditText) findViewById(R.id.med_et_evening);
        etNight = (EditText) findViewById(R.id.med_et_night);
        rbAfterFood = (RadioButton) findViewById(R.id.med_rb_afterfood);
        rbBeforeFood = (RadioButton) findViewById(R.id.med_rb_beforefood);
        btnCancel = (Button) findViewById(R.id.meddose_btn_clear);
        btnSave = (Button) findViewById(R.id.meddose_btn_save);


    }
    void clear() {
        etMedicineName.setText("");
        etMorning.setText("");
        etAfternoon.setText("");
        etNight.setText("");

    }

}

