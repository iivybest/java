package edu.hit.adv.net.socket.tcp.demo2;

import org.ivy.util.common.UniSeqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

/**
 * @ClassName: Client
 * @author: ivybest imiaodev@163.com
 * @date: 2019年5月29日 上午9:31:40
 * : TODO(用一句话描述该文件做什么)
 */
public class Client {
    private static Logger logger = LoggerFactory.getLogger(Client.class);
    private String id;
    private String name;

    private String ip;
    private int port;
    private int localPort;

    private Socket socket;
    /* @brief 交流结束语 */
    private Collection<String> finishSeqs;
    /* @brief 服务端描述信息 */
    private String serverDesc;
    /* @brief 客户端描述信息 */
    private String clientDesc;


    private Client initStaticVaritable() {
        this.finishSeqs = Arrays.asList("bye", "byebye", "goodbey", "exit", "quit");
        this.id = UniSeqUtil.generateUniSeq("TCP-C");
        this.name = this.id;
        this.ip = "127.0.0.1";
        this.port = 20000;
        this.localPort = 20001;
        return this;
    }


    private Client createSocket() throws IOException {
		/*
		// ----无代理模式，等效于空 constructor
		this.socket = new Socket(Proxy.NO_PROXY);
		// ----新建一个有HTTP代理的套接字，数据通过www.baidu.com:8080进行转发
		// ----需要事先建立代理服务器
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Inet4Address.getByName("www.baidu.com"), 8800));
		this.socket = new Socket(proxy);
		// ----新建\绑定\连接 套接字, 指定 server 端 ip、port
		// ----因为有些设置需要在连接之前，所以不建议这种初始化方式
		this.socket = new Socket(Inet4Address.getLocalHost(), this.port);
		this.socket = new Socket("localhost", this.port);
		// ----新建\绑定\连接 套接字, 指定 server 端 ip、port, 指定 client 端 ip、port
		this.socket = new Socket(Inet4Address.getLocalHost(), this.port, Inet4Address.getLocalHost(), this.localPort);
		 */


        // ----新建套接字
        this.socket = new Socket();
        // ----bind 本地端口
        this.socket.bind(new InetSocketAddress(Inet4Address.getLocalHost(), this.localPort));
        // ----连接 server 端
//		this.socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), this.port));
        return this;
    }

    private Client initSocket() throws SocketException {
        if (this.socket == null) return this;
        /*
         * 设置读取超时时间 2_000 毫秒
         * sotimeout 设置有阻塞部分的超时时间，socket 中 connet、read 有阻塞
         * socket 中专门设置 connet 的超时时间
         * 所以此处是设置 读取超时时间
         */
        this.socket.setSoTimeout(2_000);
        // ----设置是否复用未完全关闭的Socket地址，对于制定bind操作后的套接字有效
        this.socket.setReuseAddress(true);
        // ----是否开启 Nagle 算法 ---- 优化网络空间，减少心跳包发送
        this.socket.setTcpNoDelay(true);
        // ----是否需要在长时间无数据响应时发送确认数据（类似心跳包，大约2小时）
        this.socket.setKeepAlive(true);
        /*
         * 对于 close 操作的处理；默认 (false, 0)
         * false, 0		关闭时立即返回，底层系统接管输出流，将缓冲区内的数据发送完成
         * true, 0		关闭时立即返回，缓冲区数据抛弃，直接发送 RST 结束命令到对方，并无需经过 2MSL等待
         * true, 200	关闭时最长阻塞200毫秒，之后缓冲区数据抛弃，直接发送 RST 结束命令到对方，并无需经过 2MSL等待
         */
        this.socket.setSoLinger(true, 20);
        // ----是否紧急数据内敛到业务数据中，默认 false
        // ----一般不建议设置为 true， 容易导致行为数据脏乱
        // ----紧急数据发送 socket.setUrgentData(1); 发送 int 值的低 8 位
        this.socket.setOOBInline(false);
        // ----设置接收缓冲器大小 64K
        this.socket.setReceiveBufferSize(64 * 1024 * 1024);
        // ----设置发送缓冲器大小 64K， 默认 32 K
        this.socket.setSendBufferSize(64 * 1024 * 1024);
        /*
         * 设置性能参数
         * 设置 3 者权重，（比例关系）
         * socket.setPerformancePreferences(connectionTime, latency, bandwidth);
         * connectionTime	连接时间	表示短连接是否重要
         * latency			延迟		若要数据及时发送，增加延迟的比重
         * bandwidth		带宽		若要带宽利用率较高，增加带宽比重
         * 一般延迟较低时，带宽占用比较大时，两者相互影响
         */
        this.socket.setPerformancePreferences(1, 1, 1);
        return this;
    }

    private Client postSocketBuild() {
        this.serverDesc = "/server" + this.socket.getInetAddress() + "/" + this.socket.getPort();
        this.clientDesc = "/client" + this.socket.getLocalAddress() + "/" + this.socket.getLocalPort();
        return this;
    }

    private Client connnet() throws UnknownHostException, IOException {
        this.socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), this.port), 3_000);
        return this;
    }

    private void doservice() throws IOException {
        // ----发送消息----流
        Scanner scanner = new Scanner(System.in);
        OutputStream out = socket.getOutputStream();
        PrintStream printer = new PrintStream(out);

        // ---- 读取server消息----流
        InputStream _in = socket.getInputStream();
        BufferedReader _reader = new BufferedReader(new InputStreamReader(_in));

        boolean flag = true;
        String resp = null;
        do {
            // ----发送消息
            String echo = scanner.nextLine();
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
            logger.debug("---->{}-[{}]-{}", "conn", this.serverDesc, "out...");
            logger.debug("---->{}-[{}]-{}", "Client", this.clientDesc, "out...");
        }
    }

    public void launch() throws Exception {
        this.initStaticVaritable()
                .createSocket()
                .initSocket()
                .postSocketBuild()
                .connnet()
                .doservice();
    }

    public static void main(String[] args) throws Exception {
//		new Client().launch();
        Scanner scanner = new Scanner(System.in);
        String echo = null;
        do {
            echo = scanner.nextLine().toUpperCase();
            logger.debug("---->{}-{}", "publish", echo);
        } while (!"byebye".equals(echo));
    }
}












