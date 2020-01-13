package edu.hit.core.oo;

/**
 * <p> description:
 * <br>----------------------------------------
 * <br> 类的实例化顺序
 * <br>	----1、 static variable
 * <br>	----2、 static block
 * <br>	----3、 variable
 * <br>	----4、 code block
 * <br>	----5、 constructor
 * <br>----------------------------------------
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @className ClassInstanceInit
 * @date 2019/12/4 12:30
 */
public class ClassInstanceInit {

    private static int v1 = 1;
    private int v2 = 4;

    static {
        print();
        System.out.println("----2、 this is static block");
    }

    {
        System.out.println("----3、 this is variable v2 : " + v2);
        System.out.println("----4、 this is code block");
    }

    public ClassInstanceInit() {
        System.out.println("----5、 this is constructor");
    }

    private static void print() {
        System.out.println("----1、 this is static variable v1 : " + v1);
    }

    public static void main(String[] args) {
        new ClassInstanceInit();
    }

}
