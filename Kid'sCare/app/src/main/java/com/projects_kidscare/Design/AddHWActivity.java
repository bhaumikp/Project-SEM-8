package com.projects_kidscare.Design;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.projects_kidscare.Bean.Bean_HW;
import com.projects_kidscare.DBHelper.DB_HW;
import com.projects_kidscare.DBHelper.DB_Registration;
import com.projects_kidscare.R;
import com.projects_kidscare.Utility.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddHWActivity extends AppCompatActivity {

    EditText etHeightft;
    EditText etHeightin;
    EditText etWeight;
    EditText etAgeyr;
    LinearLayout ll;
    EditText etAgem;
    EditText etDate;
    Button btnSave;
    Button btnCancel;
    DB_HW dbhw;
    DB_Registration dbr;
    Bean_HW bhw;
    DatePickerDialog dp;
    SimpleDateFormat sdf;
    int mID, update;
    String DOB = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hw);
        setTitle("Add Height Weight");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etDate = (EditText) findViewById(R.id.hw_et_date);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        dbr = new DB_Registration(this);
        setDateTimeField();
        bhw = new Bean_HW();

        dbhw = new DB_HW(this);
        try {
            init();
        } catch (ParseException e) {
            e.printStackTrace();
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


                if (etHeightft.getText().length() > 0)
                    bhw.setHeightFt(etHeightft.getText().toString());

                else {
                    flag = 1;
                    etHeightft.setError("Enter Height");
                }
                if (etHeightin.getText().length() > 0)
                    bhw.setHeightIn(etHeightin.getText().toString());

                else {
                    flag = 1;
                    etHeightin.setError("Enter Height");
                }
                if (etWeight.getText().length() > 0)
                    bhw.setWeight(etWeight.getText().toString());

                else {
                    flag = 1;
                    etWeight.setError("Enter Weight");
                }
                if (etAgeyr.getText().length() > 0)
                    bhw.setAgeYr(etAgeyr.getText().toString());

                else {
                    flag = 1;
                    etAgeyr.setError("Enter Age");
                }
                if (etAgem.getText().length() > 0)
                    bhw.setAgeM(etAgem.getText().toString());

                else {
                    flag = 1;
                    etAgem.setError("Enter Age");
                }

                if (etDate.getText().length() > 0) {
                    bhw.setDate(etDate.getText().toString());

                } else {

                    etDate.setError("Please Select Date");
                    flag = 1;

                }


                if (flag == 0) {
                    bhw.setRegID(mID);

                    if (update == 1) {
                        dbhw.updateHWDetail(bhw);
                        Toast.makeText(AddHWActivity.this, "Record Updated", Toast.LENGTH_LONG).show();
                    } else {
                        dbhw.insertHWDetail(bhw);
                        Toast.makeText(AddHWActivity.this, "Record Saved", Toast.LENGTH_LONG).show();
                    }


                    clear();
                    finish();

                }
            }
        });


    }

    private String getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }

    void init() throws ParseException {

        mID = getIntent().getIntExtra("RegID", 0);
        update = getIntent().getIntExtra("Update", 0);

        ll = (LinearLayout) findViewById(R.id.layout_add_hw);
        ll.setAlpha((float) 0.8);


        etHeightft = (EditText) findViewById(R.id.hw_et_ft);
        etHeightin = (EditText) findViewById(R.id.hw_et_inch);
        etWeight = (EditText) findViewById(R.id.hw_et_weight);
        etAgeyr = (EditText) findViewById(R.id.hw_et_yr);
        etAgem = (EditText) findViewById(R.id.hw_et_month);
        etDate = (EditText) findViewById(R.id.hw_et_date);
        btnCancel = (Button) findViewById(R.id.hw_btn_clear);
        btnSave = (Button) findViewById(R.id.hw_btn_save);
        DOB = dbr.selectDOBReg(Constant.RegId);

        Calendar dob = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        Date dd = sdf.parse(DOB);
        dob.setTime(dd);


        Calendar c = Calendar.getInstance();
        etDate.setText(sdf.format(c.getTime()));

        if (update == 1) {
            btnSave.setText("Update");
            bhw = dbhw.selectbyHwID(mID);

            etAgeyr.setText(bhw.getAgeYr());
            etAgem.setText(bhw.getAgeM());
            etWeight.setText(bhw.getWeight());
            etHeightin.setText(bhw.getHeightIn());
            etHeightft.setText(bhw.getHeightFt());
            etDate.setText(bhw.getDate());
        }
    }

    void clear() {
        etHeightft.setText("");
        etHeightin.setText("");
        etWeight.setText("");
        etAgeyr.setText("");
        etAgem.setText("");
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
