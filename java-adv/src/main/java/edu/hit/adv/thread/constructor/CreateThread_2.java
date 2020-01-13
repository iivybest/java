package edu.hit.adv.thread.constructor;

import java.util.Optional;

/**
 * @author ivybest miao.xl@live.cn
 * @date 2017年9月19日 上午9:00:25
 */
public class CreateThread_2 {

    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            private int counter;

            @Override
            public void run() {
                try {
                    this.popStck(counter);
                } catch (Error e) {
                    e.printStackTrace();
                    System.out.println(this.counter);
                }
            }

            public void popStck(int i) {
                this.popStck(this.counter++);
            }

        }, "Test", 1 << 24).start();

        new Thread(() -> System.out.println(1 << 24)).start();

        Optional.of("Hello world").ifPresent(System.out::println);
    }
}
