package edu.hit.topic.regexp.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>ReplaceChar</p>
 * <p>Description:</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年12月19日 下午4:06:50
 */
public class ReplaceChar {
    /*
     * 将偶数java小写，奇数java大写
     */
    public static void main(String[] args) {
        String str = "java Java JAVa JaVa IloveJAVA you hatejava wahaha";
        Pattern p = Pattern.compile("ja|va", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (m.find()) {
            ++i;
            if (i % 2 != 0) {
                // appendReplacement - 将匹配到当前位置str，替换为制定字符串，存入sb中
                m.appendReplacement(sb, "JAVA");
            } else {
                m.appendReplacement(sb, "java");
            }
            print("----->" + i + " - " + sb);
        }
        m.appendTail(sb);    // 将匹配完后的小尾巴存入sb中
        // 将所有的java不分大小写，全替换为大写
        print(sb);
    }


    public static void print(Object o) {
        System.out.println(o);
    }
}
