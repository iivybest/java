package edu.hit.adv.nio.mmap;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName: ReadBigFileByMmap
 * @author: ivybest imiaodev@163.com
 * @date: 2019年4月17日 下午2:06:45
 * : 利用内存映射文件读取大文件
 */
public class ReadBigFileByMmap {
    private String filepath;
    private File file;

    @Before
    public void setUp() {
        this.filepath = "D:/BaiduNetdiskDownload/1/Java并发编程.png";
        this.file = new File(this.filepath);
        System.out.println(this.file.length());
    }


    @Test
    public void testReadBigFileByOSIO() throws IOException {
        try (
                FileInputStream in = new FileInputStream(this.file);
                BufferedInputStream buff = new BufferedInputStream(in)
        ) {
            byte[] buf = new byte[1024];
            while (buff.read(buf) > 0) ;
        }
    }

    @Test
    public void testReadBigFileByNIO() throws IOException {
        try (
                FileInputStream in = new FileInputStream(this.file)
        ) {
            FileChannel channel = in.getChannel();
            // ---- 普通NIO
            ByteBuffer buff = ByteBuffer.allocate(1024);
            while (channel.read(buff) != -1) {
//				buff.flip();
                buff.clear();
            }
        }
    }


    @Test
    public void testReadBigFileByDirectMemory() throws IOException {
        try (
                FileInputStream in = new FileInputStream(this.file)
        ) {
            FileChannel channel = in.getChannel();
            // ---- 使用直接内存，
//			ByteBuffer buff = ByteBuffer.allocateDirect(1024);
            // --- 增大直接内存大小，速度显著提升，略慢与内存映射文件
            ByteBuffer buff = ByteBuffer.allocateDirect((int) this.file.length());
            while (channel.read(buff) != -1) {
//				buff.flip();
                buff.clear();
            }
        }
    }

    @Test
    public void testReadBigFileByMmap() throws IOException {
        try (FileInputStream in = new FileInputStream(this.file)
        ) {
            FileChannel channel = in.getChannel();
            // ---- 创建内存映射文件
            MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

            byte[] buf = new byte[1024];
            int len = (int) file.length();

            for (int offset = 0; offset < len; offset += buf.length) {
                if (len - offset > buf.length) buff.get(buf);
                else buff.get(new byte[len - offset]);
            }
        }
    }
}





