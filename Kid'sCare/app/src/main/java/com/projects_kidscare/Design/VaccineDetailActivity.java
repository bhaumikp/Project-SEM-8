package com.projects_kidscare.Design;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.projects_kidscare.Bean.Bean_VaccineTable;
import com.projects_kidscare.DBHelper.DB_VaccineDetail;
import com.projects_kidscare.R;

public class VaccineDetailActivity extends AppCompatActivity {


    Bean_VaccineTable arrayvaccine;
    DB_VaccineDetail dbvd;
    Activity act;
    TextView tvLawName;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_detail);
        //setTitle("Details");

        setTitle(getIntent().getStringExtra("VaccineName"));

        act = this;
        dbvd = new DB_VaccineDetail(this);

        int id = getIntent().getIntExtra("VaccineDetailId",0);

       // Log.d("heloo","VaccineDetailId"+id);
     arrayvaccine = dbvd.getallvaccineInfo(id);
  //      id=Integer.parseInt(getIntent().getStringExtra("VaccineDetailId"));

        WebView wvLawDetail = (WebView) findViewById(R.id.webview_vac_detail);

        String text;
        text = "<html><body  style=\"text-align:justify;\">";
        text += (arrayvaccine.getDescription());
        text += "</body></html>";
        wvLawDetail.loadData(text, "text/html", "UTF-8");




    }


}
