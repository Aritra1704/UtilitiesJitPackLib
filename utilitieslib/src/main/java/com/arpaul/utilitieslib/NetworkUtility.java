package com.arpaul.utilitieslib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * Created by Aritra on 10-08-2016.
 */
public class NetworkUtility {
    /**
     * Method to check Network Connections
     * @param context
     * @return boolean value
     */
    public static boolean isConnectionAvailable(Context context) {
        if(isConnected(context))
            return true;
        else if(isWifiConnected(context))
            return true;
        else if(isNetworkConnectionAvailable(context))
            return true;
        else
            return false;
    }

    private static boolean isNetworkConnectionAvailable(Context context)
    {
        boolean isNetworkConnectionAvailable = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE/*"connectivity"*/);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo != null)
        {
            isNetworkConnectionAvailable = activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
        }
        return isNetworkConnectionAvailable;
    }

    private static boolean isWifiConnected(Context context)
    {
        boolean isNetworkConnectionAvailable = false;
        ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected()) {
            isNetworkConnectionAvailable = true;
        }
        return isNetworkConnectionAvailable;
    }

    private static boolean isConnected(Context context)
    {
        boolean isNetworkConnectionAvailable = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                isNetworkConnectionAvailable = true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                isNetworkConnectionAvailable = true;
            }
        } else {
            isNetworkConnectionAvailable = false;
        }
        return isNetworkConnectionAvailable;
    }

    public enum CONNECTION_TYPE {
        TYPE_WIFI,
        TYPE_MOBILE,
        TYPE_NOT_CONNECT
    }

    public static CONNECTION_TYPE getConnectionType(Context context){
        CONNECTION_TYPE connection_type = CONNECTION_TYPE.TYPE_NOT_CONNECT;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                connection_type = CONNECTION_TYPE.TYPE_WIFI;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                connection_type = CONNECTION_TYPE.TYPE_MOBILE;
            }
        } else {
            connection_type = CONNECTION_TYPE.TYPE_NOT_CONNECT;
        }
        return connection_type;
    }

    public static String getInternetSpeed(Context context) {
        StringBuilder internetSpeed = new StringBuilder();
        String NOCONNECTION = "NO-CONNECTION ", POOR = "POOR ", AVERAGE = "AVERAGE ", GOOD = "AVERAGE ";
        String WIFI = "WIFI ", MOBILE = "MOBILE ";
        int speed = 0;

        switch (getConnectionType(context)) {
            case TYPE_WIFI:
                WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
                speed = wifiInfo.getLinkSpeed();
                internetSpeed.append(WIFI);
                break;

            case TYPE_MOBILE:
                TelephonyManager teleMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                speed = teleMgr.getNetworkType();
                internetSpeed.append(MOBILE);
                break;

            case TYPE_NOT_CONNECT:
                speed = 0;
                break;
        }

        internetSpeed.append("network ");

        if(speed <= 0)
            internetSpeed.append(NOCONNECTION);
        else if(speed > 0 && speed < 20)
            internetSpeed.append(POOR);
        else if(speed > 20 && speed < 80)
            internetSpeed.append(AVERAGE);
        else if(speed >= 80)
            internetSpeed.append(GOOD);

        return internetSpeed.toString();
    }
}
