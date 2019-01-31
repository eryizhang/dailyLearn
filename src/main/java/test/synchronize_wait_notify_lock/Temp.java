package test.synchronize_wait_notify_lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/7 15:07
 * @version: 1.0
 */
public class Temp {
    private int count = 0;

    private void waiter() throws InterruptedException {
        synchronized (this) {
            System.out.println("等待");
            wait();
            System.out.println(count);
        }
    }

    static class Waiter implements Runnable {
        private final Temp temp;

        Waiter(Temp temp) {
            this.temp = temp;
        }

        @Override
        public void run() {
            try {
                temp.waiter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Notifyer implements Runnable {
        private final Temp temp;

        Notifyer(Temp temp) {
            this.temp = temp;
        }

        @Override
        public void run() {
            try {
                temp.notifyer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void notifyer() throws InterruptedException {
        synchronized (this) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("唤醒");
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread() + "notifyer:" + i);
                count += i;
            }
            notify();
        }
    }

    public static void main(String[] args) {
        Temp temp = new Temp();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Waiter(temp));
        executorService.execute(new Notifyer(temp));
        executorService.shutdown();
    }
}

