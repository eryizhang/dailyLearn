package test.conditionTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/6 19:47
 * @version: 1.0
 */
public class ConditionTest {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = ConditionTest.lock.newCondition();

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                ConditionTest.lock.lock();//请求锁
                try {
                    System.out.println(Thread.currentThread().getName() + "==》进入等待");
                    ConditionTest.condition.await();//设置当前线程进入等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    ConditionTest.condition.signal();
                    ConditionTest.lock.unlock();//释放锁
                }
                System.out.println(Thread.currentThread().getName() + "==》继续执行");

            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                ConditionTest.lock.lock();//请求锁
                try {
                    System.out.println(Thread.currentThread().getName() + "==》进入等待");
                    ConditionTest.condition.await();//设置当前线程进入等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    ConditionTest.condition.signal();
                    ConditionTest.lock.unlock();//释放锁
                }
                System.out.println(Thread.currentThread().getName() + "==》继续执行");

            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                ConditionTest.lock.lock();//请求锁
                try {
                    System.out.println(Thread.currentThread().getName() + "=》进入");
                    Thread.sleep(2000);//休息2秒
                    ConditionTest.condition.signal();//随机唤醒等待队列中的一个线程
                    System.out.println(Thread.currentThread().getName() + "休息结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    ConditionTest.lock.unlock();//释放锁
                }
            }
        }.start();
    }
}
