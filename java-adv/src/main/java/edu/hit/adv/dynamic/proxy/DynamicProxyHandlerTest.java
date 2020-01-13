package edu.hit.adv.dynamic.proxy;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;

/**
 * <p> description: the testcase for DynamicProxyHandler
 * <br>---------------------------------------------------------
 * <br> JdkDynamicProxyHandler
 * <br> CglibDynProxyHandler
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2014/6/3 09:58
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class DynamicProxyHandlerTest {

    private static void split() {
        log.debug("====>split line------------------------------");
    }

    @After
    public void tearDown() {
        split();
    }

    @Test
    public void test_01_proxyByJdk() {
        Hello[] objArr = {
                new HelloImpl("Zlatan"),
                new HelloImpl("Rashford"),
                new HelloImpl("Martial")
        };
        JdkDynamicProxyHandler proxy = JdkDynamicProxyHandler.instance();
        Arrays.stream(objArr).forEach(e -> {
            proxy.bind(e).syaHello("Messi");
            split();
        });
    }

    @Test
    public void test_02_proxyByCglib() {
        Hello[] objArr = {
                new HelloImpl().setInstanceId("Zlatan"),
                new HelloImpl().setInstanceId("Rashford"),
                new HelloImpl().setInstanceId("Martial"),
        };
        CglibDynProxyHandler proxy = CglibDynProxyHandler.instance();
        Arrays.stream(objArr).forEach(e -> {
            Hello implCglib = (Hello) proxy.getProxyInstance(e);
            implCglib.syaHello("Messi");
            split();
        });
    }


    private interface Hello {
        /**
         * sayHello to sb.
         *
         * @param data data
         */
        void syaHello(String data);

        /**
         * set instanceId
         *
         * @param instanceId instanceId
         * @return Hello this instance
         */
        Hello setInstanceId(String instanceId);
    }

    private static class HelloImpl implements Hello {
        private String instanceId;

        public HelloImpl() {
            super();
        }

        /**
         * <p> constructor
         * <br>--------------------------------------------------------------
         * <br> Setting instance member variables through constructor method
         * <br>
         * <br> # Notes:
         * <br> Constructor setting member variables that is effective
         * <br> for JDK Dynamic Proxy, is not effective for Cglib Proxy
         * <br>--------------------------------------------------------------
         * <p/>
         *
         * @param instanceId instanceId
         */
        public HelloImpl(String instanceId) {
            this();
            this.instanceId = instanceId;
        }


        @Override
        public void syaHello(String data) {
            String tips = this.instanceId + " say hello: " + data + " ...";
            System.out.println(tips);
        }

        /**
         * <p> set instanceId
         * <br>--------------------------------------------------------
         * <br> # Desc:
         * <br> Setting instance member variables through setter method
         * <br> # Notes:
         * <br> setter method setting member variables is effective
         * <br> for both jdk and cglib
         * <br> # Recommend
         * <br>--------------------------------------------------------
         * <p/>
         *
         * @param instanceId instanceId
         * @return Hello this instance
         */
        @Override
        public Hello setInstanceId(String instanceId) {
            this.instanceId = instanceId;
            return this;
        }
    }


}
