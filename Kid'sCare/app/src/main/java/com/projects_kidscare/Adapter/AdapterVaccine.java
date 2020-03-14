package com.projects_kidscare.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projects_kidscare.Bean.Bean_Vaccine_Sedual;
import com.projects_kidscare.R;

import java.util.ArrayList;

/**
 * Created by ANMOL on 9/26/2017.
 */

public class AdapterVaccine extends BaseAdapter {

    Activity act;
    ArrayList<Bean_Vaccine_Sedual> arrayVaccine;

    public AdapterVaccine(Activity act, ArrayList<Bean_Vaccine_Sedual> arrayVaccine) {
        this.act = act;
        this.arrayVaccine = arrayVaccine;

    }

    @Override
    public int getCount() {
        return arrayVaccine.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayVaccine.get(i);
    }

    @Override
    public long getItemId(int i) {
        return arrayVaccine.get(i).getVaccineID();
    }


    private class ViewHolder {

        TextView tvID;
        TextView tvRegID;
        TextView tvVaccineName;
        TextView tvDoseNo;
        TextView tvOnDate;


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = act.getLayoutInflater();
        final AdapterVaccine.ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_vaccine, null);
            viewHolder = new AdapterVaccine.ViewHolder();

            viewHolder.tvVaccineName = (TextView) view.findViewById(R.id.listvac_tv_vacname);
            viewHolder.tvDoseNo = (TextView) view.findViewById(R.id.listvac_tv_doseno);
            viewHolder.tvOnDate = (TextView) view.findViewById(R.id.listvac_tv_Ondate);
            viewHolder.tvID = (TextView) view.findViewById(R.id.listvac_tv_id);
            viewHolder.tvRegID = (TextView) view.findViewById(R.id.listvac_tv_RegID);


            view.setTag(viewHolder);

        } else {
            viewHolder = (AdapterVaccine.ViewHolder) view.getTag();
        }


        viewHolder.tvID.setText(arrayVaccine.get(i).getVaccineID() + "");
        viewHolder.tvRegID.setText(arrayVaccine.get(i).getRegID() + "");
        viewHolder.tvVaccineName.setText(arrayVaccine.get(i).getVaccineName());
        viewHolder.tvDoseNo.setText(arrayVaccine.get(i).getDoseNo() + "");
        viewHolder.tvOnDate.setText(arrayVaccine.get(i).getOnDate());

        return view;
    }
}
