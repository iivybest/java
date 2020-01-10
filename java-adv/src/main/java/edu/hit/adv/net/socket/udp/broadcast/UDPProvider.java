package edu.hit.adv.net.socket.udp.broadcast;

import edu.hit.adv.thread.closeable.ForcedCloseableAsynTask;
import org.ivy.util.common.DateTimeUtil;
import org.ivy.util.common.UniSeqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName: UDPProvider
 * @author: ivybest imiaodev@163.com
 * @date: 2019年5月22日 下午4:15:29
 * : UDP 服务提供者
 */
public class UDPProvider {
    private static Logger logger = LoggerFactory.getLogger(UDPProvider.class);

    public void launch() {
        String sn = UniSeqUtil.generateUniSeq("UDPP");
        ProviderAble provider = new ProviderAble(sn);

//		// ----异步任务启动服务
//		new Thread(provider).start();
//		// ----服务运行5分钟
//		LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 10_000);
//		// ----服务退出
//		provider.exit();

        new ForcedCloseableAsynTask()
                .taskName("xx-upd-provider")
                .execute(provider)
                .callback(() -> provider.exit())
                .shutdown(300_000);
    }

    public static void main(String[] args) {
        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 8_000);
        logger.debug("====>waiting testcase begin...");
        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 2_000);

        // ----执行测试用例
        new UDPProvider().launch();
    }

    private class ProviderAble implements Runnable {
        private final String sn;
        private boolean finish = false;
        private DatagramSocket datagramSocket = null;
        private int port;

        public ProviderAble(String sn) {
            this.sn = sn;
        }

        public void init() throws SocketException {
            this.port = 20000;
            // ---- 用于数据接收，通过本机指定port接收
            this.datagramSocket = new DatagramSocket(this.port);
            logger.debug("---->[{}]-listen-[{}]-{}", "upd_provider", datagramSocket.getLocalPort(), "in...");
        }

        private void preDestroy() {
            if (this.datagramSocket != null) {
                int port = datagramSocket.getLocalPort();
                this.datagramSocket.close();
                logger.debug("---->[{}]-listen-[{}]-{}", "upd_provider", port, "out...");
                this.datagramSocket = null;
            }
        }

        public void doservice() throws IOException {

            final byte[] buf = new byte[512];
            DatagramPacket pack = new DatagramPacket(buf, buf.length);
            DatagramPacket respPack = null;
            int respPort = -1;
            String respData = null;
            byte[] respDataBytes = null;

            for (; !this.finish; ) {
                // ----接收数据
                this.datagramSocket.receive(pack);
                String echo = new String(pack.getData(), pack.getOffset(), pack.getLength());
                logger.debug("---->{}-{ip: {}, port: {}, length: {}, data: {}}",
                        "udp_provider-recieve",
                        pack.getAddress().getHostAddress(),
                        pack.getPort(),
                        pack.getLength(),
                        echo);

                // ----回送数据
                respPort = MessageCreator.parsePort(echo);

                if (-1 == respPort) break;

                respData = MessageCreator.buildWithSN(this.sn);
                respDataBytes = respData.getBytes();
                respPack = new DatagramPacket(respDataBytes, 0, respDataBytes.length,
                        pack.getAddress(), respPort);
                this.datagramSocket.send(respPack);
                logger.debug("---->{}-{ip: {}, port: {}, data: {}}", "udp_provider-publish",
                        pack.getAddress().getHostAddress(),
                        respPort,
                        respData);
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
                this.preDestroy();
            }
        }
    }

}




