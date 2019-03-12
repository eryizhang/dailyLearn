package test.synchronize_wait_notify_lock.locktest.readwrite;

import java.util.Random;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/11 16:56
 * @Description:
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        RWLCache cache = new RWLCache();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            boolean flag = random.nextBoolean();
            Thread thread = new Thread(generateTask(flag, cache), generateThreadName(flag, i));
            thread.start();
        }
    }

    private static Runnable generateTask(boolean flag, RWLCache cache) {
        Runnable putTask = () -> cache.write();
        Runnable getTask = () -> cache.read();
        return flag ? putTask : getTask;
    }

    private static String generateThreadName(boolean flag, int index) {
        return flag ? "write-" + index : "read-" + index;
    }
}
