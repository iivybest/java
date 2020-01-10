package edu.hit.topic.regexp.demo;

/**
 * <p>GeneralRegExp</p>
 * <p>Description:</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年12月19日 上午10:33:43
 */
public class GeneralRegExp {
    public static void main(String[] args) {
        /*
         * white lines
         */
        String str = "original hell regexp";
        print("-----------------------------white lines");
        String str1 = " \t\r";
        print(str1.matches("^\\s*[\\n|\\r]$"));

        /*
         * Email
         */
        print("-----------------------------email");
        String str2 = "miao.xl-_@live.cn";
        print(str2.matches("^[\\w\\.\\-_]+@[\\w\\.\\-_]+\\.[\\w]+$"));

    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
