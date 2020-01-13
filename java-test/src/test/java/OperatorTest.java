import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> description: 操作符测试
 * <br>--------------------------------------------------------
 * <br> TODO
 * <br>--------------------------------------------------------
 * <br>Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @className OperatorTest
 * @date 2017/12/27 15:17
 */
public class OperatorTest {

    @Test
    public void testcase_01_operator() {
        int t = 6;
        int a = (--t) + (t--);
//		int a = (t--) + (--t);
        System.out.println(a);
    }


    @Test
    public void testcase_02_sublist() {
        List<String> collect = new ArrayList<String>();
        collect.add("rojo");
        collect.add("nani");
        collect.add("mata");

        List<String> sub = collect.subList(0, 2);
        sub.stream().forEach(System.out::println);
        for (String e : sub) System.out.println(e);
        for (int i = 0; i < sub.size(); System.out.println(sub.get(i++))) ;
        for (int i = 0; i < sub.size(); i++) System.out.println(sub.get(i));
        for (int i = 0, size = sub.size(); i < size; System.out.println(sub.get(i++))) ;
    }
}









