package edu.hit.adv.dynamic.compile;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * <p>JdkDynamicCompiler</p>
 *
 * @author miao.xl
 * @date 2014年8月28日 - 上午9:23:22
 */
public class JdkDynamicCompiler {
    public static void main(String[] args) throws Exception {
        String fileName = "edu/hit/java/adv/dynamic/compile/HelloWorld.java";
        String className = "edu.hit.guide.java.adv.dynamic.compile.HelloWorld";

        // 系统根路径
        String rootDir = System.getProperty("user.dir");
        String src = rootDir + "/src/main/java/";
        String fileAbsoluteUrl = src + fileName;
        System.out.println(fileAbsoluteUrl);

        /*
         * compile
         */
        // JDK6 提供 - java编译器 API
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        System.out.println(compiler.getClass().getName());
        // 拿到java编译器，只需找到需要编译文件即可
        // Sun要求使用 StandardJavaFileManager 管理编译文件
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(fileAbsoluteUrl);
        CompilationTask task = compiler.getTask(null, fileMgr, null, null, null, units);
        task.call();
        fileMgr.close();

        /*
         * load into memory create a instance
         */
        // 使用ClassLoader 往memory里load类的话，必须保证类在classPath里，既bin中
        // URLClassLoader 可以把硬盘上的文件，放到memory
        // URL指向SRC，告诉URLClassLoader 去 src下找class文件
        URL[] urls = new URL[]{new URL("file:/" + src)};
        URLClassLoader ul = new URLClassLoader(urls);
        Class<?> helloWorld = ul.loadClass(className);
        System.out.println(helloWorld);

        /*
         * reflect operate class
         */
//		Constructor[] ctr = helloWorld.getConstructors();
        HelloWorld instance = (HelloWorld) helloWorld.newInstance();
        Method[] methods = helloWorld.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            System.out.println(method.getName());
            method.invoke(instance);
        }


    }
}
