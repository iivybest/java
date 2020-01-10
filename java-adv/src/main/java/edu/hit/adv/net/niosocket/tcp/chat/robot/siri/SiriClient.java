package edu.hit.adv.net.niosocket.tcp.chat.robot.siri;

import edu.hit.adv.net.niosocket.tcp.chat.robot.cortana.CharsetHelper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * <p>SiriClient</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年4月5日-上午9:58:35
 */
public class SiriClient {
    private boolean stop = false;
    private Selector selector;
    private ByteBuffer byteBuffer;
    private int serverPort;
    private Scanner scan;

    public SiriClient(int serverPort) {
        this.serverPort = serverPort;
    }

    public void launch() {
        this.init();
        scan = new Scanner(System.in);
        while (!stop) {
            System.out.println("please input...");
            String msg = scan.nextLine();
            if ("exit".equals(msg)) {
                this.stop = true;
                break;
            }
            System.out.println(this.AskSiri(msg));
        }
    }


    private void init() {
        this.byteBuffer = ByteBuffer.allocate(128);
    }


    public String AskSiri(String msg) {
        String answer = "";
        SocketChannel channel = null;
        try {
            channel = SocketChannel.open();
            channel.configureBlocking(false);
            // 请求连接
            channel.connect(new InetSocketAddress("localhost", this.serverPort));
            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_CONNECT);

            selector.select();
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                if (key.isConnectable()) {
                    if (channel.isConnectionPending()) {
                        if (channel.finishConnect()) {
                            // 只有当连接成功后才能注册OP_READ事件
                            key.interestOps(SelectionKey.OP_READ);

                            channel.write(CharsetHelper.encode(CharBuffer.wrap(msg)));
                        } else {
                            key.cancel();
                        }
                    }
                } else if (key.isReadable()) {

                    channel.read(byteBuffer);
                    byteBuffer.flip();
                    CharBuffer charBuffer = CharsetHelper.decode(byteBuffer);
                    answer = charBuffer.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) channel.close();
                if (selector != null) selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return answer;
    }


}
