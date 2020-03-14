package com.projects_kidscare.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.projects_kidscare.Bean.Bean_Vaccine_Sedual;
import com.projects_kidscare.DBHelper.DB_VaccineSedualTable;
import com.projects_kidscare.R;

import java.util.ArrayList;

import static com.projects_kidscare.Fragment.VaccineFragment.arrayVaccineSedual;


public class AdapterVaccineSedual extends BaseAdapter {

    Activity act;

    DB_VaccineSedualTable db_vst;

    public AdapterVaccineSedual(Activity act, ArrayList<Bean_Vaccine_Sedual> arrayVaccineSedual) {
        this.act = act;

        db_vst = new DB_VaccineSedualTable(act);
    }

    @Override
    public int getCount() {
        return arrayVaccineSedual.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayVaccineSedual.get(i);
    }

    @Override
    public long getItemId(int i) {
        return arrayVaccineSedual.get(i).getVaccineID();
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    private class ViewHolder {

        TextView tvID;
        TextView tvRegID;
        TextView tvVaccineName;
        TextView tvDoseNo;
        TextView tvStatus1;
        TextView tvStatus;
        TextView tvOnDate;
        LinearLayout cardView;
        Switch aSwitch;


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = act.getLayoutInflater();
        final AdapterVaccineSedual.ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_vaccine, null);
            viewHolder = new AdapterVaccineSedual.ViewHolder();

            viewHolder.tvVaccineName = (TextView) view.findViewById(R.id.listvac_tv_vacname);
            viewHolder.tvDoseNo = (TextView) view.findViewById(R.id.listvac_tv_doseno);
            viewHolder.tvStatus1 = (TextView) view.findViewById(R.id.listvac_btn_Status);
            viewHolder.tvStatus = (TextView) view.findViewById(R.id.listvac_btn_Status1);
            viewHolder.tvOnDate = (TextView) view.findViewById(R.id.listvac_tv_Ondate);
            viewHolder.tvID = (TextView) view.findViewById(R.id.listvac_tv_id);
            viewHolder.tvRegID = (TextView) view.findViewById(R.id.listvac_tv_RegID);
            viewHolder.cardView = (LinearLayout) view.findViewById(R.id.layout_kids);
            viewHolder.aSwitch = (Switch) view.findViewById(R.id.on_off_switch);


            view.setTag(viewHolder);

        } else {
            viewHolder = (AdapterVaccineSedual.ViewHolder) view.getTag();
        }


        viewHolder.tvID.setText(arrayVaccineSedual.get(i).getVaccineID() + "");
        viewHolder.tvRegID.setText(arrayVaccineSedual.get(i).getRegID() + "");
        viewHolder.tvVaccineName.setText( arrayVaccineSedual.get(i).getVaccineName());
        viewHolder.tvDoseNo.setText(arrayVaccineSedual.get(i).getDoseNo() + "");
        viewHolder.tvOnDate.setText(arrayVaccineSedual.get(i).getOnDate());
        viewHolder.tvStatus1.setText(arrayVaccineSedual.get(i).getStatus() + "");
        if (arrayVaccineSedual.get(i).getOnDate().length() == 0) {
            viewHolder.tvOnDate.setText(arrayVaccineSedual.get(i).getAfterDate() + " to " + arrayVaccineSedual.get(i).getBeforeDate());
        }

        if (arrayVaccineSedual.get(i).getStatus() == 0) {
            viewHolder.tvStatus1.setText("Pending");
            viewHolder.tvStatus1.setTextColor(Color.parseColor("#FF5555"));
            viewHolder.aSwitch.setChecked(false);
        } else {
            viewHolder.tvStatus1.setText("Done");
            viewHolder.tvStatus1.setTextColor(Color.parseColor("#00A885"));
            viewHolder.aSwitch.setChecked(true);
        }

        final int position = i;
        viewHolder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //Toast.makeText(act,"He"+isChecked,Toast.LENGTH_SHORT).show();
                if (arrayVaccineSedual.get(position).getStatus() == 0) {
                    db_vst.updateDetail(Integer.parseInt(viewHolder.tvID.getText().toString()), 1);
                    viewHolder.tvStatus1.setText("Done");
                    viewHolder.tvStatus1.setTextColor(Color.parseColor("#00A885"));
                    arrayVaccineSedual.get(position).setStatus(1);

                } else {
                    db_vst.updateDetail(Integer.parseInt(viewHolder.tvID.getText().toString()), 0);
                    viewHolder.tvStatus1.setText("Pending");
                    viewHolder.tvStatus1.setTextColor(Color.parseColor("#FF5555"));
                    arrayVaccineSedual.get(position).setStatus(0);
                }


            }
        });


        if (i % 2 != 1) {
            viewHolder.cardView.setBackgroundResource(R.color.white);
        } else {
            viewHolder.cardView.setBackgroundResource(R.color.gray);
        }


        return view;
    }


}
