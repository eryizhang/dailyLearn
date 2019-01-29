package test.countdownish.semaphoreTest;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/29 13:37
 * @Description:
 */

import java.util.concurrent.Semaphore;


public class AdjustableSemaphore {

    private final ResizeableSemaphore semaphore = new ResizeableSemaphore();

    private int maxPermits = 0;

    public AdjustableSemaphore() {

    }

    synchronized void setMaxPermits(int newMax) {
        if (newMax < 1) {
            throw new IllegalArgumentException("Semaphore size must be at least 1,"
                    + " was " + newMax);
        }

        int delta = newMax - this.maxPermits;

        if (delta == 0) {
            return;
        } else if (delta > 0) {
            this.semaphore.release(delta);
        } else {
            delta *= -1;
            this.semaphore.reducePermits(delta);
        }

        this.maxPermits = newMax;

    }

    public int availablePermits() {
        return this.semaphore.availablePermits();
    }

    public void release() {
        this.semaphore.release();
    }

    public boolean tryAcquire() {
        return this.semaphore.tryAcquire();
    }


    private static final class ResizeableSemaphore extends Semaphore {

        ResizeableSemaphore() {
            super(0);
        }

        @Override
        protected void reducePermits(int reduction) {
            super.reducePermits(reduction);
        }

    }

    public static void main(String[] args) {
        AdjustableSemaphore semaphore = new AdjustableSemaphore();
        System.out.println("==============5");
        semaphore.setMaxPermits(5);

        for (int i = 0; i < 20; i++) {
            semaphore.tryAcquire();
        }


        System.out.println(semaphore.availablePermits()); //5个信号量全被占用，所以当前可用的为0


        System.out.println("==============2");
        semaphore.setMaxPermits(2);

        System.out.println(semaphore.availablePermits()); //将信号量显式设置为2，与上一步合并结果(2-5)=-3，表示目前有5个被占用，信号量只有2，所以还有3个欠着待释放


        System.out.println("==============20");
        semaphore.setMaxPermits(20);
        System.out.println(semaphore.availablePermits());//将信号量显式设置为20，与上一步合并结果(20-2)+(-3)=15个，表示目前还有15个可用。

        System.out.println("==============3");
        semaphore.setMaxPermits(3);
        System.out.println(semaphore.availablePermits());//同上，(3-20)+15=-2

        System.out.println("==============1");
        semaphore.setMaxPermits(1);
        System.out.println(semaphore.availablePermits());//同上，(1-3)-2=-4

        System.out.println("==============10");
        semaphore.setMaxPermits(10);
        System.out.println(semaphore.availablePermits());//同上，(10-1)-4=5

        System.out.println("==============FINALLY");
        for (int i = 0; i < 7; i++) {
            semaphore.release();
        }
        System.out.println(semaphore.availablePermits());//释放了7个，所以7+5=12，虽然显式设置了信号量为10，但因多release()了两次，所以无意之中隐式增大了信号量。

    }

}