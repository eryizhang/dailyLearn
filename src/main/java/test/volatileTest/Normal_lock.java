package test.volatileTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/11 19:00
 * @Description:
 */
public class Normal_lock {

    private int inc = 0;
    private final Lock lock = new ReentrantLock();

    private void increase() {
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Normal_lock test = new Normal_lock();
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
