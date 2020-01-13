package edu.hit.adv.reflect.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * <p>StudentReflect</p>
 * <p>Description:</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2014年8月7日 上午10:24:02
 */
public class StudentReflect {

    public Class<?> getClazz(String classAbsolutePath) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(classAbsolutePath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    /**
     * 获取类的修饰符
     *
     * @param clazz
     * @return 修饰符由 Java 虚拟机的 public、protected、private、
     * final、static、abstract 和 interface 对应的常量组成
     * @author ivybest ivybestdev@163.com
     * @date 2014年8月7日 上午11:05:04
     * @version 1.0
     */
    public int getModifiers(Class<?> clazz) {
        System.out.println("Modifier.PUBLIC : " + Modifier.PUBLIC);
        System.out.println("Modifier.PROTECTED : " + Modifier.PROTECTED);
        System.out.println("Modifier.PRIVATE : " + Modifier.PRIVATE);
        System.out.println("Modifier.FINAL : " + Modifier.FINAL);
        System.out.println("Modifier.STATIC : " + Modifier.STATIC);
        System.out.println("Modifier.ABSTRACT : " + Modifier.ABSTRACT);
        System.out.println("Modifier.INTERFACE : " + Modifier.INTERFACE);
        return clazz.getModifiers();
    }


    public Class<?>[] getInterfaces(Class<?> clazz) {
        Class<?>[] interfaces = null;
        interfaces = clazz.getInterfaces();
        return interfaces;
    }

    public Class<?> getSuperClazz(Class<?> clazz) {
        Class<?> superClazz = null;
        superClazz = clazz.getSuperclass();
        return superClazz;
    }

    public static void main(String[] args) {
        String clazzPath = "edu.hit.proxy.dynamicproxy.reflect.user.Student";
        StudentReflect srf = new StudentReflect();
        Class<?> clazz = srf.getClazz(clazzPath);
        System.out.println(clazz.getName());

        /*
         * modifiers
         */
        System.out.println("\n" + "----clazz.modifires ------------");
        System.out.println(srf.getModifiers(clazz));

        /*
         * 实现接口
         */
        System.out.println("\n" + "----clazz.interface ------------");
        Class<?>[] interfaces = srf.getInterfaces(clazz);
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println(interfaces[i].getName());
            if (interfaces[i].getName().contains("edu.hit.proxy.dynamicproxy.reflect.user.SmartAware")) {
                System.out.println("聪明人");
            }
        }

        /*
         * genericInterface
         */
        System.out.println("\n" + "----clazz.genericInterface ------------");
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        for (int i = 0; i < genericInterfaces.length; i++) {
            System.out.println(genericInterfaces[i].getClass().getName());
        }

        /*
         * getClasses
         */
        System.out.println("\n" + "clazz.getClasses ------------");
        Class<?>[] clazzes = clazz.getClasses();
        for (Class<?> c : clazzes)
            System.out.println(c.getName());

        /*
         * 获取基类
         */
        System.out.println("\n" + "clazz.superClass ------------");
        Class<?> superClazz = srf.getSuperClazz(clazz);
        System.out.println(superClazz.getName());


        /*
         * Fields
         * 返回一个包含某些 Field 对象的数组，这些对象反映此 Class 对象所表示的类或接口的所有可访问公共字段
         */
        System.out.println("\n" + "clazz.fields ------------");
        Field[] fields = clazz.getFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getModifiers()
                    + " - " + fields[i].getType().toString()
                    + " - " + fields[i].getName()
            );
        }

        /*
         * declaredFields
         * 返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段。
         */
        System.out.println("\n" + "----clazz.declaredFields ------------");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            System.out.println(declaredFields[i].getModifiers()
                    + " - " + declaredFields[i].getType().toString()
                    + " - " + declaredFields[i].getName()
            );
        }

        /*
         * declaredMethods
         */
        System.out.println("\n" + "----clazz.declaredMethods ------------");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            System.out.println(declaredMethods[i].getModifiers() + " - " + declaredMethods[i].getName());
        }

        /*
         * Methods
         */
        System.out.println("\n" + "----clazz.methods ------------");
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i].getModifiers() + " - " + methods[i].getName());
        }

    }

}
