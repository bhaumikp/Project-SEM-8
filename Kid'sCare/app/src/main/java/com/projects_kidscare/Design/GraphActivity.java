package com.projects_kidscare.Design;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projects_kidscare.Bean.Bean_HW;
import com.projects_kidscare.Bean.Bean_Ideal;
import com.projects_kidscare.DBHelper.DB_HW;
import com.projects_kidscare.DBHelper.DB_Ideal;
import com.projects_kidscare.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    DB_Ideal dbIdeal;
    ArrayList<Bean_Ideal> bean_ideals;
    ArrayList<Bean_Ideal> bean_ideals_new;
    ArrayList<Bean_Ideal> bean_ideals_second;
    BarEntry v1e[];
    BarEntry v2e[];
    float a[];
    BarChart chart;
    BarData data;
    View v1, v2;
    BarDataSet barDataSet1, barDataSet2;
    Button btn_height, btn_weight;
    DB_HW db_hw;
    ArrayList<Bean_HW> bean_hws;
    ArrayList<Bean_HW> bean_hws_tmp;
    int mID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        setTitle("Ideal vs Actual Graph");

        init();
        v1e = new BarEntry[100];
        v2e = new BarEntry[100];
        a = new float[17];


        data = new BarData(getXAxisValues(), getDataSetHeight());
        chart.setData(data);
        chart.setDescription(" ");
        chart.animateXY(2000, 2000);
        chart.notifyDataSetChanged();
        chart.invalidate();


        btn_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_height.setTextSize(14);
                btn_height.setTypeface(null, Typeface.BOLD_ITALIC);
                btn_weight.setTextSize(14);
                btn_weight.setTypeface(null, Typeface.NORMAL);

                v1.setBackgroundColor(getResources().getColor(R.color.white));
                v2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                BarData data = new BarData(getXAxisValues(), getDataSetHeight());
                chart.setData(data);
                chart.animateXY(2000, 2000);
                chart.invalidate();
            }
        });

        btn_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_weight.setTextSize(14);
                btn_weight.setTypeface(null, Typeface.BOLD_ITALIC);
                btn_height.setTextSize(14);
                btn_height.setTypeface(null, Typeface.NORMAL);

                v2.setBackgroundColor(getResources().getColor(R.color.white));
                v1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                BarData data = new BarData(getXAxisValues(), getDataSetWeight());
                chart.setData(data);
                chart.animateXY(2000, 2000);
                chart.invalidate();
            }
        });

    }

    public void init() {

        v1 = (View) findViewById(R.id.view_1);
        v2 = (View) findViewById(R.id.view_2);

        mID = getIntent().getIntExtra("RegID", 0);

        dbIdeal = new DB_Ideal(this);
        bean_ideals = dbIdeal.selectHWIdealAll();

        btn_height = (Button) findViewById(R.id.btn_graph_height);
        btn_weight = (Button) findViewById(R.id.btn_graph_weight);

        bean_ideals_new = dbIdeal.selectHWIdealAll();
        bean_ideals_second = dbIdeal.selectHWIdealAll();

        db_hw = new DB_HW(this);
        bean_hws = db_hw.selectBYHW(mID);
        //Toast.makeText(getApplicationContext(),"CC="+bean_hws.size(),Toast.LENGTH_SHORT).show();
        bean_hws_tmp = db_hw.selectBYHW(mID);

        chart = (BarChart) findViewById(R.id.chart);
    }

    private ArrayList<BarDataSet> getDataSetHeight() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();


        for (int i = 0; i < bean_ideals.size(); i++) {
            Bean_Ideal bean_ideal1 = new Bean_Ideal();
            bean_ideal1.setAge(bean_ideals.get(i).getAge());
            bean_ideal1.setIdealID(bean_ideals.get(i).getIdealID());
            String str = "";
            str = bean_ideals.get(i).getHeight().replace(" ft ", ".").trim();
            str = str.replace(" in", "");
            str.trim();

            bean_ideal1.setHeight(str);

            str = "";
            str = bean_ideals.get(i).getWeight().replace(" kg", "").trim();
            bean_ideal1.setWeight(str);


            bean_ideals.set(i, bean_ideal1);

        }


        for (int i = 0; i < bean_ideals.size(); i++) {
            //1st column of chart
            v1e[i] = new BarEntry(Float.parseFloat(bean_ideals.get(i).getHeight()), i); // Jan
            valueSet1.add(v1e[i]);

        }

        for (int j = 0; j < bean_hws.size(); j++) {
            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) == 0) {
                if (Integer.parseInt(bean_hws.get(j).getAgeM()) < 3 && Integer.parseInt(bean_hws.get(j).getAgeM()) >= 0) {
                    String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                    a[1] = Float.parseFloat(st);
                }
                if (Integer.parseInt(bean_hws.get(j).getAgeM()) >= 3 && Integer.parseInt(bean_hws.get(j).getAgeM()) < 6) {
                    String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                    a[2] = Float.parseFloat(st);
                }
                if (Integer.parseInt(bean_hws.get(j).getAgeM()) >= 6 && Integer.parseInt(bean_hws.get(j).getAgeM()) >= 9) {
                    String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                    a[3] = Float.parseFloat(st);
                }

            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 0 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 1) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[4] = Float.parseFloat(st);
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 1 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 2) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[5] = Float.parseFloat(st);
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 2 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 3) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[6] = Float.parseFloat(st);
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 3 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 4) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[7] = Float.parseFloat(st);
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 4 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 5) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[8] = Float.parseFloat(st);
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 5 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 6) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[9] = Float.parseFloat(st);
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 6 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 7) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[10] = Float.parseFloat(st);
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 7 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 8) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[11] = Float.parseFloat(st);
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 8 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 9) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[12] = Float.parseFloat(st);
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 9 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 10) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[13] = Float.parseFloat(st);
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 10 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 11) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[14] = Float.parseFloat(st);
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 11 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 12) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[15] = Float.parseFloat(st);
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 12 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 13) {
                String st = bean_hws.get(j).getHeightFt() + "." + bean_hws.get(j).getHeightIn();
                a[16] = Float.parseFloat(st);
            }
            //a[13]=0;
        }

        for (int y = 0; y < bean_ideals.size(); y++) {
            if (a[y] <= 0) {
                a[y] = 0;
            }
        }


        for (int i = 0; i < bean_ideals.size(); i++) {
            //1st column of chart
            v2e[i] = new BarEntry(a[i], i); // Jan
            valueSet2.add(v2e[i]);

        }


        barDataSet1 = new BarDataSet(valueSet1, "Ideal Height");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        barDataSet2 = new BarDataSet(valueSet2, "Actual Height");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<BarDataSet> getDataSetWeight() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();

        for (int i = 0; i < bean_ideals.size(); i++) {
            Bean_Ideal bean_ideal1 = new Bean_Ideal();
            bean_ideal1.setAge(bean_ideals.get(i).getAge());
            bean_ideal1.setIdealID(bean_ideals.get(i).getIdealID());
            String str = "";
            str = bean_ideals.get(i).getHeight().replace(" ft ", ".").trim();
            str = str.replace(" in", "");
            str.trim();

            bean_ideal1.setHeight(str);

            str = "";
            str = bean_ideals.get(i).getWeight().replace(" kg", "").trim();
            bean_ideal1.setWeight(str);


            bean_ideals.set(i, bean_ideal1);

        }


        for (int i = 0; i < bean_ideals.size(); i++) {
            //1st column of chart
            v1e[i] = new BarEntry(Float.parseFloat(bean_ideals.get(i).getWeight()), i); // Jan
            valueSet1.add(v1e[i]);

        }

        for (int j = 0; j < bean_hws.size(); j++) {
            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) == 0) {
                if (Integer.parseInt(bean_hws.get(j).getAgeM()) < 3 && Integer.parseInt(bean_hws.get(j).getAgeM()) >= 0) {
                    a[1] = Float.parseFloat(bean_hws.get(j).getWeight());
                }
                if (Integer.parseInt(bean_hws.get(j).getAgeM()) >= 3 && Integer.parseInt(bean_hws.get(j).getAgeM()) < 6) {
                    a[2] = Float.parseFloat(bean_hws.get(j).getWeight());
                }
                if (Integer.parseInt(bean_hws.get(j).getAgeM()) >= 6 && Integer.parseInt(bean_hws.get(j).getAgeM()) >= 9) {
                    a[3] = Float.parseFloat(bean_hws.get(j).getWeight());
                }

            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 0 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 1) {
                a[4] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 1 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 2) {
                a[5] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 2 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 3) {
                a[6] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 3 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 4) {
                a[7] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 4 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 5) {
                a[8] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 5 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 6) {
                a[9] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 6 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 7) {
                a[10] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 7 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 8) {
                a[11] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 8 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 9) {
                a[12] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 9 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 10) {
                a[13] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 10 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 11) {
                a[14] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 11 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 12) {
                a[15] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            if (Integer.parseInt(bean_hws.get(j).getAgeYr()) > 12 && Integer.parseInt(bean_hws.get(j).getAgeYr()) <= 13) {
                a[16] = Float.parseFloat(bean_hws.get(j).getWeight());
            }

            //a[13]=0;
        }

        for (int y = 0; y < bean_ideals.size(); y++) {
            if (a[y] <= 0) {
                a[y] = 0;
            }
        }


        for (int i = 0; i < bean_ideals.size(); i++) {
            //1st column of chart
            v2e[i] = new BarEntry(a[i], i); // Jan
            valueSet2.add(v2e[i]);

        }


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Ideal Weight");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Actual Weight");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        data.removeDataSet(barDataSet1);
        data.removeDataSet(barDataSet2);
        chart.notifyDataSetChanged();
        chart.invalidate();
    }


    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();

        for (int i = 0; i < bean_ideals_new.size(); i++) {
            xAxis.add(bean_ideals_new.get(i).getAge());
        }

        return xAxis;
    }
}