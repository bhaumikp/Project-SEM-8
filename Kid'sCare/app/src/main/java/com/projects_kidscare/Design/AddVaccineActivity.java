package com.projects_kidscare.Design;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.projects_kidscare.Bean.Bean_Vaccine;
import com.projects_kidscare.DBHelper.DB_Vaccine;
import com.projects_kidscare.DBHelper.DB_VaccineSedualTable;
import com.projects_kidscare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddVaccineActivity extends AppCompatActivity {


    EditText etVaccineName;
    EditText etDoseNo;
    EditText etDate;
    Button btnSave;
    Button btnCancel;
    DB_Vaccine dbv;
    Bean_Vaccine bv;
    DB_VaccineSedualTable dbVaccineSedualTable;
    String strFrom = "";

    DatePickerDialog dp;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);
        setTitle("Add Vaccine");

        sdf = new SimpleDateFormat("dd/MM/yyyy");
        setDateTimeField();
        init();


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.show();
            }
        });

        bv = new Bean_Vaccine();
        strFrom = getIntent().getStringExtra("regid");
        dbv = new DB_Vaccine(this);


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


                if (etVaccineName.getText().length() > 0)
                    bv.setVaccineName(etVaccineName.getText().toString());

                else {
                    flag = 1;
                    etVaccineName.setError("Enter VaccineName");
                }

                if (etDoseNo.getText().length() > 0)
                    bv.setDoseNo(etDoseNo.getText().toString());

                else {
                    flag = 1;
                    etDoseNo.setError("Enter DoseNo");
                }

                if (etDate.getText().length() > 0) {
                    bv.setDate(etDate.getText().toString());

                } else {
                    flag = 1;
                    Toast.makeText(AddVaccineActivity.this, "Select Date", Toast.LENGTH_LONG).show();
                }

                if (flag == 0) {
                    bv.setRegID(Integer.parseInt(strFrom));
                    dbVaccineSedualTable.insertDetail(bv);
                    clear();
                    finish();
                    Toast.makeText(AddVaccineActivity.this, "Record Saved", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    void init() {
        etVaccineName = (EditText) findViewById(R.id.vac_et_vacname);
        etDoseNo = (EditText) findViewById(R.id.vac_et_doseno);
        etDate = (EditText) findViewById(R.id.vac_tv_date);
        btnCancel = (Button) findViewById(R.id.vac_btn_cancel);
        btnSave = (Button) findViewById(R.id.vac_btn_save);
        dbVaccineSedualTable = new DB_VaccineSedualTable(this);


        Calendar c = Calendar.getInstance();
        etDate.setText(sdf.format(c.getTime()));
    }

    void clear() {
        etVaccineName.setText("");
        etDoseNo.setText("");
        etDate.setText("");

    }


    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        dp = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etDate.setText(sdf.format(newDate.getTime()));
                Date currentDate = new Date();

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DATE));

    }

}
