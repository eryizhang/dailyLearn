package test.countdownish;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/10/29 10:30
 * @version: 1.0
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println("shadongxi ");
            }
        });
        for (int i = 0; i < N; i++) {
            new Writer(barrier, i + 1).start();
        }

        System.out.println("CyclicBarrier重用");
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < N; i++) {
            new Writer(barrier, i + 10).start();
        }
    }

    static class Writer extends Thread {
        private final int io;
        private final CyclicBarrier cyclicBarrier;

        Writer(CyclicBarrier cyclicBarrier, int io) {
            this.cyclicBarrier = cyclicBarrier;
            this.io = io;
        }

        @Override
        public void run() {
            //int i=(int) (Math.random()*10);
            long l = 0;
            try {

                System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...睡" + io);

                Thread.sleep(2000 * io);      //以睡眠来模拟写入数据操作
                l = System.currentTimeMillis();
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕****" + l);
                cyclicBarrier.await(io, TimeUnit.SECONDS);
                //Thread.sleep(2000*io);      //以睡眠来模拟写入数据操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                System.out.println("BrokenBarrierException" + Thread.currentThread().getName() + "等待时间" + (
                        System.currentTimeMillis() - l) + "当前时间" + System.currentTimeMillis());
            } catch (TimeoutException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                System.out.println(
                        "TimeoutException" + Thread.currentThread().getName() + "等待时间" + (System.currentTimeMillis()
                                - l) + "当前时间" + System.currentTimeMillis());
            }
            System.out.println(
                    "所有线程写入完毕，继续处理其他任务..." + Thread.currentThread().getName() + "等待时间" + (System.currentTimeMillis()
                            - l) + "当前时间" + System.currentTimeMillis());
        }
    }
}
