package edu.hit.core.oo.innerclass;

/**
 * <p>MemberInnerClassDemo</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年8月11日 - 下午3:23:21
 */
public class LocalInnerClassDemo {
    private final int i = 4;

    public void test() {
        final int i2 = 6;
        /*
         * 局部内部类，使用很少，定义在方法中
         * 只能访问外部 final 属性
         */
        class Inner {
            public void test() {
                System.out.println(i + i2);
            }
        }
        new Inner().test();
    }

    public static void main(String[] args) {
        LocalInnerClassDemo demo = new LocalInnerClassDemo();
        demo.test();
    }
}
