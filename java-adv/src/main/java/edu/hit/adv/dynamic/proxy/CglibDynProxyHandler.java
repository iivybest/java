package edu.hit.adv.dynamic.proxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p> description: cglib dynamic proxy handler
 * <br>----------------------------------------------------------
 * <br> # JDK Dynamic Proxy:
 * <br>     The dynamic mechanism of jdk can only proxy the class
 * <br>     that implements interface.
 * <br>
 * <br> # Cglib Dynamic Proxy:
 * <br>    The dynamic mechanism of Cglib is that to use class
 * <br>    inheritance. which requires that the class to be proxy
 * <br>    must contain an empty constructor, and the class cannot
 * <br>    be modified by final.
 * <br>
 * <br> # Cglib Dynamic Proxy dependency asm package
 * <br>----------------------------------------------------------
 * <br> # JDK Dynamic Proxy:
 * <br>
 * <br>
 * <br> # Cglib Dynamic Proxy:
 * <br>
 * <br>
 * <br>----------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2014/6/3 11:16
 */
@Slf4j
public class CglibDynProxyHandler implements MethodInterceptor {

    private CglibDynProxyHandler() {
    }

    /**
     * get CglibDynProxyHandler singleton instance
     *
     * @return CglibDynProxyHandler
     */
    public static CglibDynProxyHandler instance() {
        return InstanceHolder.HOLDER.getInstance();
    }

    /**
     * get proxy instance implement by cglib
     *
     * @param target target
     * @param <T>    target type
     * @return T proxy instance
     */
    public <T> T getProxyInstance(T target) {
        Enhancer enhancer;
        enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result;
        before();
        result = proxy.invokeSuper(obj, args);
        after();
        return result;
    }

    private void before() {
        log.debug("====>executed a series of operations before target operation is executed");
        log.debug("====>1、cglib login authentication");
        log.debug("====>2、cglib logging");
        log.debug("====>3、cglib online population count ++");
    }

    private void after() {
        log.debug("====>executed a series of operations after target operation is executed");
        log.debug("====>1、cglib logout");
        log.debug("====>2、cglib logging");
        log.debug("====>3、cglib online population count --");
    }

    /**
     * CglibDynProxyHandler Singleton instance holder
     */
    private enum InstanceHolder {
        /**
         * the holder enum instance
         */
        HOLDER;
        /**
         * CglibDynProxyHandler singleton instance;
         */
        private CglibDynProxyHandler instance;

        /**
         * constructor
         * The JVM guarantees that the method is executed only once
         */
        InstanceHolder() {
            this.instance = new CglibDynProxyHandler();
        }

        /**
         * get instance the holder hold
         *
         * @return CglibDynProxyHandler
         */
        public CglibDynProxyHandler getInstance() {
            return this.instance;
        }
    }

}




