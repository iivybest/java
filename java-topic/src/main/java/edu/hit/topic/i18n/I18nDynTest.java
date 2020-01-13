package edu.hit.topic.i18n;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * I18nDynTest
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年5月15日 - 上午9:07:13
 */
public class I18nDynTest {
    private String baseName;

    @Before
    public void before() {
        this.baseName = "config.i18n.Msg";
    }

    @Test
    public void test_01_dynMsg() {
        String key = "sayhi";
        // 获取指定locale
        Locale locale_cn = new Locale("zh", "CN");
        Locale locale_us = new Locale("en", "US");
        // 找到资源文件，根据locale
        ResourceBundle rb_cn = ResourceBundle.getBundle(baseName, locale_cn);
        ResourceBundle rb_us = ResourceBundle.getBundle(baseName, locale_us);
        // 获取国际化内容
        String val_cn = rb_cn.getString(key);
        String val_us = rb_us.getString(key);
        System.out
                .println(MessageFormat.format(val_cn, "李莫愁", 18) + "\n" + MessageFormat.format(val_us, "Ronaldo", 30));
    }

    private String getFormatDate(DateFormat df, Date date) {
        return df.format(date);
    }

    @Test
    public void test_02_dynDateFormat() {
        Date date = new Date();
        DateFormat df = null;
        // 自定义日期格式化样式
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(this.getFormatDate(df, date));
        // 输出指定国家的全部日期
        df = DateFormat.getDateInstance(DateFormat.FULL, Locale.US);
        System.out.println(this.getFormatDate(df, date));
        // 指定国家时间
        df = DateFormat.getTimeInstance(DateFormat.FULL, Locale.CHINA);
        System.out.println(this.getFormatDate(df, date));
        // 输出指定国家的日期、时间
        df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, Locale.TAIWAN);
        System.out.println(this.getFormatDate(df, date));
    }

}
