package test.synchronize_wait_notify_lock.locktest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/6 11:30
 * @Description:
 */
public class ReentrantLockTTryLock {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //注意这个地方
    public static void main(String[] args)  {
        final ReentrantLockTTryLock test = new ReentrantLockTTryLock();

        new Thread(){
            public void run() {
                try {
                    test.insert(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        new Thread(){
            public void run() {
                try {
                    test.insert(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();
    }

    public void insert(Thread thread) throws InterruptedException {
        //if(lock.tryLock()) {
        if(lock.tryLock(200, TimeUnit.MILLISECONDS)) {
            try {
                System.out.println(thread.getName()+"得到了锁");
                for(int i=0;i<5;i++) {
                    arrayList.add(i);
                }
                Thread.sleep(300L);
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                System.out.println(thread.getName()+"释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName()+"获取锁失败");
        }
    }
}
