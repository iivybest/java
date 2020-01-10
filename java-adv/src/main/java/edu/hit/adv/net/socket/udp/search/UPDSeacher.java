package edu.hit.adv.net.socket.udp.search;

import org.ivy.util.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.SocketException;

/**
 * @ClassName: UPDSeacher
 * @author: ivybest imiaodev@163.com
 * @date: 2019年5月22日 下午4:15:44
 * : TODO(用一句话描述该文件做什么)
 */
public class UPDSeacher {
    private static Logger logger = LoggerFactory.getLogger(UPDSeacher.class);

    private int port;
    private DatagramSocket datagramSocket;

    private void initCommon() {
        this.port = 20000;
    }

    public UPDSeacher init() throws SocketException {
        this.initCommon();
        // ---- 搜索者，使用系统随机分配端口
        this.datagramSocket = new DatagramSocket();
        logger.debug("====>{}-{}", "upd_searcher", "in...");
        return this;
    }

    private UPDSeacher preDestroy() {
        if (this.datagramSocket != null) this.datagramSocket.close();
        logger.debug("====>{}-{}", "upd_searcher", "out...");
        return this;
    }

    public UPDSeacher doservice() throws IOException {
        // ----发送数据
        String reqData = "hello_udp";
        byte[] reqDataBytes = reqData.getBytes();
        DatagramPacket reqPack = new DatagramPacket(reqDataBytes, 0, reqDataBytes.length);
        reqPack.setAddress(Inet4Address.getLocalHost());
        reqPack.setPort(this.port);
        this.datagramSocket.send(reqPack);
        logger.debug("====>{}-{ip: {}, port: {}, data: {}}", "upd_searcher-publish",
                Inet4Address.getLocalHost(),
                this.port,
                reqData);

        // ----接收数据
        final byte[] buf = new byte[512];
        DatagramPacket respPack = new DatagramPacket(buf, buf.length);
        this.datagramSocket.receive(respPack);
        String echo = new String(respPack.getData(), respPack.getOffset(), respPack.getLength());
        logger.debug("====>{}-{ip: {}, port: {}, length: {}, data: {}}", "upd_searcher-recieve",
                respPack.getAddress().getHostAddress(),
                respPack.getPort(),
                respPack.getLength(),
                echo);

        return this;
    }

    private UPDSeacher launch() {
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
        new UPDSeacher().launch();
    }
}









