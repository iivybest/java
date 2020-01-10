package edu.hit.adv.net.socket.tcp.demo1;

import org.ivy.util.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Server
 * @author: ivybest imiaodev@163.com
 * @date: 2019年5月21日 上午9:16:15
 */
public class Server {
    private static Logger logger = LoggerFactory.getLogger(Server.class);
    private static ExecutorService pool;
    private static String ip;
    private static int port;
    private static ServerSocket socket;
    private static String serverDesc;
    private static CountDownLatch serverTerminationLatch;
    // ----结束语集合
    private static Collection<String> finishSeqs;

    private static void initCommon() {
        ip = "127.0.0.1";
        port = 2000;
        finishSeqs = Arrays.asList("bye", "byebye", "goodbey", "exit", "quit");
    }

    private static void init() throws IOException {
        Server.initCommon();

        // Server.pool = Executors.newCachedThreadPool();
        //----设置线程池中线程为 Daemon线程
        Server.pool = Executors.newCachedThreadPool(e -> {
            Thread t = Executors.defaultThreadFactory().newThread(e);
            t.setDaemon(true);
            return t;
        });

        Server.serverTerminationLatch = new CountDownLatch(1);
        byte[] bytesip = IPv4IntTransformer.ip2bytearray(Server.ip);
        InetAddress address = Inet4Address.getByAddress(bytesip);

        // Server.socket = new ServerSocket(Server.port);
        Server.socket = new ServerSocket(Server.port, 100, address);
        Server.serverDesc = "/server" + socket.getInetAddress() + "/" + socket.getLocalPort();
    }

    public static void main(String[] args) {
        try {
            // ----Server初始化
            Server.init();
            logger.debug("====>{}-[{}]-{}", "server", Server.serverDesc, "in...");
            // ----异步任务----线程池结束监听
            Server.monitor();
            // ----异步任务----Server 接收 client请求
            // ----阻塞方法----Server 任务结束，方法结束阻塞，向下执行
            Server.doservice(Server.socket);
        } catch (Exception ex) {
            // ----TODO 异常处理
            logger.error(StringUtil.getFullStackTrace(ex));
        } finally {
            // ----执行server销毁前收尾工作
            Server.finalized();
            logger.debug("====>{}-[{}]-{}", "server", Server.serverDesc, "out...");
        }
    }

    private static synchronized void doservice(ServerSocket socket)
            throws InterruptedException {
        Thread daemonworker = new Thread(() -> {
            for (; ; ) {
                try {
                    Socket conn = Server.socket.accept();
                    Server.pool.submit(new ClientSocketHandler(conn));
                } catch (IOException e) {
                    Server.logger.error(StringUtil.getFullStackTrace(e));
                }
            }
        });
        daemonworker.setDaemon(true);
        daemonworker.start();
        // ----线程阻塞----等待server结束调度
        Server.serverTerminationLatch.await();
    }

    private static synchronized void monitor() {
        Thread monitor = new Thread(() -> {
            try {
                // ----线程池等待
                Server.pool.awaitTermination(10, TimeUnit.MINUTES);
                // ----发送结束信号
                Server.serverTerminationLatch.countDown();
            } catch (InterruptedException e) {
                Server.logger.error(StringUtil.getFullStackTrace(e));
            }
        });
        monitor.setDaemon(true);
        monitor.start();
    }


    private static void finalized() {
        try {
            Server.pool.shutdown();
            Server.socket.close();
        } catch (IOException e) {
            Server.logger.error(StringUtil.getFullStackTrace(e));
        }
    }

    /**
     * @ClassName: ClientSocketHandler
     * @author: ivybest imiaodev@163.com
     * @date: 2019年5月21日 下午3:49:41
     * : client连接处理
     */
    private static class ClientSocketHandler implements Runnable {
        private Socket socket;
        private boolean flag = true;
        private String clientDesc;

        public ClientSocketHandler(Socket socket) {
            super();
            this.socket = socket;
            this.clientDesc = "/client" + this.socket.getInetAddress() + "/" + this.socket.getPort();
        }

        @Override
        public void run() {
            Server.logger.info("====>{}-[{}]-{}", "conn", clientDesc, "in...");
            try {
                // ----数据输出
                PrintStream outPrinter = new PrintStream(this.socket.getOutputStream());
                // ----数据接收
                InputStreamReader isReader = new InputStreamReader(this.socket.getInputStream());
                BufferedReader inReader = new BufferedReader(isReader);

                String echo = null;
                String resp = null;
                boolean finishFlag = false;
                do {
                    // ----接收client消息
                    echo = inReader.readLine();
                    logger.debug("====>{}-[{}]-{}", "receive", this.clientDesc, echo);
                    // ----判断是否结束会话
                    finishFlag = finishSeqs.contains(echo.toLowerCase());
                    // ----发送消息
                    resp = finishFlag ? "quit" : String.valueOf(echo.length());
                    logger.debug("====>{}-[{}]-{}", "publish", this.clientDesc, resp);
                    outPrinter.println(resp);
                } while (!finishFlag);

                if (flag) {
                    outPrinter.close();
                    inReader.close();
                }
            } catch (Exception e) {
                Server.logger.error("====>{}", StringUtil.getFullStackTrace(e));
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    Server.logger.error("====>{}", StringUtil.getFullStackTrace(e));
                }
            }
            logger.debug("====>{}-[{}]-{}\r\n", "conn", this.clientDesc, "out...");
        }
    }
}






