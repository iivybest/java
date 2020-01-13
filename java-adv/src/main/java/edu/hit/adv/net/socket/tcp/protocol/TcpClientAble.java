package edu.hit.adv.net.socket.tcp.protocol;

/**
 * <p>TcpClientAble</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年5月11日-下午6:54:05
 */
public interface TcpClientAble {

    /**
     * <p>initialize</p>
     *
     * @param ctx
     * @return
     * @author ivybest ivybestdev@163.com
     * @date 2016年5月11日-下午6:56:50
     */
    boolean initialize(TcpClientContext ctx);

    /**
     * <p>request</p>
     *
     * @param bytes
     * @return
     * @author ivybest ivybestdev@163.com
     * @date 2016年5月11日-下午6:59:27
     */
    byte[] request(byte[] bytes);

    /**
     * <p>stop</p>
     *
     * @author ivybest ivybestdev@163.com
     * @date 2016年5月11日-下午6:59:47
     */
    void stop();

}
