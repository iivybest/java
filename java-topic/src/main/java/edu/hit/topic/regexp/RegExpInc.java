package edu.hit.topic.regexp;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>RegExpInc</p>
 * <p>Description:</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2014年12月19日 上午9:49:42
 */
public class RegExpInc {

    @Test
    public void testReplace() {
        // replace
//		String str = "java Java JAVa JaVa IloveJAVA you hatejava wahaha";
        String str = "D:\\Users//ivybest\\照片查看器.reg";
        // CASE_INSENSITIVE - 大小写不敏感
        Pattern p = Pattern.compile("\\\\+", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        boolean matches;
        while (matches = m.find())
            // group 组，匹配的字串
            print(matches + " - (" + m.start() + "," + m.end() + ") - " + m.group());
        // 将所有的java不分大小写，全替换为大写
        print(m.replaceAll("/"));
    }

    @Test
    public void testSplit() {
        String[] spmcs = new String[]{
                "通讯费",
                "*通讯费",
                "通*讯费",
                "通**讯费",
                "通*讯*费",
                "通***讯费",
                "*14*",
                "**",
                "***",
                "****",
                "**通讯费",
                "***通讯费",
                "* *通讯费",
                "*电信服务*  通讯费",
                "*电信服务  *通讯费",
                "*电信  服务  *通讯费",
                "*电信^#@!服务*通讯费",
                "*电信服务123abc*通讯费",
                "*电信服务**通讯费",
                "**电信服务*通讯费",
                "*电信服务*通讯费"
        };
        String regex = "(\\*{1})([^\\*]*)(\\*{1})";
//		String regex = "\\*";
        Pattern p = Pattern.compile(regex);

        Matcher m = null;
        boolean matches;
        for (String spmc : spmcs) {
            m = p.matcher(spmc);
            if (matches = m.lookingAt())
                print(spmc + " - " + matches + " - (" + m.start() + "," + m.end() + ") - " + m.group());
        }
    }


    public static void main(String[] args) {
        String str = "abc";
        Pattern p = Pattern.compile("[a-z]{3,}");
        Matcher m = p.matcher(str);
        print("---------------------------- str " + m.matches());

        String str2 = "137-2254-123";
        print("----------------------------str2 " + str2);
        p = Pattern.compile("\\d{3,5}");
        m = p.matcher(str2);
        print(m.matches());        // matches()是匹配整个字符串，吃进去的字符不往外吐
        m.reset();                // 还原原字符串
        print(m.find());        // 找匹配的子字符串
        print(m.start() + " , " + m.end());        // 匹配字串的起始位置
        print(m.find());
        print(m.start() + " , " + m.end());
        print(m.find());
        print(m.start() + " , " + m.end());
        print(m.find());
        print(m.lookingAt());    // 从头开始找是否匹配的


        // 分组
        String str4 = "123ba-456cd-789ef";
        print("----------------------------str4 " + str4);
        p = Pattern.compile("(\\d{1,3})([a-z]{2})");
        m = p.matcher(str4);
        while (m.find()) {
            print("all - " + m.group()
                    + ", group1 - " + m.group(1)
                    + ", group2 - " + m.group(2));
        }

        int c = 8;
        int t = 3;
        double h = ((double) 8) / t;
        int d = (int) Math.ceil(h);
        System.out.println(d);


    }


    public static void print(Object o) {
        System.out.println(o);
    }

}
