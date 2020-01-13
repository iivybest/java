/**
 * Filename 	NioSocketHttpClient
 * TODO
 *
 * @author ivybest ivybestdev@163.com
 * @version V1.0
 * CreateDate 	2017年8月7日 下午12:32:41
 * Company 		IB.
 * Copyright 	Copyright(C) 2010-
 * All rights Reserved, Designed By ivybest
 * <p>
 * Modification History:
 * Date			Author		Version		Discription
 * --------------------------------------------------------
 * 2017年8月7日	ivybest		1.0			new create
 */
package edu.hit.adv.net.niosocket;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Map;

/**
 *  简单nio http client
 * @author Createdate 2017年8月7日 下午12:32:41
 */
public class NioSocketHttpClient {

    private enum HttpType {
        GET, POST, PUT, DELETE;

        public String getCode() {
            return this.name();
        }
    }

    // 字符集
    private static final Charset CHARSET = Charset.forName("UTF-8");
    // 字节缓冲
    private static final ByteBuffer BYTE_BUFFER = ByteBuffer.allocate(1024);

    // HTTP协议 换行
    private static final String LINE_FEED = "\r\n";
    // HTTP协议 空格
    private static final String BLACK_SPACE = " ";

    public static HttpResponse doService(HttpType type, String url, Map<String, String> params) {
        StringBuffer httpResp = new StringBuffer();
        try (SocketChannel channel = SocketChannel.open(createSocketAddress(url))) {
            // 配置通道使用非阻塞模式
            channel.configureBlocking(false);
            channel.write(ByteBuffer.wrap(prepareHttpRequest(type, url).getBytes()));
//			channel.connect(createSocketAddress(url));

            while (channel.read(BYTE_BUFFER) != -1) {
                // flip方法在读缓冲区字节操作之前调用
                BYTE_BUFFER.flip();
                // 使用Charset.decode方法将字节转换为字符串
                httpResp.append(CHARSET.decode(BYTE_BUFFER));
                // 清空缓冲
                BYTE_BUFFER.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HttpResponse(httpResp.toString());
    }

    /**
     * 准备http headers
     */
    private static String prepareHttpHdaders(HttpType type, String url) {
        return new StringBuffer()
                .append(type.getCode()).append(BLACK_SPACE).append(url).append(BLACK_SPACE).append("HTTP/1.1").append(LINE_FEED)
                .append("Host:www.ivybest.com").append(LINE_FEED)
                .append("User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").append(LINE_FEED)
                .append("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8").append(LINE_FEED)
                .append("Accept-Language:zh-CN,zh;q=0.8,en;q=0.6").append(LINE_FEED)
                .append("Accept-Encoding:gzip, deflate, sdch, br").append(LINE_FEED)
                .append("Cache-Control:max-age=0").append(LINE_FEED)
                .append("Connection:close").append(LINE_FEED)            // 默认为keep-alive
                .toString();
    }

    private static String prepareHttpRequest(HttpType type, String url) {
        return new StringBuffer().append(prepareHttpHdaders(type, url)).append(LINE_FEED).toString();
    }

    /**
     *  		createSocketAddress
     *  根据url，生成SocketAddress
     * @param        url
     * @throws MalformedURLException
     * @return InetSocketAddress
     */
    private static InetSocketAddress createSocketAddress(String url) throws MalformedURLException {
        URL urlIns = new URL(url);
        return new InetSocketAddress(urlIns.getHost(), urlIns.getPort() == -1 ? 80 : urlIns.getPort());
    }

    public static class HttpResponse {

        String resp;
        String header;
        String responseBody;

        public HttpResponse(String resp) {
            this.resp = resp;
            if (resp == null || resp.length() <= 0) return;

            String[] items = resp.split("(\\s*)(\r\n){2,}");
            this.header = items[0];
            this.responseBody = items[1];
        }

        public String getHeader() {
            return this.header;
        }

        public String getResponseBody() {
            return this.responseBody;
        }

        /* (non-Javadoc)
         * <p>Title: toString</p>
         * <p>Description: TODO</p>
         * @return
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return this.resp;
        }

    }


    public static void main(String[] args) {
        try {
            System.out.println(doService(HttpType.GET,
                    "http://192.168.84.22:8888/user/student/querybyname/Martial",
                    null).getResponseBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
