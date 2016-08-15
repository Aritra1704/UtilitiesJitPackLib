package com.arpaul.utilitieslib;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Aritra on 4/26/2016.
 */
public class CalendarUtils {
    public static final String DATETIME_FORMAT_WITH_COMMA = "dd MMM, yyyy\nhh:mm:ss aa";
    public static final String DATE_FORMAT_WITH_COMMA = "MMMM dd, yyyy";
    public static final String DATE_TIME_FORMAT = "dd-MM-yyyy'T'HH:mm:ss";
    public static final String DATE_TIME_FORMAT1 = "yyyyMMdd'T'HHmmss";
    public static final String DATE_FORMAT = "yyyy MM dd";
    public static final String DATE_FORMAT1 = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "hh:mm aa";
    public static final String TIME_SEC_FORMAT = "hh:mm:ss a";
    public static final String WEEKNAME_FORMAT = "EEE";
    public static final String MONTHNAME_FORMAT = "MMM";
    public static final String MONTHNAME_FORMAT1 = "MMMM";
    public static final String DAYNAME_FORMAT = "dd";
    public static final String YEARNAME_FORMAT = "yyyy";
    public static final String MONTH_YEAR_FORMAT = "MM-yyyy";
    public static final String MONTH_YEAR_FORMAT2 = "MMM yyyy";
    public static final String MONTH_YEAR_FORMAT3 = "MMMM yyyy";

    public static final String DATE_TIME_FORMAT_UTC = "yyyy-MM-dd HH:mm:ss Z";

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
        } else {
            String str[] = dateTime.split("-");

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

    public static String getFullMonthNameFromInt(String dateTime) {
        String reqDate = "";

        Calendar calendar = getCalendarFromString(dateTime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MONTHNAME_FORMAT1);
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

    public static int getHourFromTime(String dateTime) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(CalendarUtils.getDateFromString(dateTime, TIME_FORMAT));

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinutesFromTime(String dateTime) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(CalendarUtils.getDateFromString(dateTime, TIME_FORMAT));

        return calendar.get(Calendar.MINUTE);
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
        calendar.set(Calendar.MONTH, StringUtils.getInt(str[1]) - 1);
        calendar.set(Calendar.YEAR, StringUtils.getInt(str[0]));
        return calendar;
    }

    public static int getMonthFromString(String dateTime)
    {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(CalendarUtils.getDateFromString(dateTime, MONTH_YEAR_FORMAT));

        return (calendar.get(Calendar.MONTH) + 1);
    }

    public static Calendar getCalendarMonthFromString(String dateTime)
    {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(CalendarUtils.getDateFromString(dateTime, MONTH_YEAR_FORMAT));

        calendar.add(Calendar.MONTH, 1);
        return calendar;
    }

    public static String getCalendarMonth(Calendar calendar ) {
        String reqDate = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MONTH_YEAR_FORMAT3);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    public static int getDiffBtwDatesInDays(String todayDate,String selDate)
    {
        Calendar todaycal = Calendar.getInstance();
        Calendar selcal = Calendar.getInstance();

        todaycal.setTime(CalendarUtils.getDateFromString(todayDate, CalendarUtils.DATE_FORMAT1));
        selcal.setTime(CalendarUtils.getDateFromString(selDate, CalendarUtils.DATE_FORMAT1));

        int diff = 0;
        if(selcal.get(Calendar.YEAR) < todaycal.get(Calendar.YEAR))
            diff = -1;
        else {
            if(selcal.get(Calendar.MONTH) < todaycal.get(Calendar.MONTH))
                diff = -1;
            else {
                if(selcal.get(Calendar.DAY_OF_MONTH) < todaycal.get(Calendar.DAY_OF_MONTH)) {
                    if(selcal.get(Calendar.MONTH) > todaycal.get(Calendar.MONTH) ||
                            selcal.get(Calendar.YEAR) > todaycal.get(Calendar.YEAR))
                        diff = 1;
                    else
                        diff = -1;
                }
                else {
                    diff = 1;
                }
            }
        }

        return diff;
    }

    public static Calendar getCalendarFromDate(String date, String DATEFORMAT)
    {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(CalendarUtils.getDateFromString(date, DATEFORMAT));

        return calendar;
    }

    public static String getCurrent_Time() {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    /**
     * Get Date in the required pattern.
     * @param toPattern
     * @return
     */
    public static String getDateinPattern(String toPattern) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(toPattern);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    /**
     * Get your provided date in required format.
     * @param dateTime
     * @param fromPattern
     * @param toPattern
     * @return
     */
    public static String getDateinPattern(String dateTime, String fromPattern, String toPattern) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(CalendarUtils.getDateFromString(dateTime, fromPattern));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(toPattern);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    /**
     * Get your Calendar object from desired date.
     * @param dateTime
     * @param pattern
     * @return
     */
    public static Calendar getCalendarfromDatePattern(String dateTime, String pattern) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(CalendarUtils.getDateFromString(dateTime, pattern));

        return calendar;
    }

    public static String getDatefromCalendarPattern(Calendar calendar, String pattern) {
        String reqDate = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }
}
