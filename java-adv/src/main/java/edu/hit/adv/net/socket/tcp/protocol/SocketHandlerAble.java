package edu.hit.adv.net.socket.tcp.protocol;

import java.net.Socket;

/**
 * <p>ConnectionHandlerAble - TcpServer 处理一次client的connection</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年5月12日-上午8:42:33
 */
public interface SocketHandlerAble {

    /**
     * <p>handle connection</p>
     *
     * @param connection
     * @author miao.xl
     * @date 2016年5月12日-上午8:43:36
     */
    void handle(Socket connection);

}
