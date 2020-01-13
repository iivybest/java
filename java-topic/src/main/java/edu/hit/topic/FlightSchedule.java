package edu.hit.topic;

import org.junit.Test;

/**
 * <p>FlightSchedule</p>
 * <p>Description :
 * 班期-航班在一周内航班的安排叫班期
 * 实现-航班系统判断某天是否有航班
 * 已知-135有航班，2467无
 * </p>
 * <p>
 * Analysis :
 * 对于某一天而言，航班只有两种状态'Y/N'
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @date 2015年6月11日 - 下午3:24:46
 * @versino 1.0
 */
public class FlightSchedule {
    /*
     * 实现1-利用boolean[]
     * # 0为sunday
     */
    @Test
    public void test_01_impl01() {
        boolean[] schedule = new boolean[]{false, true, false, true, false, true, false};
        int weekDay = 3;
        boolean have = schedule[weekDay];
        System.out.println("在周 " + weekDay + " 是否有航班 :" + have);

    }

    @Test
    public void test_02_impl02() {
        byte schedule = (byte) 21;
        byte weekDay = (byte) 5;
        boolean have = (schedule & (byte) Math.pow(2, weekDay - 1)) != 0;
        System.out.println("在周 " + weekDay + " 是否有航班 :" + have);
    }


}
