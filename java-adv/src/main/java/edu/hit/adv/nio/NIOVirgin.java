package edu.hit.adv.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>NIOVirgin</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年3月31日-下午5:21:20
 */
public class NIOVirgin {
    public static void main(String[] args) {
        String msg = "nio file read and write virgin test...";
        File file = new File("D:/ImiaoDev/Test/NIOTest.txt");

        try (FileOutputStream fos = new FileOutputStream(file);
             FileChannel channel = fos.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(2048);
            buffer.put(msg.getBytes());
            buffer.flip();
            channel.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
