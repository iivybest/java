package edu.hit.adv.net.socket.tcp.login.server;

import edu.hit.adv.net.socket.tcp.protocol.SocketHandlerAble;
import edu.hit.adv.net.socket.tcp.protocol.TcpServerAble;
import edu.hit.adv.net.socket.tcp.protocol.TcpServerContext;
import org.ivy.xutil.log.LogUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>LoginServer</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年5月10日-下午2:51:55
 */
public class LoginServer implements TcpServerAble {
    private static LogUtil log = new LogUtil(LoginServer.class);

    private SocketHandlerAble handler;
    private boolean stop = false;    // 结束标志
    private ServerSocket servSoc;

    {
        this.handler = new LoginSocketHandler();
    }

    public LoginServer(TcpServerContext ctx) {
        super();
        this.initialize(ctx);
    }

    @Override
    public void initialize(TcpServerContext ctx) {
        try {
            // ----1、建立服务器，绑定端口，开始监听
            this.servSoc = new ServerSocket(ctx.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listen() {
        try {
            log.log("server start...");
            while (!this.stop) {
                // ----2、accept() 阻塞监听，获取新的连接
                Socket socket = this.servSoc.accept();
                log.log("conncetion " + socket + socket.hashCode());
                // ----3、连接处理
                this.handler.handle(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        this.stop = true;
        this.close();
    }

    /**
     * <p>close - 关闭服务</p>
     *
     * @author ivybest ivybestdev@163.com
     * @date 2016年5月10日-下午4:15:14
     */
    private void close() {
        try {
            if (this.servSoc != null) {
                this.servSoc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}




