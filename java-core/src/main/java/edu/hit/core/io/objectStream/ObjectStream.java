package edu.hit.core.io.objectStream;

import org.junit.Test;

import java.io.*;
import java.util.Date;

/**
 * <p>ObjectStream</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年5月12日 - 下午4:17:14
 */
public class ObjectStream {
    private String dirUrl = "e:/test/java/chap09/";
    private String fileSuffix = ".dat";

    // 序列化对象文件名
    private String getFilename(Class<?> clazz) {
        return this.dirUrl + clazz.getSimpleName() + this.fileSuffix;
    }

    @Test
    public void test_01_objSerialize() {
        Employee e = new Employee();
        e.setId(1);
        e.setName("VanGal范加尔");
        e.setGender(1);
        e.setBirthdate(new Date());
        System.out.println(e);

        String url = this.getFilename(Employee.class);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(url);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(e);
            oos.flush();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void test_02_objDeserialize() {
        String url = this.getFilename(Employee.class);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(url);
            ois = new ObjectInputStream(fis);
            System.out.println(ois.readObject());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) ois.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test_03_objExternalizable() {
        // 构造对象
        Employer e = new Employer();
        e.setId(1);
        e.setName("VanGal范加尔");
        e.setGender(1);
        e.setBirthdate(new Date());
        System.out.println(e);

        String url = this.getFilename(Employer.class);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            // 序列化对象
            fos = new FileOutputStream(url);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(e);
            oos.flush();
            // 反序列化对象
            fis = new FileInputStream(url);
            ois = new ObjectInputStream(fis);
            System.out.println(ois.readObject());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
                if (ois != null) ois.close();
                if (fis != null) fis.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


}
