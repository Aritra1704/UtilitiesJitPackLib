package com.arpaul.utilitiesjitpacklib;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.arpaul.utilitieslib.CalendarUtils;
import com.arpaul.utilitieslib.LogUtils;
import com.arpaul.utilitieslib.NetworkUtility;
import com.arpaul.utilitieslib.PermissionUtils;
import com.arpaul.utilitieslib.StringUtils;
import com.arpaul.utilitieslib.UnCaughtException;
import com.arpaul.utilitieslib.ValidationUtils;

public class MainActivity extends AppCompatActivity {

    String TAG = "UTILITIES_LIB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(MainActivity.this,"aritra1704@gmail.com",getString(R.string.app_name)));
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

//        if(new PermissionUtils().checkPermission(this,new String[]{
//                android.Manifest.permission.ACCESS_FINE_LOCATION,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION}) != 0){
//
//            new PermissionUtils().verifyPermission(this,new String[]{
//                    android.Manifest.permission.ACCESS_FINE_LOCATION,
//                    android.Manifest.permission.ACCESS_COARSE_LOCATION});
//        } else {
//
//        }
//
//        StringUtils.getDouble("0.001");
//        StringUtils.getFloat("0.1");
//        StringUtils.getInt("1");
//        StringUtils.getLong("1");
//        StringUtils.removeLastComma("1,2,3,");
//        StringUtils.getMeterToMile(10);
//
//        String current_day = CalendarUtils.getDateinPattern(CalendarUtils.DATE_FORMAT_WITH_COMMA);
//        LogUtils.infoLog(TAG , current_day);
//
//        String dateinReqFormat = CalendarUtils.getDateinPattern("2016-09-05", CalendarUtils.DATE_FORMAT1, CalendarUtils.DATE_FORMAT_WITH_COMMA);
//        LogUtils.infoLog(TAG , dateinReqFormat);
//
//        LogUtils.debugLog(TAG, "" + ValidationUtils.validatePhoneNumber("903030303407"));

        findViewById(R.id.btnInternetSpeed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tvInternet)).setText(NetworkUtility.getInternetSpeed(MainActivity.this));
            }
        });

    }
}
