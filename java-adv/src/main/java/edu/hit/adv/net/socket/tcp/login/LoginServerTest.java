package edu.hit.adv.net.socket.tcp.login;

import edu.hit.adv.net.socket.tcp.login.server.LoginServer;
import edu.hit.adv.net.socket.tcp.protocol.TcpServerContext;

/**
 * <p>LoginServerTest</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年5月11日-下午6:14:13
 */
public class LoginServerTest {
    public static void main(String[] args) {
        TcpServerContext ctx = new TcpServerContext().setPort(9090);
        new LoginServer(ctx).listen();
    }
}
