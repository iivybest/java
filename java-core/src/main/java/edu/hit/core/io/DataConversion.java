package edu.hit.core.io;

import org.junit.Test;

import java.io.*;

/**
 * <p>DataConversion</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年5月12日 - 上午9:45:01
 */
public class DataConversion {

    /*
     * DataInputStream / DataOutputStream
     * 将一个long类型的数，写入文件中
     * 一个long类型数1234567891 占 8个字节，转为String后，占20个字节，
     * 再转换为字节数组，内存空间会增大很多，
     * 数据流可以直接存取原始类型数据
     */
    @Test
    public void test_01_dataStream() {
        long num = 1234567891L;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            dos.writeLong(num);
            dos.writeBoolean(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        DataInputStream dis = new DataInputStream(bais);
        System.out.println("----字节数---- " + bais.available());

        try {
            System.out.println(dis.readLong());
            System.out.println(dis.readBoolean());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (dis != null) dis.close();
            if (bais != null) bais.close();
            if (dos != null) dos.close();
            if (baos != null) baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
