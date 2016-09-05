package com.arpaul.utilitieslib;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Aritra on 4/26/2016.
 */
public class LogUtils {
    public static boolean isLogEnable = true;

    public LogUtils(boolean isEnabled){
        isLogEnable = isEnabled;
    }

    public static void errorLog(String tag, String msg)
    {
        if(isLogEnable)
            Log.e(""+tag, ""+msg);
    }

    public static void infoLog(String tag, String msg)
    {
        if(isLogEnable)
            Log.i(tag, msg);
    }

    public static void debugLog(String tag, String msg)
    {
        if(isLogEnable)
            Log.d(tag, msg);
    }

    public static void printMessage(String msg)
    {
        if(isLogEnable)
            System.out.println(msg);
    }


    public static void setLogEnable(boolean isEnable)
    {
        isLogEnable = isEnable;
    }
    /**
     * This method stores InputStream data into a file at specified location
     * @param is
     */
    public static void convertResponseToFile(InputStream is) throws IOException
    {
        BufferedInputStream bis = new BufferedInputStream(is);
        FileOutputStream fos = new FileOutputStream("/sdcard/Response.xml");
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte byt[] = new byte[1024];
        int noBytes;

        while((noBytes = bis.read(byt)) != -1)
            bos.write(byt,0,noBytes);

        bos.flush();
        bos.close();
        fos.close();
        bis.close();
    }
    /**
     * This method stores data in String into a file at specified location
     * @param is
     */
    public static void convertRequestToFile(String is) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(new File("/sdcard/Request.xml"));
        fos.write(is.getBytes());
        fos.close();
    }

    /** This method will read data from the inputStream and return as StringBuffer
     * @param inpStrData
     */
    public static StringBuffer getDataFromInputStream(InputStream inpStrData)
    {
        if(inpStrData != null)
        {
            try
            {
                BufferedReader brResp = new BufferedReader(new InputStreamReader(inpStrData));
                String stringTemporaryVariable;
                StringBuffer sbResp = new StringBuffer();

                //Converts response as a StringBuffer
                while((stringTemporaryVariable = brResp.readLine()) != null)
                    sbResp.append(stringTemporaryVariable);

                brResp.close();
                inpStrData.close();

                return sbResp;
            }
            catch(Exception e)
            {
                LogUtils.errorLog("LogUtils", "Exception occurred in getDataFromInputStream(): "+e.toString());
            }
        }
        return null;
    }
}
