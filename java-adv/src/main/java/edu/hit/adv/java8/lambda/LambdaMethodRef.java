package edu.hit.adv.java8.lambda;

import edu.hit.adv.java8.functionalinterface.PrintAble;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * <p>LambdaMethodRef</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年7月20日-上午10:28:33
 */
public class LambdaMethodRef {

    /**
     * getRodomString
     * 打印文字
     *
     * @param @return
     * @return String
     * @throws
     */
    public static void staticPrint(String... args) {
        new LambdaMethodRef().print(args);
    }

    public static void main(String[] args) {

        PrintAble printer = (String[] s) -> {
            StringBuilder sb = new StringBuilder("[");
            if (args != null && args.length > 0)
                // 更简洁的 for 循环写法，可以没有方法体
                for (int i = 0; i < args.length; sb.append(args[i++]).append(",")) ;
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            System.out.println(sb.toString());
        };
        // 将上述方法体内函数封装到 print 方法中，方便调用
        PrintAble printer_0 = (String[] s) -> LambdaMethodRef.staticPrint(s);
        // 静态方法引用，类引用
        PrintAble printer_1 = LambdaMethodRef::staticPrint;
        // 普通方法引用，对象引用
        PrintAble printer_2 = new LambdaMethodRef()::print;

        printer_0.print("a", "b");
        printer_1.print("c", "d");
        printer_2.print("e", "f");

        String[] matchedsubcollection = filterString(
                new String[]{"1", "20", "19", "-8"},
                e -> Integer.valueOf(e) > 10
        );
        printer_1.print(matchedsubcollection);

    }

    /**
     * : filterString
     * : 使用断言型接口过滤String集合
     *
     * @param strs
     * @param predicate
     * @return List<String>
     */
    public static String[] filterString(String[] strs, Predicate<String> predicate) {
        if (strs == null || predicate == null) return null;
        List<String> result = new ArrayList<>();
        for (String e : strs) if (predicate.test(e)) result.add(e);
        String[] resultArr = new String[result.size()];
        result.toArray(resultArr);
        return resultArr;
    }

    /**
     * print
     * 打印输入的字符串
     *
     * @param @param args
     * @throws
     */
    public void print(String... args) {
        if (null == args) return;
        StringBuilder sb = new StringBuilder("[");
        // 更简洁的 for 循环写法，可以没有方法体
        for (int i = 0; i < args.length; sb.append(args[i++]).append(",")) ;

        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        System.out.println("[" + this.getClass().getSimpleName() + "] - [print] - " + sb.toString());
    }


}


































