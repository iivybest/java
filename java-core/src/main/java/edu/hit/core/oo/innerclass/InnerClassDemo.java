package edu.hit.core.oo.innerclass;

/**
 * <p>InnerClassDemo</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年8月11日 - 上午11:05:06
 */
public class InnerClassDemo {
    private int id;
    private String name;

    {
        this.id = 1;
        this.name = "InnerClassDemo";
    }

    public String getName() {
        return new Destination().getName();
    }

    /**
     * InnerClass Inner
     * 成员内部类可以使用外部类的属性
     */
    public class Inner {
        private int id;
        private String name;

        {
            this.id = 11;
            this.name = "Inner";
        }

        private void printId() {
            System.out.println("---------------------------------->"
                    /* 直接使用一个参数，顺序 局部 --> 内部类成员 --> 外部类成员 */
                    + "\n" + id
                    + "\n" + this.id
                    + "\n" + InnerClassDemo.this.id);
        }

        public void printName() {
            String name = "Local";
            System.out.println("---------------------------------->"
                    + "\n局部变量:" + name
                    + "\n内部类变量:" + this.name
                    + "\n外部类变量:" + InnerClassDemo.this.name);
        }
    }

    private class Destination {
        private String getName() {
            return name;
        }

        ;
    }


    public static void main(String[] args) {
        InnerClassDemo demo = new InnerClassDemo();
        System.out.println(demo.getName());

        InnerClassDemo.Inner in = new InnerClassDemo().new Inner();
        in.printId();
        in.printName();
    }
}
