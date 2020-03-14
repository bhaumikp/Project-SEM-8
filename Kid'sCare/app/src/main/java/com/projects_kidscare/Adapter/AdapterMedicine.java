package com.projects_kidscare.Adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projects_kidscare.Bean.Bean_Medicine;
import com.projects_kidscare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AdapterMedicine extends BaseAdapter {

    Activity act;
    ArrayList<Bean_Medicine> arrayMed;
    Typeface tf;

    public AdapterMedicine(Activity act, ArrayList<Bean_Medicine> arrayMed) {
        this.act = act;
        this.arrayMed = arrayMed;

    }

    @Override
    public int getCount() {
        return arrayMed.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayMed.get(i);
    }

    @Override
    public long getItemId(int i) {
        return arrayMed.get(i).getDiseasesID();
    }


    private class ViewHolder {

        TextView tvID, tvDoses;
        TextView tvRegID;
        TextView tvDiseasesName;
        TextView tvDate;
        TextView tvDay;
        TextView tvYear;
        TextView tvMonth;
        TextView tvttf;
        CardView cardView;
        int p;


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = act.getLayoutInflater();
        final AdapterMedicine.ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_medicine, null);
            viewHolder = new AdapterMedicine.ViewHolder();

            viewHolder.tvDiseasesName = (TextView) view.findViewById(R.id.listmed_tv_diseases);
            viewHolder.tvID = (TextView) view.findViewById(R.id.listmed_tv_id);
            viewHolder.tvRegID = (TextView) view.findViewById(R.id.listmed_tv_RegID);
            viewHolder.tvDate = (TextView) view.findViewById(R.id.medtv_date);
            viewHolder.tvDay = (TextView) view.findViewById(R.id.tv_day);
            viewHolder.tvMonth = (TextView) view.findViewById(R.id.medtv_month);
            viewHolder.tvYear = (TextView) view.findViewById(R.id.medtv_year);
            viewHolder.cardView = (CardView) view.findViewById(R.id.card_view_portfoliowisescrip);
            viewHolder.tvttf = (TextView) view.findViewById(R.id.medicine_arrow);

            view.setTag(viewHolder);

        } else {
            viewHolder = (AdapterMedicine.ViewHolder) view.getTag();
        }

        viewHolder.tvRegID.setText(arrayMed.get(i).getRegID() + "");
        viewHolder.tvID.setText(arrayMed.get(i).getDiseasesID() + "");
        viewHolder.tvDiseasesName.setText(arrayMed.get(i).getDiseasesName() + "");

        tf = Typeface.createFromAsset(act.getAssets(), "fontawesome-webfont.ttf");
        viewHolder.tvttf.setTypeface(tf);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try {
            d = format.parse(arrayMed.get(i).getDate());
        } catch (ParseException e) {
            e.printStackTrace();

        }

        viewHolder.tvDate.setText((String) DateFormat.format("dd", d) + "");
        viewHolder.tvYear.setText((String) DateFormat.format("yyyy", d) + "");
        viewHolder.tvMonth.setText((String) DateFormat.format("MMM", d) + "");

        if (i % 2 != 1) {
            viewHolder.cardView.setBackgroundResource(R.color.white);
        } else {
            viewHolder.cardView.setBackgroundResource(R.color.gray);
        }
        viewHolder.p = i;


        if (i % 2 != 1) {
            viewHolder.tvDate.setTextColor(act.getResources().getColor(R.color.blue_200));
        } else
            viewHolder.tvDate.setTextColor(act.getResources().getColor(R.color.blue_100));

        return view;
    }
}
