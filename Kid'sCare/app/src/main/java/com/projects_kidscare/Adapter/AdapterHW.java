package com.projects_kidscare.Adapter;

import android.app.Activity;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects_kidscare.Bean.Bean_HW;
import com.projects_kidscare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class AdapterHW extends BaseAdapter {

    Activity act;
    ArrayList<Bean_HW> arrayHW;

    public AdapterHW(Activity act, ArrayList<Bean_HW> arrayHW) {
        this.act = act;
        this.arrayHW = arrayHW;

    }

    @Override
    public int getCount() {
        return arrayHW.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayHW.get(i);
    }

    @Override
    public long getItemId(int i) {
        return arrayHW.get(i).getHWID();
    }


    private class ViewHolder {

        TextView tvID;
        TextView tvRegID;
        TextView tvWeight;
        TextView tvHeight;
        TextView tvAge;
        TextView tvDate;
        TextView tvDay;
        TextView tvYear;
        TextView tvMonth;
        LinearLayout layout_main,layout_fake;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = act.getLayoutInflater();
        final AdapterHW.ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_hw, null);
            viewHolder = new AdapterHW.ViewHolder();

            viewHolder.tvHeight = (TextView) view.findViewById(R.id.listhw_new_tv_height);
            viewHolder.tvWeight = (TextView) view.findViewById(R.id.listhw_new_tv_Weight);
            viewHolder.tvAge = (TextView) view.findViewById(R.id.listhw_new_tv_age);
            viewHolder.tvDate = (TextView) view.findViewById(R.id.tv_date);
            viewHolder.tvDay = (TextView) view.findViewById(R.id.tv_day);
            viewHolder.tvMonth = (TextView) view.findViewById(R.id.tv_month);
            viewHolder.tvYear = (TextView) view.findViewById(R.id.tv_year);
            viewHolder.tvID = (TextView) view.findViewById(R.id.listhw_tv_id);
            viewHolder.tvRegID = (TextView) view.findViewById(R.id.listhw_tv_RegID);
            viewHolder.layout_main = (LinearLayout) view.findViewById(R.id.layout_main);
            viewHolder.layout_fake = (LinearLayout) view.findViewById(R.id.layout_hw);


            view.setTag(viewHolder);

        } else {
            viewHolder = (AdapterHW.ViewHolder) view.getTag();
        }



        viewHolder.tvRegID.setText(arrayHW.get(i).getRegID() + "");
        viewHolder.tvID.setText(arrayHW.get(i).getHWID() + "");

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try {
            d = format.parse(arrayHW.get(i).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int Inch = Integer.parseInt(arrayHW.get(i).getHeightIn());

        if (Inch > 12) {
            int feet = Integer.parseInt(arrayHW.get(i).getHeightFt()) + 1;
            int inch = Integer.parseInt(arrayHW.get(i).getHeightIn()) - 12;
            viewHolder.tvHeight.setText(feet + "feet " + inch + "inch");
            viewHolder.tvHeight.setTextColor(act.getResources().getColor(R.color.dashboard_red));
        } else {
            viewHolder.tvHeight.setText(arrayHW.get(i).getHeightFt() + "feet " + arrayHW.get(i).getHeightIn() + "inch");

        }
        viewHolder.tvWeight.setText(arrayHW.get(i).getWeight() + "Kg.");

        int Month = Integer.parseInt(arrayHW.get(i).getAgeM());
        if (Month > 12) {
            int year = Integer.parseInt(arrayHW.get(i).getAgeYr()) + 1;
            int month = Integer.parseInt(arrayHW.get(i).getAgeM()) - 12;
            viewHolder.tvAge.setText(year + "Year " + month + "Month");

        } else {
            viewHolder.tvAge.setText(arrayHW.get(i).getAgeYr() + "Year " + arrayHW.get(i).getAgeM() + "Month");
        }

        viewHolder.tvDate.setText((String) DateFormat.format("dd", d) + "");
        viewHolder.tvDay.setText((String) DateFormat.format("EEEE", d) + "");
        viewHolder.tvYear.setText((String) DateFormat.format("yyyy", d) + "");
        viewHolder.tvMonth.setText((String) DateFormat.format("MMM", d) + "");

        if (i % 2 != 1) {
            viewHolder.layout_main.setBackgroundResource(R.color.white);
        } else {
            viewHolder.layout_main.setBackgroundResource(R.color.gray);
        }

        if (i % 2 != 1) {
            viewHolder.tvDate.setTextColor(act.getResources().getColor(R.color.blue_200));
        } else
            viewHolder.tvDate.setTextColor(act.getResources().getColor(R.color.blue_100));

        int M = Integer.parseInt(arrayHW.get(i).getAgeM());

        if(arrayHW.size()==1)
        {
            viewHolder.layout_fake.setGravity(Gravity.CENTER_VERTICAL);

        }

        return view;
    }
}
