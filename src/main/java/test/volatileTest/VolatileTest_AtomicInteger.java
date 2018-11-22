package test.volatileTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/6 15:00
 * @version: 1.0
 */
public class VolatileTest_AtomicInteger {
    private final AtomicInteger inc = new AtomicInteger();

    private void increase() {
        inc.getAndIncrement();
    }

    public static void main(String[] args) {
        VolatileTest_AtomicInteger test = new VolatileTest_AtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        test.increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount() > 3)  //保证前面的线程都执行完
        {
            Thread.yield();
        }
        System.out.println(test.inc);
    }
}
