package test.synchronize_wait_notify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/7 14:56
 * @version: 1.0
 */
public class RunnableTest implements Runnable {
    private static void testSyncBlock() {
        synchronized (RunnableTest.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getId() + "testSyncBlock:" + i);
            }
        }
    }

    @Override
    public void run() {
        RunnableTest.testSyncBlock();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        RunnableTest rt = new RunnableTest();
        RunnableTest rt1 = new RunnableTest();
        exec.execute(rt);
        exec.execute(rt1);
        exec.shutdown();
    }
}
