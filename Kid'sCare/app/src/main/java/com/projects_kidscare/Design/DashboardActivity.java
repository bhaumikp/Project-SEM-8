package com.projects_kidscare.Design;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.projects_kidscare.Adapter.DashboardAdapter;
import com.projects_kidscare.Bean.Bean_Registration;
import com.projects_kidscare.DBHelper.DB_Registration;
import com.projects_kidscare.R;
import com.projects_kidscare.Service.MyServiceDemo;
import com.projects_kidscare.Utility.Constant;
import com.projects_kidscare.kidscare.Design.ExpandableListViewActivity;

import java.util.ArrayList;
import java.util.Random;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnRegistration;
    Button btnIdealTable;
    TextView tvID;
    Context c;
    DB_Registration dbr;
    ArrayList<Bean_Registration> arrayReg;
    GridView gridView;
    View view;
    int p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        c = getApplicationContext();
        init();


        dbr = new DB_Registration(this);
        arrayReg = dbr.selectAllReg();
        gridView.setAdapter(new DashboardAdapter(this, arrayReg));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // NotificationScheduler.setReminder(DashboardActivity.this, AlarmReceiver.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      /*  new MaterialShowcaseView.Builder(this)
                .setTarget(fab)
                .setDismissText("GOT IT")
                .setContentText("First Click Here And Register")
                .setDelay(500) // optional but starting animations immediately in onCreate can make them choppy
                .singleUse("1") // provide a unique ID used to ensure it is only shown once
                .show();*/


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(DashboardActivity.this, RegistrationActivity.class);
                in.putExtra("Update", 0);
                in.putExtra("RegID", 0);
                startActivity(in);


            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String strid = ((TextView) view.findViewById(R.id.listreg_tv_id)).getText().toString();
                Intent in = new Intent(DashboardActivity.this, MainActivity.class);
                in.putExtra("RegID", strid);
                Constant.RegId = Integer.parseInt(strid);
                startActivity(in);

            }
        });


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {

            Intent serviceIntent = new Intent(this, MyServiceDemo.class);
            startService(serviceIntent);

            Intent i = new Intent(DashboardActivity.this, RegistrationActivity.class);
            startActivity(i);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                openContextMenu(gridView);
                p = position;
                return true;
            }
        });


        // Code to run once


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_developer) {
            Intent i = new Intent(DashboardActivity.this, DeveloperActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_registration) {
            Intent in = new Intent(DashboardActivity.this, RegistrationActivity.class);
            startActivity(in);


        } else if (id == R.id.nav_idealtable) {

            Intent in = new Intent(DashboardActivity.this, IdealTableActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_Vaccinetable) {

            Intent in = new Intent(DashboardActivity.this, ExpandableListViewActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_share) {

            Intent share = new Intent();
            share.setAction("android.intent.action.SEND");
            share.setType("text/plain");
            share.putExtra("android.intent.extra.TEXT", Constant.SharedMessage + "");
            startActivity(Intent.createChooser(share, "Share with..!"));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    void init() {
        setTitle("Dashboard");
        gridView = (GridView) findViewById(R.id.dashboard_lst);
        btnRegistration = (Button) findViewById(R.id.nav_registration);
        btnIdealTable = (Button) findViewById(R.id.nav_registration);
        tvID = (TextView) findViewById(R.id.listreg_tv_id);
        registerForContextMenu(gridView);
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        menu.add("Edit");
        menu.add("Delete");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final String str = item.getTitle().toString();


        if (str.equalsIgnoreCase("Edit")) {
            Intent i = new Intent(DashboardActivity.this, RegistrationActivity.class);
            i.putExtra("RegID", arrayReg.get(p).getRegID());
            i.putExtra("Update", 1);
            startActivity(i);
        } else if (str.equalsIgnoreCase("Delete")) {
            AlertDialog Yes = new AlertDialog.Builder(new ContextThemeWrapper(this,
                    android.R.style.Theme_Holo_Light_Dialog))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage("Are you sure want to Delete")
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    dbr.deleteReg(arrayReg.get(p).getRegID());
                                    arrayReg = dbr.selectAllReg();
                                    gridView.setAdapter(new DashboardAdapter(DashboardActivity.this, arrayReg));
                                    Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();

                                }
                            }).setNegativeButton("No",
                            null).show();
        }

        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void createNotification(Context con, String title, String largetext) {

        Intent intent = new Intent(con, DashboardActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(con, (int) System.currentTimeMillis(), intent, 0);


        Notification noti = new Notification.Builder(con)
                .setContentTitle("" + title)
                .setContentText(largetext).setSmallIcon(R.drawable.add)
                .setContentIntent(pIntent)
                .build();
        NotificationManager notificationManager = (NotificationManager) con.getSystemService(NOTIFICATION_SERVICE);

        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        notificationManager.notify(m, noti);

    }

    protected void onResume() {
        super.onResume();
        arrayReg = dbr.selectAllReg();
        gridView.setAdapter(new DashboardAdapter(this, arrayReg));

    }


}
