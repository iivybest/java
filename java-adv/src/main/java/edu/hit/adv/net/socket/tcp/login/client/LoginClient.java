package edu.hit.adv.net.socket.tcp.login.client;

import edu.hit.adv.net.socket.tcp.protocol.TcpClientAble;
import edu.hit.adv.net.socket.tcp.protocol.TcpClientContext;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <p>LoginClient</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年5月10日-下午2:52:10
 */
public class LoginClient implements TcpClientAble {

    private Socket socket;

    private LoginClient(TcpClientContext ctx) {
        super();
        this.initialize(ctx);
    }

    @Override
    public boolean initialize(TcpClientContext ctx) {
        boolean connection = false;
        try {
            // ----1、与服务器端建立连接
            this.socket = new Socket(ctx.getHost(), ctx.getPort());
            connection = true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static LoginClient getInstance(TcpClientContext ctx) {
        return new LoginClient(ctx);
    }

    @Override
    public byte[] request(byte[] bytes) {
        OutputStream os = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            // ----2、得到socket的读写流
            os = new BufferedOutputStream(this.socket.getOutputStream());
            is = new BufferedInputStream(this.socket.getInputStream());

            // ----3、向服务器写入数据
            os.write(bytes);
            os.flush();

            // ----4、关闭socket输出流
            socket.shutdownOutput();

            // ----5、通过socket输入流，读取server的response信息
            baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                baos.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // ----6、关闭资源
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }

    @Override
    public void stop() {
        try {
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}





