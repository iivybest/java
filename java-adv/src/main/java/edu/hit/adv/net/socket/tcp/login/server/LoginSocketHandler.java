package edu.hit.adv.net.socket.tcp.login.server;

import edu.hit.adv.net.socket.tcp.protocol.SocketHandlerAble;
import org.ivy.xutil.log.LogUtil;

import java.io.*;
import java.net.Socket;

/**
 * <p>LoginSocketHandler</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年5月11日-下午6:43:51
 */
public class LoginSocketHandler implements SocketHandlerAble {
    private static LogUtil log = new LogUtil(LoginSocketHandler.class);

    @Override
    public void handle(Socket socket) {
        new Thread(new LoginHandler(socket)).start();
    }


    /**
     * <p>LoginHandler - server处理线程</p>
     *
     * @author miao.xl
     * @version 1.0
     * @date 2016年5月11日-下午6:05:01
     */
    private class LoginHandler implements Runnable {
        private Socket connection;

        /**
         * @param connection
         */
        public LoginHandler(Socket connection) {
            super();
            this.connection = connection;
        }

        @Override
        public void run() {
            this.handle();
        }

        private void handle() {
            InputStream is = null;
            ByteArrayOutputStream baos = null;
            OutputStream os = null;
            try {
                // ----3、获得输入流
                is = new BufferedInputStream(this.connection.getInputStream());
                baos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int len;
                while ((len = is.read(buf)) > 0) {
                    baos.write(buf, 0, len);
                }
                byte[] req = baos.toByteArray();
                log.log("receive client request - " + new String(req));

                // ----4、获取输出流
                os = new BufferedOutputStream(this.connection.getOutputStream());
                os.write("hello caonima...".getBytes());
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (baos != null) {
                        baos.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                    if (this.connection != null) {
                        this.connection.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}








