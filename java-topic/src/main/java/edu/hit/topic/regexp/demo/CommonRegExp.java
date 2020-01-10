package edu.hit.topic.regexp.demo;

/**
 * <p>CommonRegExp</p>
 * <p>Description:</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年12月19日 上午9:53:30
 */
public class CommonRegExp {
    public static void main(String[] args) {
        String str1 = "a";
        print("--------------------------- " + str1);
        print(str1.matches("^[abc]$"));
        print(str1.matches("[^abc]"));
        print(str1.matches("[abc]"));
        print(str1.matches("[a-zA-Z]"));
        print(str1.matches("[a-z]|[A-Z]"));
        print(str1.matches("[a-z[A-Z]]"));
        print(str1.matches("[a-z&&[RFG]]"));


        String str2 = " \n\r\t";
        print("--------------------------- str2");
        print(str2.matches("\\s{4}"));
        print("^".matches("[&^#%]+"));
        /*
         * \\ 表示一个char"\",
         * 匹配的RegExp位"\\", 所以在java里表示为\\\\
         */
        print("\\".matches("\\\\"));

        // POSIX style
        String str3 = "abc";
        print("--------------------------- str3");
        print(str3.matches("^[a-z]{1,}"));
        print(str3.matches("^\\p{Lower}{1,}"));

        //Boundary
        String str4 = "hello sir";
        print("--------------------------- str4 " + str4);
        print(str4.matches("^h.*"));
        print(str4.matches(".*ir$"));
        // 在 sir前面有一个单词边界
        print(str4.matches("^h[a-z]*\\b.*"));
        print("hellosir".matches("^h[a-z]{1,4}\\b.*"));

        //
        String str5 = "ccc 888c";
        print("--------------------------- str5 " + str5);
        print(str5.matches("^.*\\b\\d{1,3}.*$"));

    }

    public static void print(Object o) {
        System.out.println(o);
    }

}
