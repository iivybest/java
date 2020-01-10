package edu.hit.adv.net.niosocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.StandardSocketOptions;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class What21HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(What21HttpClient.class);

    private final ByteArrayOutputStream baHtmlPage = new ByteArrayOutputStream();
    private String htmlPage = null;
    // 字节缓存
    private final ByteBuffer buffer = ByteBuffer.allocate(2 * 1024);

    private void startHttpClient() throws InterruptedException {
        try (    // open Selector and ServerSocketChannel by calling the open() method
                 Selector selector = Selector.open();
                 SocketChannel socketChannel = SocketChannel.open();) {

            // check that both of them were successfully opened
            if ((socketChannel.isOpen()) && (selector.isOpen())) {
                // configure non-blocking mode
                socketChannel.configureBlocking(false);
                socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 2 * 1024);
                socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 2 * 1024);
                socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, false);
                // socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
                // socketChannel.connect(new InetSocketAddress(IP, DEFAULT_PORT));
                socketChannel.connect(createSocketAddress());

                // register the current channel with the given selector
                socketChannel.register(selector, SelectionKey.OP_CONNECT);

                while (true) {
                    // wait for incomming events
                    int num = selector.selectNow();
                    if (num == 0) {
                        // Thread.yield();
                        Thread.sleep(2000);
                        System.out.println("sleep: 2 sec");
                        continue;
                    }

                    // there is something to process on selected keys
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while (keys.hasNext()) {
                        SelectionKey key = (SelectionKey) keys.next();
                        // prevent the same key from coming up again
                        keys.remove();

                        if (!key.isValid()) continue;

                        if (key.isConnectable() && socketChannel.finishConnect()) {
                            System.out.println("Key: OP_CONNECT");
                            // reset the byte-array
                            baHtmlPage.reset();

                            // Connected --> Send the HTTP request
                            key.interestOps(SelectionKey.OP_WRITE);
                        } else if (key.isReadable()) {
                            System.out.println("Key: OP_READ");
                            if (readResponse(key)) {
                                logger.info("finished reading, htmlpage:{}" + htmlPage);
                            } else {
                                key.interestOps(SelectionKey.OP_READ);
                            }
                            // Once read is done --> we are done
                            // key.interestOps(SelectionKey.OP_WRITE);
                        } else if (key.isWritable()) {
                            System.out.println("Key: OP_WRITE");
                            if (writeHttpRequest(key)) {
                                // HTTP request is sent --> Get the response
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }

                }
            } else {
                // if ((serverSocketChannel.isOpen()) && (selector.isOpen())) {
                System.out.println("The server socket channel or selector cannot be opened!");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private static InetSocketAddress createSocketAddress() throws MalformedURLException {
        String urlStr = "http://192.168.84.22:8888/user/student/querybyname/Martial";
        URL url = new URL(urlStr);
        String host = url.getHost();
        int port = url.getPort() == -1 ? 80 : url.getPort();
        return new InetSocketAddress(host, port);
    }

    private boolean readResponse(SelectionKey key) throws IOException {
        boolean done = false;
        SocketChannel socketChannel = (SocketChannel) key.channel();

        int numRead = -1;
        do {
            buffer.clear();
            numRead = socketChannel.read(buffer);

            baHtmlPage.write(buffer.array(), 0, numRead);
            System.out.println("Server sent:" + new String(buffer.array(), 0, numRead, "UTF-8"));
        } while (numRead > 0);
        if (numRead == -1) {
            System.out.println("Connection closed by: " + socketChannel.getRemoteAddress());
            key.cancel();
            socketChannel.close();
            htmlPage = baHtmlPage.toString("UTF-8");
            done = true;
        }
        return done;
    }

    private boolean writeHttpRequest(SelectionKey key) throws IOException {
        boolean done = false;

        SocketChannel socketChannel = (SocketChannel) key.channel();
        String request = "GET / HTTP/1.1\r\n" + "Host: www.ivybest.com\r\n" + "Cache-Control: no-cache\r\n\r\n";

        ByteBuffer randomBuffer = ByteBuffer.wrap(request.getBytes("UTF-8"));
        int rem = randomBuffer.remaining();
        int num = socketChannel.write(randomBuffer);

        if (rem == num) {
            done = true;
            System.out.printf("Request written:%s\n", request);
        }
        return done;
    }

    public static void main(String[] args) throws InterruptedException {
        What21HttpClient client = new What21HttpClient();
        client.startHttpClient();
    }

}