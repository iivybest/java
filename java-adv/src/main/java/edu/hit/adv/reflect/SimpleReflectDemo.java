package edu.hit.adv.reflect;

import edu.hit.adv.reflect.bean.MyClass;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>SimpleReflectDemo</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年2月17日-下午2:07:38
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class SimpleReflectDemo {

    private MyClass myClass;

    @Before
    public void setup() {
        this.myClass = new MyClass(0);
    }

    /*
     *  创建对象的一般做法
     */
    @Test
    public void testCreateObjCommonStyle() {
        this.myClass = new MyClass(0);
        myClass.increase(2);
        System.out.println("Normal --> " + myClass.count);
        System.out.println("----devil deviding line---------------------------------------------------");
    }

    /*
     *
     */
    @Test
    public void testGetClassTypeByReflect() {
        System.out.println("======================================得到指定类型");

        // JVM查找并加载指定的类 MyClass，JVM会执行该类的静态代码
        try {
            // 获得该类型的数组对象
            Class<?> clazzArray = Class.forName("[Ledu.hit.adv.reflect.bean.MyClass;");
            System.out.println("clazzArray " + clazzArray.toString());
            System.out.println("clazzArray is Array " + clazzArray.isArray());
            System.out.println("clazzArray component Type is " + clazzArray.getComponentType().toString());

            Class<?> clazz0 = Class.forName("edu.hit.adv.reflect.bean.MyClass");
            System.out.println("clazz0 " + clazz0.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class<?> clazz1 = myClass.getClass();
        System.out.println("clazz1 " + clazz1.toString());
        // 这种写法更安全高效,比起前两种来
        Class<?> clazz2 = MyClass.class;
        System.out.println("clazz2 " + clazz2.toString());
        System.out.println("----devil deviding line---------------------------------------------------");
    }

    @Test
    public void testReflectTypeInfo() {
        Class<?> clazz2 = MyClass.class;
        Package pkg = clazz2.getPackage();
        System.out.println("pkg " + pkg);

        Class<?>[] ifs = clazz2.getInterfaces();
        for (Class<?> i : ifs) System.out.println("实现接口 " + i);

        Class<?> superCls = clazz2.getSuperclass();
        System.out.println("父类 " + superCls);
        System.out.println("----devil deviding line---------------------------------------------------");
    }

    @Test
    public void testCreateNewObjbyReflect() {
        Class<?> clazz2 = MyClass.class;
        try {
            System.out.println("======================================得到构造器");
            // newInstance()创建实例只能调用无参constructor，
            // MyClass 没有无参构造器，所以不能通过通过这种方法创建实例
//			MyClass cc =  (MyClass)clazz.newInstance();

            // 获取该类的所有构造方法
            // getConstructors()只能取得访问级别为public的构造函数
            Constructor<?>[] constructors = clazz2.getConstructors();
            // 获取全部构造方法
//			Constructor<?>[] cons2 = clazz.getDeclaredConstructors();
            Constructor<?> constructor = constructors[0];
            // 修饰符
            System.out.println(constructor.getModifiers());
            System.out.println(constructor.getParameterTypes().length);
            System.out.println(constructor.getParameterTypes()[0]);
            // 通过构造器实例化对象
            MyClass myClassReflect = (MyClass) constructor.newInstance(0);

            /*
             * 要是清楚constructor，可直接写为如下方式：
             */
            // 获取构造方法
//			Constructor<?> constructor1 = MyClass.class.getConstructor(int.class);
//			Constructor<?> constructor1 = clazz.getConstructor(int.class);
            // 创建对象
//			MyClass myClassReflect1 = (MyClass)constructor1.newInstance(0);


            System.out.println("======================================类的所有方法");
            Method[] methods = clazz2.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++)
                System.out.println(methods[i].toString());
            // 直接调用对象方法
//			myClassReflect.increase(2);
            // 通过反射-获取方法
            Method method = MyClass.class.getMethod("increase", int.class);
            method.invoke(myClassReflect, 2);


            System.out.println("======================================获取域");
            Field[] fields = clazz2.getDeclaredFields();
            for (int i = 0; i < fields.length; i++)
                System.out.println(fields[i].getType() + " " + fields[i].getName());
            Field field = MyClass.class.getField("count");
            field.set(myClassReflect, 15);        // 修改属性值
            System.out.println(field.getName() + "=" + field.getInt(myClassReflect));


            System.out.println("======================================获取Annotation");
            Annotation[] annos = clazz2.getAnnotations();
            for (Annotation a : annos) System.out.println(a.toString());
            Annotation[] annos2 = clazz2.getDeclaredAnnotations();
            for (Annotation a : annos2) System.out.println(a.toString());

            System.out.println("======================================获取内部类");
            Class<?>[] innerCls = clazz2.getDeclaredClasses();
            for (Class<?> c : innerCls) System.out.println(c);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}

