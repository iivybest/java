package edu.hit.adv.net.socket.tcp.demo1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IPv4地址和int数字的互相转换
 *
 * @author imiaodev@163.com
 */
public class IPv4IntTransformer {

    // 匹配数字
    private static final Pattern PATTERN_DIGIT = Pattern.compile("\\d+");

    /**
     * IPv4地址转换为int类型数字
     */
    public static int ip2Integer(String ipv4Addr) {
        // 判断是否是ip格式的
        if (!isIPv4Address(ipv4Addr)) {
            throw new RuntimeException("Invalid ip address");
        }

        // 匹配数字
        Matcher matcher = PATTERN_DIGIT.matcher(ipv4Addr);
        int result = 0;
        int counter = 0;
        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group());
            result = (value << 8 * (3 - counter++)) | result;
        }
        return result;
    }


    public static byte[] ip2bytearray(String ipv4Addr) {
        byte[] bytes = new byte[4];
        // 判断是否是ip格式的
        if (!isIPv4Address(ipv4Addr)) throw new RuntimeException("Invalid ip address");
        // 匹配数字
        Matcher matcher = PATTERN_DIGIT.matcher(ipv4Addr);
        int idx = 0;
        while (matcher.find()) bytes[idx++] = Byte.parseByte(matcher.group());
        return bytes;
    }


    /**
     * 判断是否为ipv4地址
     */
    private static boolean isIPv4Address(String ipv4Addr) {
        String lower = "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])"; // 0-255的数字
        String regex = lower + "(\\." + lower + "){3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ipv4Addr);
        return matcher.matches();
    }

    /**
     * 将int数字转换成ipv4地址
     */
    public static String integer2Ip(int ip) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        boolean needPoint = false; // 是否需要加入'.'
        for (int i = 0; i < 4; i++) {
            if (needPoint) {
                sb.append('.');
            }
            needPoint = true;
            int offset = 8 * (3 - i);
            num = (ip >> offset) & 0xff;
            sb.append(num);
        }
        return sb.toString();
    }
}













