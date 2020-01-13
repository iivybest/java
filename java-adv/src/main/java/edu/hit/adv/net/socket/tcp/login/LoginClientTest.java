package edu.hit.adv.net.socket.tcp.login;

import edu.hit.adv.net.socket.tcp.login.client.LoginClient;
import edu.hit.adv.net.socket.tcp.protocol.TcpClientAble;
import edu.hit.adv.net.socket.tcp.protocol.TcpClientContext;

/**
 * <p>LoginClientTest</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年5月11日-下午6:14:53
 */
public class LoginClientTest {
    public static void main(String[] args) {
        TcpClientContext ctx = new TcpClientContext();
        ctx.setHost("127.0.0.1");
        ctx.setPort(9090);

        TcpClientAble client = LoginClient.getInstance(ctx);

        String msg = "happy rooney...";
        byte[] reply = client.request(msg.getBytes());

        System.out.println("receive server resposne - " + new String(reply));

        client.stop();
    }
}
