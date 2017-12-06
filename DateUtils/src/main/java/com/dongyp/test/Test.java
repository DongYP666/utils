package com.dongyp.test;

import com.dongyp.dateUtils.DateUtils;
import com.dongyp.vo.YearWeekVo;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Copyright (C), 2014-2015, 深圳云集智造系统技术有限公司
 *
 * @Title:
 * @Description:
 * @Author by dongyp
 * @date on 2017/12/5
 */
public class Test {

    public static final String DATA_TIME_FORMAT = "E yyyy-MM-dd HH:mm:ss.SSS";

    public static void main(String[] args) {

        //创建用时间表示某个瞬间的对象，并输出 -----------------------------------
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,11,5,13,57,0);//calendar month 0 - 11

        DateTime dateTime = new DateTime(2017,12,5,13,57,0);

        SimpleDateFormat sdf = new SimpleDateFormat(DATA_TIME_FORMAT);
        System.out.println(sdf.format(calendar.getTime()));
        System.out.println(dateTime.toString(DATA_TIME_FORMAT));
        System.out.println();

        //向一个瞬间加上90，并输出结果 -----------------------------------
        calendar.add(Calendar.DAY_OF_MONTH,90);

        dateTime = dateTime.plusDays(90);

        System.out.println(sdf.format(calendar.getTime()));
        System.out.println(dateTime.toString(DATA_TIME_FORMAT));
        System.out.println();

        //距离45天之后的某天在下一个月的当前周的最后一天的日期 -----------------------------------
        dateTime = dateTime.plusDays(45).plusMonths(1).dayOfWeek().withMaximumValue();

        System.out.println(dateTime.toString(DATA_TIME_FORMAT));
        System.out.println();

        //Joda 和 JDK 互操作性 -----------------------------------
        calendar.setTime(dateTime.toDate());

        System.out.println(sdf.format(calendar.getTime()));
        System.out.println();

        //希望获得任何一年中的第 11 月的第一个星期二的日期，而这天必须是在这个月的第一个星期一之后 -----------------------------------
        LocalDate now = new LocalDate();
        LocalDate electionDate = now
                .monthOfYear()
                .setCopy(11)        // November
                .dayOfMonth()       // Access Day Of Month Property
                .withMinimumValue() // Get its minimum value
                .plusDays(6)        // Add 6 days
                .dayOfWeek()        // Access Day Of Week Property
                .setCopy(1)         // Set to Monday(1) (it will round down)
                .plusDays(1);       // Gives us Tuesday

        System.out.println(electionDate.toString(DATA_TIME_FORMAT));
        System.out.println();

        DateTime testDateTime = new DateTime();
        YearWeekVo yearWeekVo = DateUtils.getYeaWeek(testDateTime.toDate());
        System.out.println(yearWeekVo.getClass().getName() + ", \n"
                + sdf.format(yearWeekVo.getWeekBeginDate()) + " -- " + sdf.format(yearWeekVo.getWeekEndDate()) + " \n"
                + yearWeekVo.getVal());
    }
}
