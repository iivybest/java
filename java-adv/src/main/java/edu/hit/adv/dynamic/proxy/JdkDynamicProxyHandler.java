package edu.hit.adv.dynamic.proxy;

import lombok.extern.slf4j.Slf4j;
import org.ivy.util.annotation.ThreadSafe;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p> description: Dynamic Proxy Handler Provided by JDK
 * <br>---------------------------------------------------------
 * <br> 1.什么是动态代理?
 * <br>     答：动态代理可以提供对另一个对象的访问，同时隐藏实际对象的具体事实。
 * <br>         代理一般会实现它所表示的实际对象的接口。代理可以访问实际对象，
 * <br>         但是延迟实现实际对象的部分功能，实际对象实现系统的实际功能，
 * <br>         代理对象对客户隐藏了实际对象。客户不知道它是与代理打交道还是与实际对象打交道。
 * <br> 2.为什么使用动态代理？
 * <br>     答：因为动态代理可以对请求进行任何处理
 * <br> 3.使用它有哪些好处?
 * <br>     答：因为动态代理可以对请求进行任何处理
 * <br> 4.哪些地方需要动态代理?
 * <br>     答：不允许直接访问某些类；对访问要做特殊处理等
 * <br>---------------------------------------------------------
 * <br> # Jdk 对动态代理的支持，只支持对接口的的实现。其实现主要通过
 * <br>     java.lang.reflect.Proxy 类
 * <br>     java.lang.reflect.InvocationHandler接口。
 * <br>     Proxy 类主要用来获取动态代理对象，
 * <br>     InvocationHandler 接口用来实现用自定义的代理内容
 * <br>---------------------------------------------------------
 * <br> 应用：
 * <br>     动态代理适用于-AOP切面编程，
 * <br>     如 预处理消息、过滤消息、以及事后处理消息等
 * <br>---------------------------------------------------------
 * <br> TODO: 如何解决线程安全问题:
 * <br>     1、废弃 handler 的 单例模式 ？
 * <br>     2、使用 ThreadLocal ？
 * <br>
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2014/6/3 16:32
 */
@Slf4j
@ThreadSafe(value = true, msg = {})
public class JdkDynamicProxyHandler implements InvocationHandler {
    /**
     * the proxy instance, this instance proxy target
     */
//    private static JdkDynamicProxyHandler proxy;
    /**
     * the target which was proxy by proxy instance
     */
    private Object target;

    private JdkDynamicProxyHandler() {
    }

    public static JdkDynamicProxyHandler instance() {
//        return InstanceHolder.HOLDER.getInstance();
        return new JdkDynamicProxyHandler();
    }


    private enum InstanceHolder {
        HOLDER;
        private JdkDynamicProxyHandler instance;

        /**
         * constructor
         * The JVM guarantees that the method is executed only once
         */
        InstanceHolder() {
            this.instance = new JdkDynamicProxyHandler();
        }

        /**
         * get instance the holder hold
         *
         * @return JdkDynamicProxyHandler
         */
        public JdkDynamicProxyHandler getInstance() {
            return this.instance;
        }

    }

    /**
     * 绑定委托对象并返回一个代理类
     * 生成被代理对象的代理对象，这个方法也可以定义到InvocationHandler实现类的外面
     *
     * @param target target
     * @return proxy
     */
    public <T> T bind(T target) {
        this.target = target;
        return (T) Proxy.newProxyInstance(
                this.target.getClass().getClassLoader(),
                this.target.getClass().getInterfaces(),
                this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        before();
        // 执行方法
        result = method.invoke(this.target, args);
        after();
        return result;
    }


    private void before() {
        log.debug("====>executed a series of operations before target operation is executed");
        log.debug("====>1、login authentication");
        log.debug("====>2、logging");
        log.debug("====>3、online population count ++");
    }

    private void after() {
        log.debug("====>executed a series of operations after target operation is executed");
        log.debug("====>1、logout");
        log.debug("====>2、logging");
        log.debug("====>3、online population count --");
    }


}
