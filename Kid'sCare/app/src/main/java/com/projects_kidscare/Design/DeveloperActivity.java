package com.projects_kidscare.Design;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects_kidscare.BuildConfig;
import com.projects_kidscare.R;
import com.projects_kidscare.Utility.Constant;

public class DeveloperActivity extends AppCompatActivity {

    TextView icmail, icweb, icshare, icphone, icapp, icrate, iclike, icupdate, iccopy, tvPrivacy;
    TextView appinfo;
    Toolbar tb;
    LinearLayout email, web, call, share, moreapps, rate, likefb, update;
   // WebView wvdetail;
    Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        setTitle("Developer");

        tvPrivacy = (TextView) findViewById(R.id.dev_tv_privacy);
        icmail = (TextView) findViewById(R.id.dev_ic_mail);
        icweb = (TextView) findViewById(R.id.dev_ic_web);
        icshare = (TextView) findViewById(R.id.dev_ic_share);
        icphone = (TextView) findViewById(R.id.dev_ic_phone);
        icapp = (TextView) findViewById(R.id.dev_ic_app);
        icrate = (TextView) findViewById(R.id.dev_ic_rate);
        iclike = (TextView) findViewById(R.id.dev_ic_like);
        icupdate = (TextView) findViewById(R.id.dev_ic_update);
        appinfo = (TextView) findViewById(R.id.dev_tv_appinfo);
        iccopy = (TextView) findViewById(R.id.dev_tv_copy);
        call = (LinearLayout) findViewById(R.id.dev_call);


        tf = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        icmail.setTypeface(tf);
        icweb.setTypeface(tf);
        icshare.setTypeface(tf);
        icapp.setTypeface(tf);
        icrate.setTypeface(tf);
        icphone.setTypeface(tf);
        iclike.setTypeface(tf);
        icupdate.setTypeface(tf);
        appinfo.setTypeface(tf);
        iccopy.setTypeface(tf);

        email = (LinearLayout) findViewById(R.id.dev_email);
        web = (LinearLayout) findViewById(R.id.dev_web);
        share = (LinearLayout) findViewById(R.id.dev_share);
        moreapps = (LinearLayout) findViewById(R.id.dev_more_apps);
        rate = (LinearLayout) findViewById(R.id.dev_rate);
        likefb = (LinearLayout) findViewById(R.id.dev_like_fb);
        update = (LinearLayout) findViewById(R.id.dev_update);




        appinfo.setText(getResources().getString(R.string.app_name) + " (v" + BuildConfig.VERSION_NAME + ")");

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailintent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", Constant.AdminEmail, null));
                emailintent.putExtra("android.intent.extra.SUBJECT", "Contact from " + getString(R.string.app_name));
                startActivity(Intent.createChooser(emailintent, "Send Email to Us"));
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+" + Constant.AdminMobileNo));
                startActivity(intent);
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webintent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.marwadiuniversity.ac.in"));
                startActivity(webintent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent();
                share.setAction("android.intent.action.SEND");
                share.setType("text/plain");
                share.putExtra("android.intent.extra.TEXT", Constant.AppPlayStoreLink);
                startActivity(Intent.createChooser(share,"Share with..!"));
            }
        });
//
//        rate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent rateintent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName()));
//                startActivity(rateintent);
//            }
//        });
//
//
//
//        likefb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent fbintent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/"));
//                startActivity(fbintent);
//            }
//        });
//
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent updateintent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName()));
//                startActivity(updateintent);
//            }
//        });
//
        //String strInst = getString(R.string.inst_name).toString();
        //iccopy.setText("\uf1f9 " + Calendar.getInstance().get(Calendar.YEAR) + " " + strInst);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        wvdetail.destroy();
//    }
}


