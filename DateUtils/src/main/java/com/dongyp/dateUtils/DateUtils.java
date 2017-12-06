package com.dongyp.dateUtils;

import com.dongyp.vo.YearMonthVo;
import com.dongyp.vo.YearWeekVo;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Copyright (C), 2014-2015, 深圳云集智造系统技术有限公司
 *
 * @Title:
 * @Description:
 * @Author by dongyp
 * @date on 2017/12/5
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public DateUtils() {
    }

    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String formatYMD(Date date) {
        return format(date, DATE_FORMAT);
    }

    public static String formatYMDHMS(Date date) {
        return format(date, DATE_TIME_FORMAT);
    }

    public static String formatHMS(Date date) {
        return format(date, TIME_FORMAT);
    }

    public static Date parse(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try {
            return sdf.parse(str);
        } catch (ParseException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static Date parseYMD(String str) {
        return parse(str, DATE_FORMAT);
    }

    public static Date parseYMDHMS(String str) {
        return parse(str, DATE_TIME_FORMAT);
    }

    public static String curDateTime() {
        return format(new Date(), DATE_TIME_FORMAT);
    }

    public static String curDate() {
        return format(new Date(), DATE_FORMAT);
    }

    public static String curTime() {
        return format(new Date(), TIME_FORMAT);
    }

    public static final Date getTime() {
        return new Date();
    }

    public static final Date getDate() {
        return LocalDate.now().toDate();
    }

    public static final Date formatDate(String dateStr, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);

        try {
            Date date = formatter.parse(dateStr);
            return date;
        } catch (Exception var4) {
            return null;
        }
    }

    public static final Date getFirstDay(Date date) {
        return (new LocalDate(date)).dayOfMonth().withMinimumValue().toDate();
    }

    public static final Date getLastedDay(Date date) {
        return (new LocalDate(date)).dayOfMonth().withMaximumValue().toDate();
    }

    public static final Integer getYear(Date date) {
        return Integer.valueOf((new DateTime(date)).getYear());
    }

    public static final Integer getMonth(Date date) {
        return Integer.valueOf((new DateTime(date)).getMonthOfYear());
    }

    public static final boolean eqCompare(Date date1, Date date2) {
        return date1.getTime() == date2.getTime();
    }

    public static final boolean gtCompare(Date date1, Date date2) {
        return date1.getTime() > date2.getTime();
    }

    public static final boolean geCompare(Date date1, Date date2) {
        return date1.getTime() >= date2.getTime();
    }

    public static final boolean ltCompare(Date date1, Date date2) {
        return date1.getTime() < date2.getTime();
    }

    public static final boolean leCompare(Date date1, Date date2) {
        return date1.getTime() <= date2.getTime();
    }

    public static Date dayAdd(Date myDate, int dayNum) {
        return (new DateTime(myDate)).plusDays(dayNum).toDate();
    }

    public static Date minuteAdd(Date myDate, int num) {
        return (new DateTime(myDate)).plusMinutes(num).toDate();
    }

    public static Date monthAdd(Date myDate, int num) {
        return (new DateTime(myDate)).plusMonths(num).toDate();
    }

    public static int getWeek(Date date) {
        return (new DateTime(date)).getWeekOfWeekyear();
    }

    public static YearWeekVo getYeaWeek(Date date) {
        DateTime dateTime = new DateTime(date);
        LocalDate weekStart = dateTime.toLocalDate().plusDays(1 - dateTime.getDayOfWeek());
        LocalDate weekEnd = dateTime.toLocalDate().plusDays(7 - dateTime.getDayOfWeek());
        DateTime endWeekTime = new DateTime(weekEnd.getYear(), weekEnd.getMonthOfYear(), weekEnd.getDayOfMonth(), 11, 59, 59);
        return new YearWeekVo(Integer.valueOf(dateTime.getYear() + "" + String.format("%02d", new Object[]{Integer.valueOf(dateTime.getWeekOfWeekyear())})).intValue(), weekStart.toDate(), endWeekTime.toDate());
    }

    public static YearMonthVo getYearMonth(Date date) {
        DateTime dateTime = new DateTime(date);
        LocalDate firstDay = (new LocalDate(dateTime)).dayOfMonth().withMinimumValue();
        LocalDate lastDay = (new LocalDate(dateTime)).dayOfMonth().withMaximumValue();
        return new YearMonthVo(Integer.valueOf(dateTime.getYear() + "" + String.format("%02d", new Object[]{Integer.valueOf(dateTime.getMonthOfYear())})), firstDay.toDate(), lastDay.toDate());
    }
}
