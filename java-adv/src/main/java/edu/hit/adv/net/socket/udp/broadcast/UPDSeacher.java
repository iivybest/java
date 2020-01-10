package edu.hit.adv.net.socket.udp.broadcast;

import org.ivy.util.common.DateTimeUtil;
import org.ivy.util.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.SocketException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;


/**
 * @ClassName: UPDSeacher
 * @author: ivybest imiaodev@163.com
 * @date: 2019年5月22日 下午4:15:44
 * : TODO(用一句话描述该文件做什么)
 */
public class UPDSeacher {
    private static Logger logger = LoggerFactory.getLogger(UPDSeacher.class);

    private int port_listen;
    private int port_remote;
    private CountDownLatch listenActivationLatch;


    private void initCommon() {
        this.port_listen = 30000;
        this.port_remote = 20000;
        this.listenActivationLatch = new CountDownLatch(1);
    }

    public UPDSeacher init() throws SocketException {
        this.initCommon();
        return this;
    }

    private void boradcast(String data) throws IOException {
        // ----通信数据
        byte[] reqBetyes = data.getBytes();
        // ----通信UDP包
        DatagramPacket packet = new DatagramPacket(reqBetyes, reqBetyes.length);
        packet.setAddress(Inet4Address.getByName("255.255.255.255"));
        packet.setPort(this.port_remote);
        // ----广播
        DatagramSocket ds = new DatagramSocket();
        ds.send(packet);
        // ----关闭ds
        ds.close();
        logger.debug("====>broadcast-{}", data);
    }

    private void listen() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        ListenWorker listener = new ListenWorker(this.port_listen, this.listenActivationLatch, latch);
        // ----监听管理
        new Thread(() -> {
            try {
                logger.debug("---->ListenWorker-finishLatch-monitor-{}}", "in...");
                latch.await();
                LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 60_000);
                listener.exit();
                logger.debug("---->ListenWorker-finishLatch-monitor-{}}", "out...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // ----启动监听线程
        new Thread(listener).start();

        this.listenActivationLatch.await();
    }

    public UPDSeacher doservice() throws Exception {
        // ----监听
        this.listen();
        // ----确定监听启动后，发送广播
        this.boradcast(MessageCreator.buildWithPort(this.port_listen));
        return this;
    }

    private UPDSeacher launch() {
        try {
            this.init().doservice();
        } catch (Exception ex) {
            logger.error("====>{}", StringUtil.getFullStackTrace(ex));
        }
        return this;
    }

    public static void main(String[] args) {
        new UPDSeacher().launch();
    }

    private class ListenWorker implements Runnable {
        private CountDownLatch beginLatch;
        private CountDownLatch finishLatch;
        private boolean finish = false;
        private int port;
        private DatagramSocket datagramSocket = null;

        public ListenWorker(int port, CountDownLatch beginLatch, CountDownLatch finishLatch) {
            this.port = port;
            this.beginLatch = beginLatch;
            this.finishLatch = finishLatch;
        }

        public void init() throws SocketException {
            // ---- 用于数据接收，通过本机指定port接收
            this.datagramSocket = new DatagramSocket(this.port);
            logger.debug("---->[{}]-listen-[{}]-{}", "upd_listener", datagramSocket.getLocalPort(), "in...");
            this.beginLatch.countDown();
        }

        private void preDestroy() {
            if (this.datagramSocket != null) {
                int port = datagramSocket.getLocalPort();
                this.datagramSocket.close();
                logger.debug("---->[{}]-listen-[{}]-{}", "upd_listener", port, "out...");
            }
            this.datagramSocket = null;
        }

        public void doservice() throws IOException {

            final byte[] buf = new byte[512];
            DatagramPacket pack = new DatagramPacket(buf, buf.length);
            for (; !this.finish; ) {
                // ----接收数据
                this.datagramSocket.receive(pack);
                String echo = new String(pack.getData(), pack.getOffset(), pack.getLength());
                String sn = MessageCreator.parseSN(echo);
                logger.debug("---->{}-{ip: {}, port: {}, length: {}, data: {}, sn: {}}",
                        "upd_listener-recieve",
                        pack.getAddress().getHostAddress(),
                        pack.getPort(),
                        pack.getLength(),
                        echo,
                        sn);

                // ----通知主体，结束任务
                this.finishLatch.countDown();
                logger.debug("---->ListenWorker-finishLatch-{}}", true);
            }

        }

        public void exit() {
            this.finish = true;
            this.preDestroy();
        }

        @Override
        public void run() {
            try {
                this.init();
                this.doservice();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.exit();
            }
        }
    }
}









