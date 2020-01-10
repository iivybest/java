package edu.hit.adv.dynamic.proxy.bytecode;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>AsmDemo</p>
 *
 * @author miao.xl
 * @date 2014-6-3 下午04:15:36
 */
public class AsmDemo {

    public static void main(String[] args) {
        /*
         * 生成类文件的结构，代码现在是这样 public class HelloWorld{ }
         */
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        writer.visit(49, Opcodes.ACC_PUBLIC, "HelloWorld", null, "java/lang/Object", null);

        Method method1 = Method.getMethod("void <init> ()");

        GeneratorAdapter adapter = new GeneratorAdapter(Opcodes.ACC_PUBLIC, method1, null, null, writer);
        adapter.loadThis();
        adapter.invokeConstructor(Type.getType(Object.class), method1);
        adapter.returnValue();
        adapter.endMethod();
        /*
         * 在HelloWorld中添加main方法，在main方法中添加代码，代码现在是这样子 public class HelloWorld{
         * public static void mian(String[] args){
         * System.out.println("Hello World!"); } }
         */
        method1 = Method.getMethod("void main (String[])");
        adapter = new GeneratorAdapter(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, method1, null, null, writer);
        adapter.getStatic(Type.getType(System.class), "out", Type.getType(PrintStream.class));
        adapter.push("Hello World!");
        adapter.invokeVirtual(Type.getType(PrintStream.class), Method.getMethod("void println (String)"));
        adapter.returnValue();
        adapter.endMethod();
        writer.visitEnd();
        /*
         * 这个方法用于后面的类动态加载测试,HelloWorld中的结构现在已经变成 public class HelloWorld{ public
         * static void mian(String[] args){ System.out.println("Hello World!");
         * } public void doSomething(){ System.out.println("动态生成，动态加载调用方法"); } }
         */
        method1 = Method.getMethod("void doSomething()");
        adapter = new GeneratorAdapter(Opcodes.ACC_PUBLIC, method1, null, null, writer);
        adapter.getStatic(Type.getType(System.class), "out", Type.getType(PrintStream.class));
        adapter.push("动态生成，动态加载调用方法");
        adapter.invokeVirtual(Type.getType(PrintStream.class), Method.getMethod("void println(String)"));
        adapter.returnValue();
        adapter.endMethod();

        MyClassLoader mcl = new MyClassLoader();
        // 利用自定义的类加载器把生成类文件加载到jvm 。
        byte[] bytes = writer.toByteArray();
        // 返回的对象时被加载类的class
        Class<?> hw = mcl.defineClassByName("HelloWorld", bytes, 0, bytes.length);
        try {
            // 利用反射创建对象
            Object o = hw.newInstance();
            java.lang.reflect.Method method = o.getClass().getMethod("doSomething");
            method.invoke(o);
            /*
             * 把文件生成的class文件写入磁盘 File.separator为文件分隔符（知道的勿喷）
             * ，它会根据操作系统不同，而自动转换，比直接写成\\这种通用性强。 这个路径原来一般写成d:\\HelloWorld.class
             */
            File file = new File("d:" + File.separator + "HelloWorld.class");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(writer.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
