package com.arpaul.utilitieslib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Aritra on 4/26/2016.
 */
public class CalendarUtils {
    private static final String DATETIME_FORMAT_WITH_COMMA = "dd MMM, yyyy\nhh:mm:ss aa";
    private static final String DATE_FORMAT_WITH_COMMA = "MMM dd, yyyy";
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy'T'HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy MM dd";
    private static final String DATE_FORMAT1 = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "hh:mm aa";
    private static final String WEEKNAME_FORMAT = "EEE";
    private static final String MONTHNAME_FORMAT = "MMM";
    private static final String DAYNAME_FORMAT = "dd";
    private static final String YEARNAME_FORMAT = "yyyy";
    private static final String MONTH_YEAR_FORMAT = "MM-yyyy";

    public static String getCommaFormattedDateTime(String dateTime) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();

        if (dateTime.contains("T")){
            String date = dateTime.split("T")[0];
            String str[] = date.split("-");

            calendar.set(Calendar.YEAR,StringUtils.getInt(str[0]));
            calendar.set(Calendar.MONTH,StringUtils.getInt(str[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH,StringUtils.getInt(str[2]));

            String time = dateTime.split("T")[1];
            String strTime[] = time.split(":");
            calendar.set(Calendar.HOUR_OF_DAY,StringUtils.getInt(strTime[0]));
            calendar.set(Calendar.MINUTE,StringUtils.getInt(strTime[1]));
            calendar.set(Calendar.SECOND,StringUtils.getInt(strTime[2]));
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_FORMAT_WITH_COMMA);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static String getCommaFormattedDate(String dateTime) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();

        if (dateTime.contains("T")){
            String date = dateTime.split("T")[0];
            String str[] = date.split("-");

            calendar.set(Calendar.DAY_OF_MONTH,StringUtils.getInt(str[2]));
            calendar.set(Calendar.MONTH,StringUtils.getInt(str[1]) - 1);
            calendar.set(Calendar.YEAR,StringUtils.getInt(str[0]));
        } else {
            String str[] = dateTime.split("-");

            calendar.set(Calendar.DAY_OF_MONTH,StringUtils.getInt(str[2]));
            calendar.set(Calendar.MONTH,StringUtils.getInt(str[1]) - 1);
            calendar.set(Calendar.YEAR,StringUtils.getInt(str[0]));
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_WITH_COMMA);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static String getDateFormatFromDateTime(String dateTime) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();

        if (dateTime.contains("T")){
            String date = dateTime.split("T")[0];
            String str[] = date.split("-");

            calendar.set(Calendar.DAY_OF_MONTH,StringUtils.getInt(str[2]));
            calendar.set(Calendar.MONTH,StringUtils.getInt(str[1]) - 1);
            calendar.set(Calendar.YEAR,StringUtils.getInt(str[0]));
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT1);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static Calendar getCalendarFromDateTime(String dateTime) {

        Calendar calendar = Calendar.getInstance();

        if (dateTime.contains("T")){
            String date = dateTime.split("T")[0];
            String str[] = date.split("-");

            calendar.set(Calendar.DAY_OF_MONTH,StringUtils.getInt(str[2]));
            calendar.set(Calendar.MONTH,StringUtils.getInt(str[1]) - 1);
            calendar.set(Calendar.YEAR,StringUtils.getInt(str[0]));
        }
        return calendar;
    }

    public static String getCurrentDateTime() {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static String getDateTime(String date) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(StringUtils.getLong(date));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static int getDiffBtwDatesInMinutes(String startDate,String endDate)
    {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.setTime(CalendarUtils.getDateFromString(startDate, CalendarUtils.DATE_TIME_FORMAT));
        calendar2.setTime(CalendarUtils.getDateFromString(endDate, CalendarUtils.DATE_TIME_FORMAT));

        long milliseconds1 = calendar1.getTimeInMillis();
        long milliseconds2 = calendar2.getTimeInMillis();

        long diff = milliseconds2 - milliseconds1;
        int diffMins = (int) (diff / (60 * 1000));

        return diffMins;
    }

    public static int getDiffBtwTimeInMinutes(String startTime,String endTime)
    {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.setTime(CalendarUtils.getDateFromString(startTime, CalendarUtils.TIME_FORMAT));
        calendar2.setTime(CalendarUtils.getDateFromString(endTime, CalendarUtils.TIME_FORMAT));

        long milliseconds1 = calendar1.getTimeInMillis();
        long milliseconds2 = calendar2.getTimeInMillis();

        long diff = milliseconds2 - milliseconds1;
        int diffMins = (int) (diff / (60 * 1000));

        return diffMins;
    }

    public static Date getDateFromString(String date, String pattern)
    {
        Date dateObj = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try
        {
            dateObj = sdf.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dateObj;
    }

    public static String getCurrentDate() {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static String getCurrent_Date() {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT1);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static String getDateFromthisDay(int day,int month,int year) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.YEAR,year);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT1);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static String getDateFromInt(int day,int month,int year) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.YEAR,year);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_WITH_COMMA);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static Calendar getCalendarFromInt(int day,int month,int year) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.YEAR,year);

        return calendar;
    }

    public static String getDateFormatFromInt(int day,int month,int year) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.YEAR,year);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT1);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static String getTimeFromInt(int hourofDay,int minutes) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hourofDay);
        calendar.set(Calendar.MINUTE,minutes);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static String getCalendarDateTime(Calendar calendar ) {
        String reqDate = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT1);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static String getWeekNameFromInt(int dayOfWeek) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,dayOfWeek);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(WEEKNAME_FORMAT);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static String getDayNameFromInt(String dateTime) {
        String reqDate = "";

        Calendar calendar = getCalendarFromString(dateTime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DAYNAME_FORMAT);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static String getMonthNameFromInt(String dateTime) {
        String reqDate = "";

        Calendar calendar = getCalendarFromString(dateTime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MONTHNAME_FORMAT);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static String getYearNameFromInt(String dateTime) {
        String reqDate = "";

        Calendar calendar = getCalendarFromString(dateTime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YEARNAME_FORMAT);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static Calendar getCalendarFromString(String dateTime){
        Calendar calendar = Calendar.getInstance();
        String str[];
        if (dateTime.contains("T")){
            String date = dateTime.split("T")[0];
            str = date.split("-");
        } else
            str = dateTime.split("-");

        calendar.set(Calendar.DAY_OF_MONTH,StringUtils.getInt(str[2]));
        calendar.set(Calendar.MONTH,StringUtils.getInt(str[1]) - 1);
        calendar.set(Calendar.YEAR, StringUtils.getInt(str[0]));
        return calendar;
    }

    public static int getMonthFromString(String dateTime)
    {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(CalendarUtils.getDateFromString(dateTime, MONTH_YEAR_FORMAT));

        return (calendar.get(Calendar.MONTH) + 1);
    }
}
