package com.projects_kidscare.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects_kidscare.Bean.Bean_Registration;
import com.projects_kidscare.R;

import java.util.ArrayList;



public class DashboardAdapter extends BaseAdapter {

    Activity act;
    ArrayList<Bean_Registration> arrayReg;
    private int position1;


    public int getPosition1() {
        return position1;
    }

    public void setPosition1(int position1) {
        this.position1 = position1;

    }

    int EventID = 0;

    public DashboardAdapter(Activity act, ArrayList<Bean_Registration> arrayReg) {
        this.act = act;
        this.arrayReg = arrayReg;

    }

    @Override
    public int getCount() {
        return arrayReg.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayReg.get(i);
    }

    @Override
    public long getItemId(int i) {
        return arrayReg.get(i).getRegID();
    }


    private class ViewHolder {

        TextView tvName;
        TextView tvID;
        ImageView iv_profile;
        TextView tvGallery;


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = act.getLayoutInflater();
        final ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_reg, null);
            viewHolder = new ViewHolder();

            viewHolder.tvName = (TextView) view.findViewById(R.id.listreg_btn_name);
            viewHolder.tvID = (TextView) view.findViewById(R.id.listreg_tv_id);

            viewHolder.iv_profile = (ImageView) view.findViewById(R.id.iv_profile);
            viewHolder.tvGallery = (TextView) view.findViewById(R.id.isGallery);

            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.tvID.setText(arrayReg.get(i).getRegID() + "");
        viewHolder.tvName.setText(arrayReg.get(i).getName());

        if (arrayReg.get(i).getIsGallery() == 1) {
            viewHolder.iv_profile.setImageBitmap(StringToBitMap(arrayReg.get(i).getPicurePath()));

        } else {
            viewHolder.iv_profile.setImageBitmap(BitmapFactory.decodeFile(arrayReg.get(i).getPicurePath()));

        }
            if(arrayReg.get(i).getPicurePath()== null && arrayReg.get(i).getIsGallery() == 0)
         {
            viewHolder.iv_profile.setImageResource(R.drawable.baby2);
        }
        return view;
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
