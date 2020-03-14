package com.projects_kidscare.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projects_kidscare.Bean.Bean_Ideal;
import com.projects_kidscare.R;

import java.util.ArrayList;


public class AdapterIdealTable extends BaseAdapter {

    Activity act;
    ArrayList<Bean_Ideal> arrayIdeal;

    public AdapterIdealTable(Activity act, ArrayList<Bean_Ideal> arrayIdeal) {
        this.act = act;
        this.arrayIdeal = arrayIdeal;
    }

    @Override
    public int getCount() {
        return arrayIdeal.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayIdeal.get(i);
    }

    @Override
    public long getItemId(int i) {
        return arrayIdeal.get(i).getIdealID();
    }


    private class ViewHolder {

        TextView tvAge;
        TextView tvHeight;
        TextView tvWeight;
        TextView tvID;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = act.getLayoutInflater();
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_ideal, null);
            viewHolder = new ViewHolder();

            viewHolder.tvAge = (TextView) view.findViewById(R.id.listideal_tv_age);
            viewHolder.tvHeight = (TextView) view.findViewById(R.id.listideal_tv_height);
            viewHolder.tvWeight = (TextView) view.findViewById(R.id.listideal_tv_weight);
            viewHolder.tvID = (TextView) view.findViewById(R.id.listideal_tv_id);

            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvHeight.setText(arrayIdeal.get(i).getHeight());
        viewHolder.tvWeight.setText(arrayIdeal.get(i).getWeight());
        viewHolder.tvAge.setText(arrayIdeal.get(i).getAge());
        viewHolder.tvID.setText(arrayIdeal.get(i).getIdealID() + "");

        return view;
    }

}
