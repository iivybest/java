package edu.hit.adv.rmi.demo.server;

import edu.hit.adv.rmi.demo.api.ClubService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * <p>RMIServer</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2014年11月4日 - 下午6:20:16
 */
public class RMIServer {
    /*
     * 1. 创建远程接口及声明远程方法（HelloInterface.java）
     * 2. 实现远程接口及远程方法（继承UnicastRemoteObject）(Hello.java)
     * 3. 启动RMI注册服务，并注册远程对象（HelloServer.java）
     * 4. 客户端查找远程对象，并调用远程方法（HelloClient）
     * 5. 执行程序：启动服务HelloServer；运行客户端HelloClient进行调用
     */
    public static void main(String[] args) {
        try {
            ClubService service = new ClubServiceImpl();
            // 注册通讯端口
            LocateRegistry.createRegistry(6600);
            // 注册通讯路径
            Naming.rebind("rmi://127.0.0.1:6600/service", service);
            System.out.println("server start...");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
