package edu.hit.core.oo.innerclass;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * <p> description:
 * <br>----------------------------------------
 * <br> 匿名类，也叫匿名内部类AnonymousInnerClass
 * <br> 可以继承抽象类和接口
 * <br> 用途：实现
 * <br>----------------------------------------
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @className AnonymousClassTest
 * @date 2019/12/4 10:11
 */
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AnonymousClassTest {

    @Before
    public void setUp() {
        log.debug("----> split line---------------------------");
    }

    @After
    public void tearDown() {
        log.debug("----> split line---------------------------");
    }

    @Test
    public void test_01_uooIns() {
        new Uoo().test();
    }


    /**
     * <bt> new Uoo() {}; 是 Anonymous Inner Class，是继承于 Uoo 的子类，
     * <br> 并且同时创建了 子类型实例
     * <br> {} 是类体，可以写任何类中的成员，可以继承抽象类和接口
     * <br> 用途：实现
     */
    @Test
    public void test_02_uooAnonymousClass() {
        new Uoo() {
            @Override
            public void test() {
                log.debug("anonymous class override class Uoo method test...");
            }
        }.test();
    }

    @Test
    public void test_03_dooAnonymousClass() {
        Doo d1 = new Doo() {
            @Override
            public void test() {
                log.debug("anonymous class implement interface Doo method test...");
            }
        };
        d1.test();
    }

}

@Slf4j
class Uoo {
    public void test() {
        log.debug("Uoo class method test origin implement...");
    }

    ;
}

interface Doo {
    public void test();
}

