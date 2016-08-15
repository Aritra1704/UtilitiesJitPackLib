package com.arpaul.utilitieslib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Aritra on 4/26/2016.
 */
public class UnCaughtException implements Thread.UncaughtExceptionHandler {
    private static final String RECIPIENT = "bug.param@gmail.com";
    private Thread.UncaughtExceptionHandler previousHandler;
    private Context context;
    private static Context context1;
    private  String appName;

    public UnCaughtException(Context ctx, String s, String appName) {
        context = ctx;
        context1 = ctx;
        this.appName = appName;
    }

    private StatFs getStatFs() {
        File path = Environment.getDataDirectory();
        return new StatFs(path.getPath());
    }

    private long getAvailableInternalMemorySize(StatFs stat) {
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    private long getTotalInternalMemorySize(StatFs stat) {
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    private void addInformation(StringBuilder message) {
        message.append("Locale: ").append(Locale.getDefault()).append('\n');
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi;
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            message.append("Version: ").append(pi.versionName).append('\n');
            message.append("Package: ").append(pi.packageName).append('\n');
        } catch (Exception e) {
            Log.e("CustomExceptionHandler", "Error", e);
            message.append("Could not get Version information for ").append(context.getPackageName());
        }
        message.append("Phone Model: ").append(android.os.Build.MODEL).append('\n');
        message.append("Android Version: ").append(android.os.Build.VERSION.RELEASE).append('\n');
        message.append("Board: ").append(android.os.Build.BOARD).append('\n');
        message.append("Brand: ").append(android.os.Build.BRAND).append('\n');
        message.append("Device: ").append(android.os.Build.DEVICE).append('\n');
        message.append("Host: ").append(android.os.Build.HOST).append('\n');
        message.append("ID: ").append(android.os.Build.ID).append('\n');
        message.append("Model: ").append(android.os.Build.MODEL).append('\n');
        message.append("Product: ").append(android.os.Build.PRODUCT).append('\n');
        message.append("Type: ").append(android.os.Build.TYPE).append('\n');
        StatFs stat = getStatFs();
        message.append("Total Internal memory: ").append(getTotalInternalMemorySize(stat)).append('\n');
        message.append("Available Internal memory: ").append(getAvailableInternalMemorySize(stat)).append('\n');
    }

    public void uncaughtException(Thread t, Throwable e) {
        try {
            StringBuilder report = new StringBuilder();
            Date curDate = new Date();
            report.append("Error Report collected on : ").append(curDate.toString()).append('\n').append('\n');
            report.append("Informations :").append('\n');
            addInformation(report);
            report.append('\n').append('\n');
            report.append("Stack:\n");
            final Writer result = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(result);
            e.printStackTrace(printWriter);
            report.append(result.toString());
            printWriter.close();
            report.append('\n');
            report.append("****  End of current Report ***");
            Log.e(UnCaughtException.class.getName(),"Error while sendErrorMail" + report);
            sendErrorMail(report);
        } catch (Throwable ignore) {
            Log.e(UnCaughtException.class.getName(),"Error while sending error e-mail", ignore);
        }
    }

    //Method to send email
    public void sendErrorMail(final StringBuilder errorContent) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        new Thread() {
            @Override
            public void run() {
                try
                {
                    Looper.prepare();
                    builder.setTitle("Sorry...!");
                    builder.create();
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    System.exit(0);
                                }
                            });
                    builder.setPositiveButton("Send Mail", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                                    String subject = "Param Solutions Log.";
                                    StringBuilder body = new StringBuilder("");
                                    body.append(errorContent).append('\n').append('\n');
                                    sendIntent.setType("message/rfc822");
                                    sendIntent.putExtra(Intent.EXTRA_EMAIL,new String[] { RECIPIENT });
                                    sendIntent.putExtra(Intent.EXTRA_TEXT,body.toString());
                                    sendIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
                                    sendIntent.setType("message/rfc822");
                                    context1.startActivity(sendIntent);
                                    System.exit(0);
                                }
                            });
                    builder.setMessage("Sorry for inconvenience. " + appName + " app stopped working.");
                    builder.show();
                    Looper.loop();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }.start();
    }
}
