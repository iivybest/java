package edu.hit.adv.net.socket.udp.broadcast;

/**
 * @ClassName: MessageCreator
 * @author: ivybest imiaodev@163.com
 * @date: 2019年5月23日 上午11:19:27
 * : TODO(用一句话描述该文件做什么)
 */
public class MessageCreator {

    private static final String SN_HEADER = "receive_signal, i_am_(sn):";
    private static final String PORT_HEADER = "signal, please_reply_port:";

    public static String buildWithPort(int port) {
        return PORT_HEADER + port;
    }

    public static int parsePort(String msg) {
        int port = -1;
        if (msg == null || msg.length() == 0) {
            return port;
        }
        if (msg.startsWith(PORT_HEADER)) {
            port = Integer.parseInt(msg.substring(PORT_HEADER.length()));
        }
        return port;
    }

    public static String buildWithSN(String sn) {
        return SN_HEADER + sn;
    }

    public static String parseSN(String msg) {
        String sn = null;
        if (msg == null || msg.length() == 0) {
            return sn;
        }
        if (msg.startsWith(SN_HEADER)) {
            sn = msg.substring(SN_HEADER.length());
        }
        return sn;
    }

}
