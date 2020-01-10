package edu.hit.adv.net.niosocket.tcp.chat.robot.siri;

/**
 * <p>Test</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年4月5日-上午10:33:26
 */
public class SiriTest {
    public static void main(String[] args) {
        int port = 9201;
        Siri siri = new Siri(port);
        SiriClient client = new SiriClient(port);
        siri.launch();
        client.launch();
    }
}
