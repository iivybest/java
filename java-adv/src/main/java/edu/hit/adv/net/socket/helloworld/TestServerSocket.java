package edu.hit.adv.net.socket.helloworld;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * <p>TestServerSocket</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年5月10日-上午10:47:31
 */
public class TestServerSocket {


    @Test
    public void testServerSocket() {
        ServerSocket servSoc = null;
        try {
            servSoc = new ServerSocket(1987);
            InetAddress address = servSoc.getInetAddress();
            System.out.println(address.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (servSoc != null) {
                    servSoc.close();
                    servSoc = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
