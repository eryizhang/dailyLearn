package test.volatileTest;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/6 14:58
 * @version: 1.0
 */
public class VolatileTest_synchronized {

    private int inc = 0;

    private synchronized void increase() {
        inc++;
    }

    public static void main(String[] args) {
        VolatileTest_synchronized test = new VolatileTest_synchronized();
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
