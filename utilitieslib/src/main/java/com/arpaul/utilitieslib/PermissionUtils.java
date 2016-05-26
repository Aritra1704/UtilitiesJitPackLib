package com.arpaul.utilitieslib;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Aritra on 5/17/2016.
 */
public class PermissionUtils {

    public int checkPermission(Context context){
        int hasLocationPermission = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hasLocationPermission = context.checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION );
            if( hasLocationPermission == PackageManager.PERMISSION_GRANTED ) {
                hasLocationPermission = context.checkSelfPermission( Manifest.permission.ACCESS_COARSE_LOCATION );
            }
        }
        return hasLocationPermission;
    }

    public void verifyLocation(Context context, String[] requestedPermission){
        ActivityCompat.requestPermissions((Activity) context, requestedPermission,1);
    }
}
