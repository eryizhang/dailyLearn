package test.countdownish;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/29 15:21
 * @Description:
 */


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author qifuguang
 * @date 15/8/25 00:34
 */
public class TestCyclicBarrier {
    private static final int THREAD_NUMBER = 5;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(THREAD_NUMBER, new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getId() + "：我宣布，所有小伙伴写入数据完毕");
            }
        });
        for (int i = 0; i < THREAD_NUMBER; i++) {
            if (i < THREAD_NUMBER - 1) {
                Thread t = new Thread(new Worker(barrier));
                t.start();
            } else {  //最后一个线程故意延迟3s创建。
                Thread.sleep(3000);
                Thread t = new Thread(new Worker(barrier));
                t.start();
            }
        }

        Thread.sleep(10000);
        System.out.println("================barrier重用==========================");
        if(barrier.isBroken())
        {
            barrier.reset();
        }
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread t = new Thread(new Worker(barrier));
            t.start();
        }

    }

    static class Worker implements Runnable {
        private CyclicBarrier barrier;

        public Worker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        public void run() {
            int time = RANDOM.nextInt(1000);
            System.out.println(Thread.currentThread().getId() + "：我需要" + time + "毫秒时间写入数据.");
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + "：写入数据完毕，等待其他小伙伴..."+barrier.getNumberWaiting()+"pat"+barrier.getParties());
            try {
                barrier.await(2000, TimeUnit.MILLISECONDS); // 只等待2s，必然会等待最后一个线程超时
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getId() + "：所有线程都写入数据完毕，继续干活...");
        }
    }
}

