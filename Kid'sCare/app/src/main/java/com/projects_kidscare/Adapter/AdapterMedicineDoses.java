package com.projects_kidscare.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects_kidscare.Bean.Bean_MedicineDoses;
import com.projects_kidscare.R;

import java.util.ArrayList;


public class AdapterMedicineDoses extends BaseAdapter {

    Activity act;
    ArrayList<Bean_MedicineDoses> arrayMed1;

    public AdapterMedicineDoses(Activity act, ArrayList<Bean_MedicineDoses> arrayMed1) {
        this.act = act;
        this.arrayMed1 = arrayMed1;

    }

    @Override
    public int getCount() {
        return arrayMed1.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayMed1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return arrayMed1.get(i).getMedicineID();
    }


    private class ViewHolder {

        TextView tvID;
        TextView tvMedicineName;
        TextView tvMorning;
        TextView etMorning;
        TextView tvAfternoon;
        TextView etAfternoon;
        TextView tvEvening;
        TextView etEvening;
        TextView tvNight;
        TextView etNight;
        TextView tvDoses;
        LinearLayout ll;


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = act.getLayoutInflater();


        final AdapterMedicineDoses.ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_medicinedoses, null);
            viewHolder = new AdapterMedicineDoses.ViewHolder();

            viewHolder.tvMedicineName = (TextView) view.findViewById(R.id.listmed1_tv_medicinename);
            viewHolder.tvMorning = (TextView) view.findViewById(R.id.listmed1_et_morning);
            viewHolder.etMorning = (TextView) view.findViewById(R.id.listmed1_et_morning);
            viewHolder.tvAfternoon = (TextView) view.findViewById(R.id.listmed1_et_afternoon);
            viewHolder.etAfternoon = (TextView) view.findViewById(R.id.listmed1_et_afternoon);
            viewHolder.tvEvening = (TextView) view.findViewById(R.id.listmed1_et_evening);
            viewHolder.etEvening = (TextView) view.findViewById(R.id.listmed1_et_evening);
            viewHolder.tvNight = (TextView) view.findViewById(R.id.listmed1_et_night);
            viewHolder.etNight = (TextView) view.findViewById(R.id.listmed1_et_night);
            viewHolder.tvDoses = (TextView) view.findViewById(R.id.listmed1_tv_doses);
            viewHolder.tvID = (TextView) view.findViewById(R.id.listmed1_tv_id);
            viewHolder.ll = (LinearLayout) view.findViewById(R.id.layout_main);


            view.setTag(viewHolder);

        } else {
            viewHolder = (AdapterMedicineDoses.ViewHolder) view.getTag();
        }


        viewHolder.tvID.setText(arrayMed1.get(i).getMedicineID() + "");
        viewHolder.tvMedicineName.setText(arrayMed1.get(i).getMedicineName());
        viewHolder.tvMorning.setText("Morning : " + arrayMed1.get(i).getMorning() + "/");
        viewHolder.tvAfternoon.setText("Afternoon : " + arrayMed1.get(i).getAfternoon() + "/");
        viewHolder.tvEvening.setText("Evening : " + arrayMed1.get(i).getAfternoon() + "/");
        viewHolder.tvNight.setText("Night : " + arrayMed1.get(i).getNight());
        if (arrayMed1.get(i).getDoses().equalsIgnoreCase("AfterFood"))
            viewHolder.tvDoses.setText("After Food");
        else
            viewHolder.tvDoses.setText("Before Food");

        if (i % 2 != 1) {
            viewHolder.ll.setBackgroundResource(R.color.white);
        } else {
            viewHolder.ll.setBackgroundResource(R.color.gray);
        }

        return view;
    }

}
