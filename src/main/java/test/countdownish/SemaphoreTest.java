package test.countdownish;

import java.util.concurrent.Semaphore;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/10/30 19:32
 * @version: 1.0
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        int N = 8;            //工人数
        Semaphore semaphore = new Semaphore(5); //机器数目

        //ResizeableSemaphore resizeableSemaphore;
        for (int i = 0; i < N; i++) {
            new Worker(i, semaphore).start();
        }
    }

    static class Worker extends Thread {
        private final int num;
        private final Semaphore semaphore;


        Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人" + num + "占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人" + num + "释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
