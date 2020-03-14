package com.projects_kidscare.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects_kidscare.Bean.Bean_VaccineTable;
import com.projects_kidscare.R;

import java.util.HashMap;
import java.util.List;


public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<Bean_VaccineTable>> _listDataChild;
    LinearLayout layout_header;
    LinearLayout layout_last;
    TextView DoseNo, OnMonth, AfterMonth, beforeMonth;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<Bean_VaccineTable>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Bean_VaccineTable childText = (Bean_VaccineTable) getChild(groupPosition, childPosition);
        //Bean_VaccineTable bean_item_list=this._listDataChild.get(groupPosition).get(childPosition);//(Bean_VaccineTable) getChild(groupPosition, childPosition);


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_elvitem, null);

        }

        layout_header = (LinearLayout) convertView.findViewById(R.id.header_main);

        if (childPosition == 0) {
            layout_header.setVisibility(View.VISIBLE);

            DoseNo = (TextView) convertView.findViewById(R.id.listChild_doseno1);
            DoseNo.setText(childText.getDoseNo());


            TextView OnMonth = (TextView) convertView.findViewById(R.id.listchild_onmonth1);
            OnMonth.setText(childText.getOnMonth() + "");

            TextView AfterMonth = (TextView) convertView.findViewById(R.id.listchild_aftermonth1);
            AfterMonth.setText(childText.getAfterMonth() + "");

            TextView BeforeMonth = (TextView) convertView.findViewById(R.id.listchild_beforemonth1);
            BeforeMonth.setText(childText.getBeforeMonth() + "");
        } else {
            layout_header.setVisibility(View.GONE);
            DoseNo = (TextView) convertView.findViewById(R.id.listChild_doseno1);
            DoseNo.setText(childText.getDoseNo());

            TextView OnMonth = (TextView) convertView.findViewById(R.id.listchild_onmonth1);
            OnMonth.setText(childText.getOnMonth() + "");

            TextView AfterMonth = (TextView) convertView.findViewById(R.id.listchild_aftermonth1);
            AfterMonth.setText(childText.getAfterMonth() + "");

            TextView BeforeMonth = (TextView) convertView.findViewById(R.id.listchild_beforemonth1);
            BeforeMonth.setText(childText.getBeforeMonth() + "");


        }


        layout_last = (LinearLayout) convertView.findViewById(R.id.header_last);

        if (isLastChild) {
            layout_last.setVisibility(View.VISIBLE);

            DoseNo = (TextView) convertView.findViewById(R.id.listChild_doseno1);
            DoseNo.setText(childText.getDoseNo());


            TextView OnMonth = (TextView) convertView.findViewById(R.id.listchild_onmonth1);
            OnMonth.setText(childText.getOnMonth() + "");

            TextView AfterMonth = (TextView) convertView.findViewById(R.id.listchild_aftermonth1);
            AfterMonth.setText(childText.getAfterMonth() + "");

            TextView BeforeMonth = (TextView) convertView.findViewById(R.id.listchild_beforemonth1);
            BeforeMonth.setText(childText.getBeforeMonth() + "");
        } else {
            layout_last.setVisibility(View.GONE);
            DoseNo = (TextView) convertView.findViewById(R.id.listChild_doseno1);
            DoseNo.setText(childText.getDoseNo());

            TextView OnMonth = (TextView) convertView.findViewById(R.id.listchild_onmonth1);
            OnMonth.setText(childText.getOnMonth() + "");

            TextView AfterMonth = (TextView) convertView.findViewById(R.id.listchild_aftermonth1);
            AfterMonth.setText(childText.getAfterMonth() + "");

            TextView BeforeMonth = (TextView) convertView.findViewById(R.id.listchild_beforemonth1);
            BeforeMonth.setText(childText.getBeforeMonth() + "");


        }


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_elv_header, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.listhedername);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

   /* public void filterData(String query){

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        h.clear();

        if(query.isEmpty()){
            continentList.addAll(originalList);
        }
        else {

            for(Continent continent: originalList){

                ArrayList<Country> countryList = continent.getCountryList();
                ArrayList<Country> newList = new ArrayList<Country>();
                for(Country country: countryList){
                    if(country.getCode().toLowerCase().contains(query) ||
                            country.getName().toLowerCase().contains(query)){
                        newList.add(country);
                    }
                }
                if(newList.size() > 0){
                    Continent nContinent = new Continent(continent.getName(),newList);
                    continentList.add(nContinent);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        notifyDataSetChanged();

    }*/

}
