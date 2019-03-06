package test.synchronize_wait_notify_lock.locktest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/6 11:35
 * @Description:
 */
public class ReentrantLockTLockInterruptibly {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLockTLockInterruptibly test = new ReentrantLockTLockInterruptibly();
        MyThread thread1 = new MyThread(test);
        MyThread thread2 = new MyThread(test);
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("尝试终断");
        thread1.interrupt();
        thread2.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName() + "得到了锁");
            long startTime = System.currentTimeMillis();
            Thread.sleep(3000L);
        } finally {
            System.out.println(Thread.currentThread().getName() + "执行finally");
            lock.unlock();
            System.out.println(thread.getName() + "释放了锁");
        }
    }

     static class MyThread extends Thread {
        private ReentrantLockTLockInterruptibly test = null;

        public MyThread(ReentrantLockTLockInterruptibly test) {
            this.test = test;
        }

        @Override
        public void run() {

            try {
                test.insert(Thread.currentThread());
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被中断");
            }
        }
    }
}
