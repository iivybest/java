package edu.hit.adv.net.socket.tcp.demo1;

import org.ivy.util.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collection;


/**
 * @ClassName: Client
 * @author: ivybest imiaodev@163.com
 * @date: 2019年5月20日 下午4:58:17
 */
public class Client {
    private static Logger logger = LoggerFactory.getLogger(Client.class);
    private static Socket socket;
    private static int port;
    private static String ip;
    private static Collection<String> finishSeqs;
    private static String serverDesc;
    private static String clientDesc;


    private static void initCommon() {
        ip = "127.0.0.1";
        port = 2000;
        finishSeqs = Arrays.asList("bye", "byebye", "goodbey", "exit", "quit");
    }

    private static void init() throws IOException {
        Client.initCommon();

        int timeout = 30000;
        InetSocketAddress address = new InetSocketAddress(Client.ip, Client.port);

        Client.socket = new Socket();
        Client.socket.setSoTimeout(timeout);
        Client.socket.connect(address, timeout);

        Client.serverDesc = "/server" + socket.getInetAddress() + "/" + socket.getPort();
        Client.clientDesc = "/client" + socket.getLocalAddress() + "/" + socket.getLocalPort();
    }


    public static void main(String[] args) throws IOException {
        // ----初始化
        Client.init();
        logger.debug("---->{}-[{}]-{}", "client", Client.clientDesc, "in...");
        logger.debug("---->{}-[{}]-{}", "conn", Client.serverDesc, "in...");
        // ----Client与Server通信
        try {
            Client.doservice(socket);
        } catch (IOException e) {
            logger.error("---->{}", StringUtil.getFullStackTrace(e));
        }
    }

    private static void doservice(Socket socket) throws IOException {
        // ----发送消息----流
        InputStream in = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        OutputStream out = socket.getOutputStream();
        PrintStream printer = new PrintStream(out);

        // ---- 读取server消息----流
        InputStream _in = socket.getInputStream();
        BufferedReader _reader = new BufferedReader(new InputStreamReader(_in));

        boolean flag = true;
        String resp = null;
        do {
            // ----发送消息
            String echo = reader.readLine();
            printer.println(echo);
            logger.debug("---->{}-{}", "publish", echo);
            // ----读取server消息
            resp = _reader.readLine();
            logger.debug("---->{}-{}", "receive", resp);
        } while ((flag = !finishSeqs.contains(resp.toLowerCase())));

        if (!flag) {
            printer.close();
            _reader.close();
            socket.close();
            logger.debug("---->{}-[{}]-{}", "conn", Client.serverDesc, "out...");
            logger.debug("---->{}-[{}]-{}", "Client", Client.clientDesc, "out...");
        }
    }
}









