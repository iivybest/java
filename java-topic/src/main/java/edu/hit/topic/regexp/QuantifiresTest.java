package edu.hit.topic.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>QuantifiresTest</p>
 * <p>Description:</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年12月24日 上午10:43:32
 */
public class QuantifiresTest {
    public static void print(Object o) {
        System.out.println(o);
    }

    public static boolean regExp(String original, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(original);
        while (m.find()) {
            print("========" + m.start() + "," + m.end() + "-" + m.group());
        }

        return m.matches();
    }

    public static void main(String[] args) {
        String data = "aaaa5bbbb6";
//		String data = "aaaa5bbbb66";

        /*
         * Greedy quantifiers
         * greedy 见到{3,10}，一次会吞进10个字符进行匹配，往后匹配第11个字符时，发现不匹配，
         * 吐出一个字符在进行匹配，直到匹配上为止
         */
        String greedyRegEx = "(.{3,10})\\d";
        print("----greedy-- " + regExp(data, greedyRegEx));

        /*
         * Reluctant quantifiers
         * reluctant 见到{3,10}，一次吞进3个字符进行匹配，往后匹配第四个字符时，发现不匹配，
         * 在吞进一个继续匹配，直到匹配为止
         */
        String reluctantRegEx = "(.{3,10}?)\\d";
        print("----reluctant-- " + regExp(data, reluctantRegEx));

        /*
         * Possessive quantifiers
         * possessive 见到{3,10}，一次吞进10个字符，往后匹配时若不匹配，不吐出，直接退出
         */
        String possessiveRegEx = "(.{3,10}+)\\d";
        print("----possessive-- " + regExp(data, possessiveRegEx));
    }
}
