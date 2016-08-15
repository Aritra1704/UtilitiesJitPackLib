package com.arpaul.utilitieslib;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aritra on 4/26/2016.
 */
public class StringUtils {
    public static int getInt(String integer) {
        int reqInteger = 0;

        if(integer == null || TextUtils.isEmpty(integer))
            return reqInteger;

        reqInteger = Integer.parseInt(integer);

        return reqInteger;
    }

    public static long getLong(String integer) {
        long reqInteger = 0;

        if(integer == null || TextUtils.isEmpty(integer))
            return reqInteger;

        reqInteger = Long.parseLong(integer);

        return reqInteger;
    }

    public static float getFloat(String integer) {
        float reqInteger = 0;

        if(integer == null || TextUtils.isEmpty(integer))
            return reqInteger;

        reqInteger = Float.parseFloat(integer);

        return reqInteger;
    }

    public static double getDouble(String integer) {
        double reqInteger = 0;

        if(integer == null || TextUtils.isEmpty(integer))
            return reqInteger;

        reqInteger = Double.parseDouble(integer);

        return reqInteger;
    }

    public static String getStringFormattedArray(ArrayList<String> arrString) {
        String eventDate = "";

        if(arrString == null || arrString.size() <= 0)
            return eventDate;

        eventDate = TextUtils.join(",", arrString);

        return eventDate;
    }

    public static String removeLastComma(String inputString){
        String finalStr = "";

        if(!TextUtils.isEmpty(inputString)){
            inputString = inputString.toString().trim();
        }
        if(inputString.contains(","))
            finalStr = inputString.substring(0, inputString.lastIndexOf(","));
        else
        finalStr = inputString;

        return finalStr;
    }

    public static float getMeterToMile(int meter){
        float mile = 0;

        mile = (float)((float) meter / 1609.34);

        DecimalFormat form = new DecimalFormat("0.00");
        mile = getFloat(form.format(mile));

        return mile;
    }

    public static String convertArraylistToString(List<String> list){
        String listString = "";

        for (String s : list)
        {
            listString += "\"" + s + "\",";
        }
        return listString;
    }
}
