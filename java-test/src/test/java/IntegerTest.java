import org.junit.Test;


/**
 * @author Ivybest imiaodev@163.com
 * @date 2018年3月15日 下午7:27:57
 * @version 1.0 ------------------------------------------
 * TODO(这里用一句话描述这个类的作用)
 */
public class IntegerTest {
    @Test
    public void test() {
        Integer a = 128;
        Integer b = Integer.valueOf(128);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        Object o = null;
        o.equals(b);


    }
}
