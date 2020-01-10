package edu.hit.adv.annotation.validator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * <p> description: junit testcase suite demo
 * <br>---------------------------------------------------------
 * <br> 1、创建一个public空类，含有默认构造方法
 * <br> 2、使用注解
 * <br>    org.junit.runner.RunWith、
 * <br>    org.junit.runners.Suite.SuiteClasses 修饰此类
 * <br> 3、将 org.junit.runners.Suite 注入 RunWith
 * <br> 4、将测试类们放入 @SuiteClasses
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2016/2/18 16:30
 */
@RunWith(Suite.class)
@SuiteClasses({
        StudentTestcase1.class,
        StudentTestcase2.class
})
public class StudentTestSuite {

}
