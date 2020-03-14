package com.projects_kidscare.Design;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projects_kidscare.Bean.Bean_Registration;
import com.projects_kidscare.Bean.Bean_VaccineTable;
import com.projects_kidscare.DBHelper.DB_Registration;
import com.projects_kidscare.DBHelper.DB_VaccineSedualTable;
import com.projects_kidscare.DBHelper.DB_VaccineTable;
import com.projects_kidscare.R;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationActivity extends AppCompatActivity {

    Bean_Registration beanRegistration;
    EditText etName;
    TextView tvDOB, img_error;
    Button btnSave;
    Button btnCancel;
    DB_Registration dbr;
    Bean_Registration br;
    String strFrom = "";
    DB_VaccineTable dbvactbl;
    ArrayList<Bean_VaccineTable> arrayVactbl;
    DatePickerDialog dp;
    SimpleDateFormat sdf;
    DB_VaccineSedualTable db_vst;
    CircleImageView civ;
    String picturePath = "";
    String IsGallery = "";
    int mId, updateStatus;
    ImageView ivImage;
    Bitmap picture;


    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Registration");


        init();

        setDateTimeField();

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code

                if (checkPermission1()) {

                } else {
                    requestPermission1();
                }
            } else {
                requestPermission(); // Code for permission
                if (checkPermission1()) {

                } else {
                    requestPermission1();
                }
            }

        } else {

            // Code for Below 23 API Oriented Device
            // Do next code
        }

        tvDOB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dp.show();
                return true;
            }
        });


        br = new Bean_Registration();
        strFrom = getIntent().getStringExtra("From");

        dbr = new DB_Registration(this);

        db_vst = new DB_VaccineSedualTable(this);

        tvDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.show();
            }
        });

        dp.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (tvDOB.getText().length() > 0) {
                    tvDOB.setError(null);

                }
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        civ.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                final CharSequence[] items = {"Take Photo", "Choose from Library",
                        "Cancel"};


                AlertDialog.Builder builder = new AlertDialog.Builder(
                        RegistrationActivity.this);


                // builder.setTitle("Add Photo!");
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Take Photo")) {

                            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePicture, 0);


                        } else if (items[item].equals("Choose from Library")) {
                            Intent i = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(i, 1);


                        } else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }


        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int flag = 0;


                if (etName.getText().length() > 0) {
                    br.setName(etName.getText().toString());

                    //code place here

                } else {
                    flag = 1;
                    etName.setError("Enter Name");
                }

                if (!(picturePath.length() == 0)) {


                } else {


                    img_error.setVisibility(View.GONE);

                }


                if (tvDOB.getText().length() > 0) {
                    br.setDOB(tvDOB.getText().toString());

                } else {
                    tvDOB.setError("Please Select Date");
                    flag = 1;

                }

                if (flag == 0) {

                    if (updateStatus == 1) {
                        br.setPicurePath(beanRegistration.getPicurePath());
                        br.setIsGallery(beanRegistration.getIsGallery());
                        br.setRegID(mId);
                        dbr.updateRegDetail(br);
                        Toast.makeText(RegistrationActivity.this, "Record Updated", Toast.LENGTH_LONG).show();
                    } else {
                        dbr.insertRegDetail(br, strFrom);
                        Toast.makeText(RegistrationActivity.this, "Record Saved", Toast.LENGTH_LONG).show();
                    }

                    dbvactbl = new DB_VaccineTable(getApplicationContext());
                    arrayVactbl = dbvactbl.selectAllVaccineTable();
                    db_vst.insertDetailFromTable(arrayVactbl, db_vst.selectMaxRegID());


                    //for ondate column
                    for (int i = 0; i < arrayVactbl.size(); i++) {
                        if (arrayVactbl.get(i).getOnMonth().equalsIgnoreCase("Birth")) {
                            db_vst.updateOnTimeRegID(tvDOB.getText().toString(), db_vst.selectMaxRegID(), "On", arrayVactbl.get(i).getVaccineID());
                        }

                        if (arrayVactbl.get(i).getOnMonth().contains("m")) {
                            String temp = arrayVactbl.get(i).getOnMonth().toString();
                            String tmp_new = temp.replace("m", " ");

                            String st = br.getDOB().toString() + "";
                            try {
                                Date date = (Date) sdf.parse(st);
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);
                                calendar.add(Calendar.MONTH, Integer.parseInt(tmp_new.trim()));
                                db_vst.updateOnTimeRegID(sdf.format(calendar.getTime()).toString(), db_vst.selectMaxRegID(), "On", arrayVactbl.get(i).getVaccineID());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                        if (arrayVactbl.get(i).getOnMonth().contains("y")) {
                            String temp = arrayVactbl.get(i).getOnMonth().toString();
                            String tmp_new = temp.replace("y", " ");

                            String st = br.getDOB().toString();
                            try {
                                Date date = (Date) sdf.parse(st);
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);
                                calendar.add(Calendar.YEAR, Integer.parseInt(tmp_new.trim()));
                                db_vst.updateOnTimeRegID(sdf.format(calendar.getTime()).toString(), db_vst.selectMaxRegID(), "On", arrayVactbl.get(i).getVaccineID());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    //for afterdate column
                    for (int i = 0; i < arrayVactbl.size(); i++) {
                        if (arrayVactbl.get(i).getAfterMonth().equalsIgnoreCase("Birth")) {
                            db_vst.updateOnTimeRegID(tvDOB.getText().toString(), db_vst.selectMaxRegID(), "After", arrayVactbl.get(i).getVaccineID());
                        }

                        if (arrayVactbl.get(i).getAfterMonth().contains("m")) {
                            String temp = arrayVactbl.get(i).getAfterMonth().toString();
                            String tmp_new = temp.replace("m", " ");

                            String st = br.getDOB().toString();
                            try {
                                Date date = (Date) sdf.parse(st);
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);
                                calendar.add(Calendar.MONTH, Integer.parseInt(tmp_new.trim()));
                                db_vst.updateOnTimeRegID(sdf.format(calendar.getTime()).toString(), db_vst.selectMaxRegID(), "After", arrayVactbl.get(i).getVaccineID());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                        if (arrayVactbl.get(i).getAfterMonth().contains("y")) {
                            String temp = arrayVactbl.get(i).getAfterMonth().toString();
                            String tmp_new = temp.replace("y", " ");

                            String st = br.getDOB().toString();
                            try {
                                Date date = (Date) sdf.parse(st);
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);
                                calendar.add(Calendar.YEAR, Integer.parseInt(tmp_new.trim()));
                                db_vst.updateOnTimeRegID(sdf.format(calendar.getTime()).toString(), db_vst.selectMaxRegID(), "After", arrayVactbl.get(i).getVaccineID());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                    //for beforedate column
                    for (int i = 0; i < arrayVactbl.size(); i++) {
                        if (arrayVactbl.get(i).getBeforeMonth().equalsIgnoreCase("Birth")) {
                            db_vst.updateOnTimeRegID(tvDOB.getText().toString(), db_vst.selectMaxRegID(), "Before", arrayVactbl.get(i).getVaccineID());
                        }

                        if (arrayVactbl.get(i).getBeforeMonth().contains("m")) {
                            String temp = arrayVactbl.get(i).getBeforeMonth().toString();
                            String tmp_new = temp.replace("m", " ");

                            String st = br.getDOB().toString();
                            try {
                                Date date = (Date) sdf.parse(st);
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);
                                calendar.add(Calendar.MONTH, Integer.parseInt(tmp_new.trim()));
                                db_vst.updateOnTimeRegID(sdf.format(calendar.getTime()).toString(), db_vst.selectMaxRegID(), "Before", arrayVactbl.get(i).getVaccineID());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                        if (arrayVactbl.get(i).getBeforeMonth().contains("y")) {
                            String temp = arrayVactbl.get(i).getBeforeMonth().toString();
                            String tmp_new = temp.replace("y", " ");

                            String st = br.getDOB().toString();
                            try {
                                Date date = (Date) sdf.parse(st);
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);
                                calendar.add(Calendar.YEAR, Integer.parseInt(tmp_new.trim()));
                                db_vst.updateOnTimeRegID(sdf.format(calendar.getTime()).toString(), db_vst.selectMaxRegID(), "Before", arrayVactbl.get(i).getVaccineID());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    finish();

                } else {

                }


            }
        });


    }

    void init() {

        updateStatus = getIntent().getIntExtra("Update", 0);
        mId = getIntent().getIntExtra("RegID", 0);


        etName = (EditText) findViewById(R.id.reg_et_name);
        tvDOB = (EditText) findViewById(R.id.reg_tv_dob);
        img_error = (TextView) findViewById(R.id.img_error);
        btnCancel = (Button) findViewById(R.id.reg_btn_cancel);
        btnSave = (Button) findViewById(R.id.reg_btn_save);
        civ = (CircleImageView) findViewById(R.id.profile_image);
        ivImage = (ImageView) findViewById(R.id.profile_image);

        tvDOB = (TextView) findViewById(R.id.reg_tv_dob);
        sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (updateStatus == 1) {
            btnSave.setText("UPDATE");
            beanRegistration = new Bean_Registration();
            dbr = new DB_Registration(this);
            beanRegistration = dbr.selectbyIDReg(mId);
            etName.setText(beanRegistration.getName().toString() + "");
            tvDOB.setText(beanRegistration.getDOB().toString() + "");

            //ivImage.setImageBitmap(BitmapFactory.decodeFile(beanRegistration.getPicurePath().toString() + ""));
            picturePath = beanRegistration.getPicurePath();
            //IsGallery = beanRegistration.getIsGallery();
        }
    }


    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        dp = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                DatePicker datePicker = dp.getDatePicker();
                Calendar newDate = Calendar.getInstance();

                datePicker.setMaxDate(newDate.getTimeInMillis());
                newDate.set(year, monthOfYear, dayOfMonth);
                tvDOB.setText(sdf.format(newDate.getTime()));
                Date currentDate = new Date();


            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DATE));



    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(RegistrationActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkPermission1() {
        int result = ContextCompat.checkSelfPermission(RegistrationActivity.this, Manifest.permission.CAMERA);

        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(RegistrationActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(RegistrationActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(RegistrationActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private void requestPermission1() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(RegistrationActivity.this, Manifest.permission.CAMERA)) {
            Toast.makeText(RegistrationActivity.this, "Write Camera permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(RegistrationActivity.this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // check whether storage permission granted or not.
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //do what you want;
                    }
                }

            case MY_CAMERA_REQUEST_CODE:
                if (grantResults.length > 0 && permissions[0].equals(Manifest.permission.CAMERA)) {
                    // check whether storage permission granted or not.
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //do what you want;
                    }
                }


                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            br.setPicurePath(picturePath);
            if (updateStatus == 1) {
                beanRegistration.setPicurePath(picturePath);

            }

            br.setIsGallery(0);
            ivImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            ivImage.setImageBitmap(picture);
            img_error.setVisibility(View.GONE);

        } else if (requestCode == 0 && resultCode == RESULT_OK) {
            //Toast.makeText(getApplicationContext(), "abcd", Toast.LENGTH_SHORT).show();


            picture = (Bitmap) data.getExtras().get("data");
            br.setPicurePath(BitMapToString(picture));
            if (updateStatus == 1) {
                beanRegistration.setPicurePath(picture.toString());
            }

            br.setIsGallery(1);
            ivImage.setImageBitmap(picture);
            img_error.setVisibility(View.GONE);

        }

    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code

                if (checkPermission1()) {

                } else {
                    requestPermission1();
                }
            } else {
                requestPermission(); // Code for permission
                if (checkPermission1()) {

                } else {
                    requestPermission1();
                }
            }

        } else {

            // Code for Below 23 API Oriented Device
            // Do next code
        }
    }
}
