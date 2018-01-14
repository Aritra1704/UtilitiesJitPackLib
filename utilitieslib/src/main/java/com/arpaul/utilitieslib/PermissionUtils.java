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

    /**
     * Checks runtime permission if user have already provided it.
     * @param context
     * @param requestPermission
     * @return
     */
    public int checkPermission(Context context, String[] requestPermission){
        int hasPermission = PackageManager.PERMISSION_DENIED;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < requestPermission.length; i++) {
                hasPermission = context.checkSelfPermission(requestPermission[i]);
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    break;
                }
            }
        }
        return hasPermission;
    }

    /**
     * Requests runtime permission.
     * @param context
     * @param requestedPermission
     */
    public void requestPermission(Context context, String[] requestedPermission){
        ActivityCompat.requestPermissions((Activity) context, requestedPermission,1);
    }

    /**
     * Requests runtime permission with request code for easy travesal
     * @param context
     * @param requestedPermission
     * @param requestCode
     */
    public void requestPermission(Context context, String[] requestedPermission, int requestCode){
        ActivityCompat.requestPermissions((Activity) context, requestedPermission, requestCode);
    }
}
