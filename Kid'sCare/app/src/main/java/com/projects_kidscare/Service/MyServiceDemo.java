package com.projects_kidscare.Service;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.projects_kidscare.Bean.Bean_Vaccine_Sedual;
import com.projects_kidscare.DBHelper.DB_Registration;
import com.projects_kidscare.DBHelper.DB_VaccineSedualTable;
import com.projects_kidscare.Design.DashboardActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyServiceDemo extends Service {
    public MyServiceDemo() {
    }

    Activity act;
    DB_VaccineSedualTable  db_vaccineSedualTable;
    DB_Registration db_registration;
    ArrayList<Bean_Vaccine_Sedual>  bean_dates;
    String formattedDate;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        db_vaccineSedualTable=new DB_VaccineSedualTable(this);
        db_registration=new DB_Registration(this);
        Log.d("hello","Started");
        Date c = Calendar.getInstance().getTime();
        //System.out.println("Current time => " + c);

        final String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());

        final Context context=this;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                Log.d("hello","running");
               // Toast.makeText(context,"Runing",Toast.LENGTH_SHORT).show();
                Calendar rightNow = Calendar.getInstance();
                int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
                int min=rightNow.get(Calendar.MINUTE);
                if(currentHour==8 && min==0)
                {
                    bean_dates=db_vaccineSedualTable.selectBYDattee();
                    for(int i=0;i<bean_dates.size();i++)
                    {

                        if(date.equalsIgnoreCase(bean_dates.get(i).getOnDate().toString()))
                        {
                            DashboardActivity.createNotification(getApplicationContext(),bean_dates.get(i).getVaccineName()+"",currentHour+"Take a Dose...!"+db_registration.selectNameReg(bean_dates.get(i).getRegID()));
                        }

                    }
                }

            }
        };
//        timer.schedule(task, 0, (60000));// In ms 60 secs is 60000
        timer.schedule(task, 0, (1000));// In ms 60 secs is 60000
        // 15 mins is 60000 * 15


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("hello","Destroy");
    }
}
