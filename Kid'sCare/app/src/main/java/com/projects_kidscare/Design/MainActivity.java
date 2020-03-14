package com.projects_kidscare.Design;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.projects_kidscare.Bean.Bean_Registration;
import com.projects_kidscare.DBHelper.DB_RegDetail;
import com.projects_kidscare.Fragment.HWFragment;
import com.projects_kidscare.Fragment.MedicineFragment;
import com.projects_kidscare.Fragment.VaccineFragment;
import com.projects_kidscare.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Bean_Registration br;
    DB_RegDetail dbr;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String RegID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String ID = getIntent().getStringExtra("RegID");
        dbr = new DB_RegDetail(this);
        RegID = ID;
        br = dbr.selectByRegID(Integer.parseInt(ID));
        setTitle(br.getName());

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    public String getMyData() {
        return RegID;
    }

    private void setupViewPager(ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new HWFragment(), "Height-Weight");
        adapter.addFragment(new MedicineFragment(), "Medicine");
        adapter.addFragment(new VaccineFragment(), "Vaccine");

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        ListView lst;
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);


        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }

}
