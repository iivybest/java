package edu.hit.adv.net.socket.tcp.protocol;

/**
 * <p>TcpServerAble</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年5月11日-下午6:15:48
 */
public interface TcpServerAble {

    /**
     * <p>initialize</p>
     *
     * @param ctx
     * @author ivybest ivybestdev@163.com
     * @date 2016年5月11日-下午6:22:25
     */
    void initialize(TcpServerContext ctx);

    /**
     * <p>listen</p>
     *
     * @author ivybest ivybestdev@163.com
     * @date 2016年5月11日-下午6:22:31
     */
    void listen();

    /**
     * <p>stop</p>
     *
     * @author ivybest ivybestdev@163.com
     * @date 2016年5月11日-下午6:22:35
     */
    void stop();

}
