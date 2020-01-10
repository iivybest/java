package edu.hit.adv.net.socket.udp.demo;

import edu.hit.adv.net.socket.tcp.demo1.IPv4IntTransformer;
import org.ivy.util.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: UDPServer
 * @author: ivybest imiaodev@163.com
 * @date: 2019年5月23日 上午9:07:46
 * : TODO(用一句话描述该文件做什么)
 */

public class UDPServer {
    private static Logger logger = LoggerFactory.getLogger(UDPServer.class);

    private ExecutorService pool;
    private String ip;
    private int port;
    private DatagramSocket datagramSocket;
    private String serverDesc;
    private CountDownLatch serverTerminationLatch;
    // ----结束语集合
    private static Collection<String> finishSeqs;

    static {
        finishSeqs = Arrays.asList("bye", "byebye", "goodbey", "exit", "quit");
    }

    private void initCommon() {
        this.ip = "127.0.0.1";
        this.port = 20000;
    }

    private void initEnv() throws SocketException, UnknownHostException {
        //----设置线程池中线程为 Daemon线程
        this.pool = Executors.newCachedThreadPool(e -> {
            Thread t = Executors.defaultThreadFactory().newThread(e);
            t.setDaemon(true);
            return t;
        });
        // ----服务结束监听器
        this.serverTerminationLatch = new CountDownLatch(1);
        // ----用于数据接收，通过本机指定port接收
//				this.datagramSocket = new DatagramSocket(this.port);
        byte[] bytesip = IPv4IntTransformer.ip2bytearray(this.ip);
        InetAddress address = Inet4Address.getByAddress(bytesip);
        this.datagramSocket = new DatagramSocket(this.port, address);
        // ----服务描述信息
        this.serverDesc = "/upd/server" + datagramSocket.getInetAddress() + "/" + datagramSocket.getLocalPort();
    }

    public UDPServer init() throws SocketException, UnknownHostException {
        // ----基础数据初始化
        this.initCommon();
        // ----环境初始化
        this.initEnv();
        // ----启动服务监控
        this.monitor();
        logger.debug("---->{}-[{}]-{}", "server", this.serverDesc, "in...");
        return this;
    }

    private UDPServer preDestroy() {
        this.pool.shutdown();
        if (this.datagramSocket != null) this.datagramSocket.close();

        logger.debug("---->{}-[{}]-{}", "server", this.serverDesc, "out...");
        return this;
    }

    private synchronized UDPServer monitor() {
        Thread monitor = new Thread(() -> {
            try {
                // ----线程池等待
                UDPServer.this.pool.awaitTermination(10, TimeUnit.MINUTES);
                // ----发送结束信号
                UDPServer.this.serverTerminationLatch.countDown();
            } catch (InterruptedException e) {
                logger.error(StringUtil.getFullStackTrace(e));
            }
        });
        monitor.setDaemon(true);
        monitor.start();
        return this;
    }

    public UDPServer doservice() throws Exception {
        Thread daemonworker = new Thread(() -> {
            for (; ; ) {
                try {
                    final byte[] buf = new byte[512];
                    DatagramPacket pack = new DatagramPacket(buf, buf.length);
                    this.datagramSocket.receive(pack);
                    this.pool.submit(() -> {
                        // ----客户端描述
                        String clientDesc = "/udp/client"
                                + pack.getAddress().getHostAddress() + "/" + pack.getPort();
                        // ----接收数据
                        String echo = new String(pack.getData(), pack.getOffset(), pack.getLength());
                        logger.debug("---->{}-{client: {}, length: {}, data: {}}", "recieve",
                                clientDesc,
                                pack.getLength(),
                                echo);

                        // ----回送数据
                        String respData = "receive_data_len_" + pack.getLength();
                        byte[] respDataBytes = respData.getBytes();
                        DatagramPacket respPack = new DatagramPacket(respDataBytes, 0, respDataBytes.length,
                                pack.getAddress(), pack.getPort());
                        try {
                            this.datagramSocket.send(respPack);
                            logger.debug("---->{}-{client: {}, data: {}}", "udp_provider publish",
                                    clientDesc,
                                    respData);
                        } catch (IOException e) {
                            logger.error(StringUtil.getFullStackTrace(e));
                        }
                    });
                } catch (Exception e) {
                    logger.error(StringUtil.getFullStackTrace(e));
                }
            }
        });
        daemonworker.setDaemon(true);
        daemonworker.start();
        // ----线程阻塞----等待server结束调度
        this.serverTerminationLatch.await();
        return this;
    }

    private UDPServer launch() {
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
        new UDPServer().launch();
    }
}
