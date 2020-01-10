package edu.hit.topic.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>NonCaptureTest</p>
 * <p>Description:</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年12月24日 下午1:58:24
 */
public class NonCaptureTest {
    public static void print(Object o) {
        System.out.println(o);
    }

    public static boolean regExp(String original, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(original);
        while (m.find()) print("----" + m.start() + "," + m.end() + "-" + m.group());

        return m.matches();
    }

    public static void main(String[] args) {

    }
}
