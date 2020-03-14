package com.projects_kidscare.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.projects_kidscare.DBHelper.DB_VaccineSedualTable;
import com.projects_kidscare.R;
import com.projects_kidscare.Utility.Constant;

import static com.projects_kidscare.Fragment.VaccineFragment.arrayVaccineSedual;


public class AdapterVaccineFragment extends RecyclerView.Adapter<AdapterVaccineFragment.MyViewHolder> {


    DB_VaccineSedualTable db_vst;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvID;
        TextView tvRegID;
        TextView tvVaccineName;
        TextView tvDoseNo;
        TextView tvStatus1;
        TextView tvStatus;
        TextView tvOnDate;
        LinearLayout cardView;
        Switch aSwitch;

        public MyViewHolder(View view) {
            super(view);

            tvVaccineName = (TextView) view.findViewById(R.id.listvac_tv_vacname);
            tvDoseNo = (TextView) view.findViewById(R.id.listvac_tv_doseno);
            tvStatus1 = (TextView) view.findViewById(R.id.listvac_btn_Status);
            tvStatus = (TextView) view.findViewById(R.id.listvac_btn_Status1);
            tvOnDate = (TextView) view.findViewById(R.id.listvac_tv_Ondate);
            tvID = (TextView) view.findViewById(R.id.listvac_tv_id);
            tvRegID = (TextView) view.findViewById(R.id.listvac_tv_RegID);
            cardView = (LinearLayout) view.findViewById(R.id.layout_kids);
            aSwitch = (Switch) view.findViewById(R.id.on_off_switch);


        }
    }

    public AdapterVaccineFragment(Activity activity) {

        db_vst = new DB_VaccineSedualTable(activity);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_vaccine, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, int i) {
        viewHolder.tvID.setText(arrayVaccineSedual.get(i).getVaccineID() + "");
        viewHolder.tvRegID.setText(arrayVaccineSedual.get(i).getRegID() + "");
        viewHolder.tvVaccineName.setText("Vaccine : " + arrayVaccineSedual.get(i).getVaccineName());
        viewHolder.tvDoseNo.setText("Dose No. " + arrayVaccineSedual.get(i).getDoseNo() + "");
        viewHolder.tvOnDate.setText("Date: " + arrayVaccineSedual.get(i).getOnDate());
        viewHolder.tvStatus1.setText(arrayVaccineSedual.get(i).getStatus() + "");

        if (arrayVaccineSedual.get(i).getOnDate().length() == 0) {
            viewHolder.tvOnDate.setText("Date: " + arrayVaccineSedual.get(i).getAfterDate() + " to " + arrayVaccineSedual.get(i).getBeforeDate());
        }

        if (Integer.parseInt(viewHolder.tvStatus1.getText().toString()) == 0) {
            viewHolder.tvStatus1.setText("Pending");
            viewHolder.tvStatus1.setTextColor(Color.parseColor("#FF5555"));
            viewHolder.aSwitch.setChecked(false);
        } else if (Integer.parseInt(viewHolder.tvStatus1.getText().toString()) == 1) {
            viewHolder.tvStatus1.setText("Done");
            viewHolder.tvStatus1.setTextColor(Color.parseColor("#00A885"));
            viewHolder.aSwitch.setChecked(true);
        }

        Constant.cPosition = i;
        viewHolder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int position = Constant.cPosition;
                if (arrayVaccineSedual.get(position).getStatus() == 0) {
                    db_vst.updateDetail(arrayVaccineSedual.get(position).getVaccineSedualID(), 1);
                    viewHolder.tvStatus1.setText("Done");
                    viewHolder.tvStatus1.setTextColor(Color.parseColor("#00A885"));
                    arrayVaccineSedual.get(position).setStatus(1);

                } else {
                    db_vst.updateDetail(arrayVaccineSedual.get(position).getVaccineSedualID(), 0);
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


    }

    @Override
    public int getItemCount() {
        return arrayVaccineSedual.size();
    }
}
