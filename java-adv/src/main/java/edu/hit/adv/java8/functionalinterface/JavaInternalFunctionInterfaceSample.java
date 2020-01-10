package edu.hit.adv.java8.functionalinterface;

import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p> description:
 * <br>----------------------------------------
 * <br> java 内置函数型接口示例
 * <br>----------------------------------------
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * <br>----------------------------------------------
 * Consumer<T>		消费型接口		void accept(T t)
 * Supplier<T>		供给型接口		T get()
 * Function<T, R>	函数型接口		R apply(T t)
 * Predicate<T>		断言型接口		boolean test(T t)
 * <br>----------------------------------------------
 * @className JavaInternalFunctionInterfaceSample
 * @date 2019/12/4 14:46
 */
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JavaInternalFunctionInterfaceSample {

    @Test
    public void test_01_consumerInterface() {
        this.consume(1_000, System.out::println);
        this.consume(1_000, e -> log.debug("====>[足疗店]-小张消费了{} 元", e));
        this.consume(1_500, e -> log.debug("====>[游乐场]-小王消费了{} 元", e));
        this.consume(1_500, e -> log.debug("====>[图书馆]-小丽消费了{} 元", e));
    }

    @Test
    public void test_02_supplierInterface() {
        /* 生产 100 个 0-100 的随机数*/
        List<Integer> num = this.supply(100, () -> (int) (Math.random() * 100));
        /* 打印能被 13 整数的整数*/
        num.stream().filter(e -> e % 13 == 0).map(e -> "====>[test_02_supplierInterface] " + e).forEach(log::debug);
    }

    @Test
    public void test_03_functionInterface() {
        String origin = "12345abcde中国";
        /* 截取前 4 个字符 */
        String handled = this.apply(origin, e -> e.substring(0, 4));
        log.debug("====>[test_03_functionInterface]-{} ", handled);
        /* 截取后 4 个字符 */
        Stream.of(origin).map(e -> e.substring(4, e.length())).forEach(log::debug);
    }

    @Test
    public void test_04_predicateInterface() {
        // 构造 100 个随机数
        List<Integer> num = this.supply(10, () -> (int) (Math.random() * 100));
        // 定义一个断言接口实现类
        Predicate<String> valid = e -> e.contains("1");
        // 打印所有数字
        num.stream().map(String::valueOf).forEach(log::debug);
        log.info("====> split line ------------------");
        // 打印符合要求的数字
        num.stream()
                .map(String::valueOf)
                .filter(e -> this.predicate(e, valid))
                .sorted()
                .forEach(log::debug);
    }


    private void consume(double money, Consumer<Double> op) {
        op.accept(money);
    }

    /**
     * 供给型接口示例，应用较多，
     * eg，实现一个资源池，由用户指定池中实例的构建方式
     *
     * @param num
     * @param supplier
     * @return
     */
    private List<Integer> supply(int num, Supplier<Integer> supplier) {
        List<Integer> coll = new ArrayList<Integer>();
        IntStream.range(0, num).forEach(e -> coll.add(supplier.get()));
        return coll;
    }

    private String apply(String origin, Function<String, String> function) {
        return function.apply(origin);
    }

    private boolean predicate(String origin, Predicate<String> predicate) {
        return predicate.test(origin);
    }
}






