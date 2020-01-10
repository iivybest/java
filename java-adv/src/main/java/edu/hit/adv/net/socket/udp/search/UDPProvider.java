package edu.hit.adv.net.socket.udp.search;

import org.ivy.util.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @ClassName: UDPProvider
 * @author: ivybest imiaodev@163.com
 * @date: 2019年5月22日 下午4:15:29
 * : UDP 服务提供者
 */
public class UDPProvider {
    private static Logger logger = LoggerFactory.getLogger(UDPProvider.class);

    private int port;
    private DatagramSocket datagramSocket;

    private void initCommon() {
        this.port = 20000;
    }

    public UDPProvider init() throws SocketException {
        this.initCommon();
        // ---- 用于数据接收，通过本机指定port接收
        this.datagramSocket = new DatagramSocket(this.port);

        logger.debug("---->{}-[{}]", "upd_provider", "in...");
        return this;
    }

    private UDPProvider preDestroy() {
        if (this.datagramSocket != null) this.datagramSocket.close();
        logger.debug("---->{}-[{}]", "upd_provider", "out...");
        return this;
    }

    public UDPProvider doservice() throws IOException {
        // ----接收数据
        final byte[] buf = new byte[512];
        DatagramPacket pack = new DatagramPacket(buf, buf.length);
        this.datagramSocket.receive(pack);
        String echo = new String(pack.getData(), pack.getOffset(), pack.getLength());
        logger.debug("---->{}-{ip: {}, port: {}, length: {}, data: {}}", "udp_provider recieve",
                pack.getAddress().getHostAddress(),
                pack.getPort(),
                pack.getLength(),
                echo);

        // ----回送数据
        String respData = "receive data len " + pack.getLength();
        byte[] respDataBytes = respData.getBytes();
        DatagramPacket respPack = new DatagramPacket(respDataBytes, 0, respDataBytes.length,
                pack.getAddress(), pack.getPort());
        this.datagramSocket.send(respPack);
        logger.debug("---->{}-{ip: {}, port: {}, data: {}}", "udp_provider publish",
                pack.getAddress().getHostAddress(),
                pack.getPort(),
                respData);

        return this;
    }

    private UDPProvider launch() {
        try {
            this.init().doservice();
        } catch (Exception ex) {
            logger.error("====>{}", StringUtil.getFullStackTrace(ex));
        } finally {
            this.preDestroy();
        }
        return this;
    }

    public static void main(String[] args) {
        new UDPProvider().launch();
    }
}




