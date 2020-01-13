package edu.hit.topic.i18n;

import org.junit.Before;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年5月14日 - 上午10:36:47
 */
public class I18nCommonTest {
    private String baseName;

    @Before
    public void before() {
        this.baseName = "config.i18n.Msg";
    }

    @Test
    public void test_01_getLocale() {
        Locale local = Locale.getDefault();
        System.out.println(local.getLanguage()); // zh
        Locale locale_en = new Locale(Locale.ENGLISH.getLanguage());
        System.out.println(Locale.ENGLISH.getLanguage()); // en
        System.out.println(locale_en.getLanguage()); // en
        Locale locale_en_gb = new Locale("en", "GB");
        System.out.println(locale_en_gb.getCountry()); // GB
    }

    @Test
    public void test_02_i18n() {
        String key = "sayhello";
        // 获取指定locale
        Locale locale_cn = new Locale("zh", "CN");
        Locale locale_us = new Locale("en", "US");
        // 找到资源文件，根据locale
        ResourceBundle rb_cn = ResourceBundle.getBundle(baseName, locale_cn);
        ResourceBundle rb_us = ResourceBundle.getBundle(baseName, locale_us);
        System.out.println(rb_cn.getString(key) + "\n" + rb_us.getString(key));
    }

    @Test
    public void test_03_clsRes() {
        Locale locale = new Locale("en", "GB");
        ResourceBundle rb = ResourceBundle.getBundle(baseName, locale);
        String val_sayhi = rb.getString("sayhi");
        System.out.println(rb.getString("sayhello") + "\n" + MessageFormat.format(val_sayhi, "Rojo", 25));
    }
}
