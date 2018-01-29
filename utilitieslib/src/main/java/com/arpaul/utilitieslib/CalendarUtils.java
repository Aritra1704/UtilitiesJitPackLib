package com.arpaul.utilitieslib;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Aritra on 4/26/2016.
 */
public class CalendarUtils {
    public static final String DATETIME_FORMAT_WITH_COMMA = "dd MMM, yyyy\nhh:mm:ss aa";
    public static final String DATE_FORMAT_WITH_COMMA = "MMMM dd, yyyy";
    public static final String DATE_TIME_FORMAT_T = "dd-MM-yyyy'T'HH:mm:ss";
    public static final String DATE_TIME_FORMAT1 = "yyyyMMdd'T'HHmmss";
    public static final String DATE_FORMAT = "yyyy MM dd";
    public static final String DATE_FORMAT1 = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "hh:mm aa";
    public static final String TIME_SEC_FORMAT = "hh:mm:ss a";
    public static final String TIME_HOUR_MINUTE = "hh:mm";
    public static final String TIME_MINUTE = "mm";
    public static final String WEEKNAME_FORMAT = "EEE";
    public static final String MONTHNAME_FORMAT = "MMM";
    public static final String MONTHNAME_FORMAT1 = "MMMM";
    public static final String DAYNAME_FORMAT = "dd";
    public static final String YEARNAME_FORMAT = "yyyy";
    public static final String HOUR_24_FORMAT = "HH";
    public static final String MONTH_YEAR_FORMAT = "MM-yyyy";
    public static final String MONTH_YEAR_FORMAT2 = "MMM yyyy";
    public static final String MONTH_YEAR_FORMAT3 = "MMMM yyyy";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_UTC = "yyyy-MM-dd HH:mm:ss Z";

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
     * Get your provided time in millis in required format.
     * @param time
     * @param toPattern
     * @return
     */
    public static String getDatefromTimeinMilliesPattern(long time, String toPattern) {
        String reqDate = "";

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(toPattern);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    /**
     * Get Time in Millis
     * @param dateTime
     * @param fromPattern
     * @return
     */
    public static long getTimeinMilliesPattern(String dateTime, String fromPattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(CalendarUtils.getDateFromString(dateTime, fromPattern));

        return calendar.getTimeInMillis();
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

    /**
     * Get date from Calendar in desired pattern.
     * @param calendar
     * @param pattern
     * @return
     */
    public static String getDatefromCalendarPattern(Calendar calendar, String pattern) {
        String reqDate = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        reqDate = simpleDateFormat.format(calendar.getTime());

        return reqDate;
    }

    /**
     * Get differemce between dates in pattern
     * @param startDate
     * @param endDate
     * @param pattern
     * @return
     */
    public static int getDiffBtwDatesPattern(String startDate,String endDate,DIFF_TYPE type, String pattern)
    {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.setTime(CalendarUtils.getDateFromString(startDate, pattern));
        calendar2.setTime(CalendarUtils.getDateFromString(endDate, pattern));

        long milliseconds1 = calendar1.getTimeInMillis();
        long milliseconds2 = calendar2.getTimeInMillis();

        long diff = milliseconds2 - milliseconds1;

        long divisor = 0;
        switch (type){
            case TYPE_SECONDS:
                divisor = 1000;
                break;

            case TYPE_MINUTE:
                divisor = 60 * 1000;
                break;

            case TYPE_HOUR:
                divisor = 60 * 60 * 1000;
                break;

            case TYPE_DAY:
                divisor = 24 * 60 * 60 * 1000;
                break;

            case TYPE_WEEK:
                divisor = 7 * 24 * 60 * 60 * 1000;
                break;
        }

        int diffMins = (int) (diff / divisor);

        return diffMins;
    }

    public enum DIFF_TYPE {
        TYPE_SECONDS,
        TYPE_MINUTE,
        TYPE_HOUR,
        TYPE_DAY,
        TYPE_WEEK
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

    /**
     * Get different TimeZone format in required timezone.
     * For eg. Actual: 2016-11-01T23:59:00-04:00 to Final: 2016-11-01T16:59:00-1100
     * Eg. fromPattern: "yyyy-MM-dd'T'HH:mm:ssZ"
     * Eg. toTimeZone; Use method getCurrentTimeZone()
     * @param dateTime
     * @param fromPattern
     * @param toTimeZone
     * @return
     */
    public static String getTimeInTimeZone(String dateTime, String fromPattern, String toTimeZone) {
        String result = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fromPattern);
            Date date = simpleDateFormat.parse(dateTime);

            TimeZone destTz = TimeZone.getTimeZone(toTimeZone);
            simpleDateFormat.setTimeZone(destTz);
            result = simpleDateFormat.format(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * Get Time in required timezone.
     * @param dateTime
     * @param fromPattern
     * @param fromTimeZone
     * @param toTimeZone
     * @return
     */
    public static String getTimeInTimeZone(String dateTime, String fromPattern, String fromTimeZone, String toTimeZone) {
        String result = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fromPattern);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(fromTimeZone));
            Date date = simpleDateFormat.parse(dateTime);

            TimeZone destTz = TimeZone.getTimeZone(toTimeZone);
            simpleDateFormat.setTimeZone(destTz);
            result = simpleDateFormat.format(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * Get current timezone in short format.
     * @return
     */
    public static String getCurrentTimeZone() {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        return tz.getDisplayName(false, TimeZone.SHORT);
    }
}
