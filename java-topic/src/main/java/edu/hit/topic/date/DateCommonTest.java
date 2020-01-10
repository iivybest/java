package edu.hit.topic.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * DateCommonTest
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年6月3日 - 上午10:58:15
 */
public class DateCommonTest {

    @Test
    public void test_01_date() {
        Date date = new Date(); // 表示当前时间
        System.out.println(date);
    }

    @Test
    public void test_02_dateFormat() {
        Date date = new Date(); // 表示当前时间
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("1、---- " + format.format(date));
        format.applyPattern("GGG yyyy.MMM.dd  hh:mm a");
        System.out.println("2、---- " + format.format(date));
    }

    @Test
    public void test_03_calendar() {
        Calendar calendar = new GregorianCalendar();
        // 当前时间 24 小时后的时间
        calendar.add(Calendar.HOUR, 24);
        System.out.println(calendar.getTime());
        // 构造指定时间 2012/12/21 - 12:52:12
        Calendar c1 = new GregorianCalendar(2012, 11, 21, 12, 52, 12);
        System.out.println(c1.getTime());
    }

    @Test
    public void test_04_calendar() {
        TimeZone zone = TimeZone.getTimeZone("ETC/GMT+9");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = new GregorianCalendar();
        Calendar c3 = Calendar.getInstance(Locale.UK);
        Calendar c4 = new GregorianCalendar(zone);
        System.out.println(c1.getTime() + "\n"
                + c2.getTime() + "\n"
                + c3.getTime() + "\n"
                + c4.getTime() + "\n");
    }

    @Test
    public void test_05_calendar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println(sdf.format(cal.getTime()));
    }


}

















