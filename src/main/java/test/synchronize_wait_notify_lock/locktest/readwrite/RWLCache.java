package test.synchronize_wait_notify_lock.locktest.readwrite;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/11 16:56
 * @Description:
 */
public class RWLCache {
    private MyReentrantReadWriteLock lock = new MyReentrantReadWriteLock();

    public void write() {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquires write lock, queued threads is " + getThreadNames(lock.getQueuedThreads()));
            sleep(1);
        } finally {
            System.out.println(Thread.currentThread().getName() + " releases write lock");
            lock.writeLock().unlock();
        }
    }

    public void read() {
        lock.readLock().lock();
        try {

            System.out.println(Thread.currentThread().getName() + " acquires read lock, queued threads is " + getThreadNames(lock.getQueuedThreads()));
            sleep(10);
        } finally {
            System.out.println(Thread.currentThread().getName() + " releases read lock");
            lock.readLock().unlock();
        }
    }

    private static final class MyReentrantReadWriteLock extends ReentrantReadWriteLock {
        @Override
        protected Collection<Thread> getQueuedThreads() {
            List<Thread> threads = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(threads);
            return threads;
        }
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getThreadNames(Collection<Thread> threads) {
        StringBuilder result = new StringBuilder("[");
        Iterator<Thread> iterator = threads.iterator();
        if (!threads.isEmpty()) {
            Thread thread = iterator.next();
            result.append(thread.getName());
        }

        while (iterator.hasNext()) {
            Thread thread = iterator.next();
            result.append(",").append(thread.getName());
        }

        return result.append("]").toString();
    }
}
