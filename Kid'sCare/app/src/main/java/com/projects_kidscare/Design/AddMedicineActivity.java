package com.projects_kidscare.Design;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.projects_kidscare.Bean.Bean_Medicine;
import com.projects_kidscare.DBHelper.DB_Medicine;
import com.projects_kidscare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddMedicineActivity extends AppCompatActivity {

    EditText etDiseasesName;
    EditText etDate;
    Button btnSave;
    Button btnCancel;
    DB_Medicine dbmed;
    Bean_Medicine bmed;
    String strFrom = "";
    DatePickerDialog dp;
    SimpleDateFormat sdf;
    int mID, update, regid;
    String st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);
        setTitle("Add Medicine");

        init();
        setDateTimeField();

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.show();
            }
        });


        strFrom = getIntent().getStringExtra("From");
        st = getIntent().getStringExtra("DisID");
        update = Integer.parseInt(getIntent().getStringExtra("Update"));
        regid = Integer.parseInt(getIntent().getStringExtra("RegID"));

        bmed = new Bean_Medicine();
        dbmed = new DB_Medicine(this);


        if (update == 1) {
            mID = Integer.parseInt(st);
            bmed = dbmed.selectBYMedicineID(mID);
            btnSave.setText("UPDATE");
            etDiseasesName.setText(bmed.getDiseasesName().toString());
            etDate.setText(bmed.getDate());
        }


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.show();
            }
        });


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

                if (etDiseasesName.getText().length() > 0)
                    bmed.setDiseasesName(etDiseasesName.getText().toString() + "");

                else {
                    flag = 1;
                    etDiseasesName.setError("Enter DiseasesName");
                }
                if (etDate.getText().length() > 0) {
                    bmed.setDate(etDate.getText().toString());

                } else {
                    flag = 1;
                    Toast.makeText(AddMedicineActivity.this, "Select Date ", Toast.LENGTH_LONG).show();
                }
                if (flag == 0) {

                    bmed.setRegID(regid);
                    if (update == 1) {
                        bmed.setDiseasesID(Integer.parseInt(st));
                        dbmed.updateMedicineDetail(bmed);
                        Toast.makeText(AddMedicineActivity.this, "Record Updated", Toast.LENGTH_LONG).show();
                    } else {
                        dbmed.insertMedicineDetail(bmed);
                        Toast.makeText(AddMedicineActivity.this, "Record Saved", Toast.LENGTH_LONG).show();
                    }


                    clear();
                    finish();


                }
            }
        });


    }

    void init() {
        etDiseasesName = (EditText) findViewById(R.id.med_et_diseases);
        etDate = (EditText) findViewById(R.id.med_et_date);
        btnCancel = (Button) findViewById(R.id.med_btn_clear);
        btnSave = (Button) findViewById(R.id.med_btn_save);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        etDate.setText(sdf.format(c.getTime()));

        if (update == 1) {
            btnSave.setText("Update");
            bmed = dbmed.selectBYMedicineID(mID);

            etDiseasesName.setText(bmed.getDiseasesName());
            etDate.setText(bmed.getDate());
        }
    }

    void clear() {
        etDiseasesName.setText("");
        etDate.setText("");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

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
