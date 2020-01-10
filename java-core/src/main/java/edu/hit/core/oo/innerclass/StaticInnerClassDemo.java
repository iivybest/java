package edu.hit.core.oo.innerclass;

/**
 * <p>StaticInnerClassDemo</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年8月11日 - 下午2:51:09
 */
public class StaticInnerClassDemo {

    public int getRandom() {
        return Holder.getRandom();
    }

    private static class Holder {
        private static int getRandom() {
            return 151;
        }
    }

    static class Inner {
        public String sayHello(String o) {
            return "hello " + o;
        }
    }

    public static void main(String[] args) {
        StaticInnerClassDemo.Inner inner1 = new Inner();
        System.out.println(inner1.sayHello("Nani"));

        StaticInnerClassDemo.Inner inner2 = new StaticInnerClassDemo.Inner();
        System.out.println(inner2.sayHello("Rojo"));

        StaticInnerClassDemo demo = new StaticInnerClassDemo();
        System.out.println(demo.getRandom());
    }
}
