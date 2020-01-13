package edu.hit.adv.dynamic.proxy.bytecode;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * <p>HelloASM</p>
 *
 * @author ivybest ivybestdev@163.com
 * @date 2014-6-3 下午03:35:07
 */
public class HelloASM extends ClassLoader implements Opcodes {
    /**
     * 我们知道Java是静态语言，而python、ruby是动态语言，Java程序一旦写好很难在运行时更改类的行为，而python、ruby可以。
     * 不过基于bytecode层面上我们可以做一些手脚，来使Java程序多一些灵活性和Magic，ASM就是这样一个应用广泛的开源库。 ASM is a
     * Java bytecode manipulation framework. It can be used to dynamically
     * generate stub classes or other proxy classes, directly in binary form, or
     * to dynamically modify classes at load time, i.e., just before they are
     * loaded into the Java Virtual Machine.
     * <p>
     * ASM完成了BCEL和SERP同样的功能，但ASM 只有30多k，而后两者分别是350k和150k。apache真是越来越过气了。
     * <p>
     * 让我们来看一个ASM的简单例子Helloworld.java，它生成一个Example类和一个main方法，main方法打印"Hello world!"
     * 语句:
     */
    public static void main(String[] args) throws Exception {

        ClassWriter writer = new ClassWriter(0);
        writer.visit(V1_1, ACC_PUBLIC, "Example", null, "java/lang/Object", null);

        MethodVisitor visitor = writer.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        visitor.visitVarInsn(ALOAD, 0);
        visitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        visitor.visitInsn(RETURN);
        visitor.visitMaxs(1, 1);
        visitor.visitEnd();

        visitor = writer.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        visitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        visitor.visitLdcInsn("Hello ASM!");
        visitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        visitor.visitInsn(RETURN);
        visitor.visitMaxs(2, 2);
        visitor.visitEnd();

        byte[] code = writer.toByteArray();
        FileOutputStream fos = new FileOutputStream("Example.class");
        fos.write(code);
        fos.close();

        HelloASM loader = new HelloASM();
        Class<?> exampleClass = loader.defineClass("Example", code, 0, code.length);
        exampleClass.getMethods()[0].invoke(null, new Object[]{null});

        // ------------------------------------------------------------------------
        // Same example with a GeneratorAdapter (more convenient but slower)
        // ------------------------------------------------------------------------

        writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        writer.visit(V1_1, ACC_PUBLIC, "Example", null, "java/lang/Object", null);

        Method method = Method.getMethod("void <init> ()");
        GeneratorAdapter adapter = new GeneratorAdapter(ACC_PUBLIC, method, null, null, writer);
        adapter.loadThis();
        adapter.invokeConstructor(Type.getType(Object.class), method);
        adapter.returnValue();
        adapter.endMethod();

        method = Method.getMethod("void main (String[])");
        adapter = new GeneratorAdapter(ACC_PUBLIC + ACC_STATIC, method, null, null, writer);
        adapter.getStatic(Type.getType(System.class), "out", Type.getType(PrintStream.class));
        adapter.push("Hello ASM 2!");
        adapter.invokeVirtual(Type.getType(PrintStream.class), Method.getMethod("void println (String)"));
        adapter.returnValue();
        adapter.endMethod();

        writer.visitEnd();

        code = writer.toByteArray();
        loader = new HelloASM();
        exampleClass = loader.defineClass("Example", code, 0, code.length);
        exampleClass.getMethods()[0].invoke(null, new Object[]{null});
    }
}
