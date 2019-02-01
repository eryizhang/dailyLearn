package test.synchronize_wait_notify_lock.synchronizedp;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/31 15:41
 * @Description:
 */
public class SynchronizedTest {

    public static void main(String[] args) throws InterruptedException {

        STest sTest = new STest();

        STest sTest1 = new STest();


/*        new Thread() {
            public void run() {
                sTest.d("ta");
            }

            ;
        }.start();

        new Thread() {
            public void run() {
                sTest1.d("tb");
            }

            ;
        }.start();*/


        Sint sint = new Sint();
        sTest.sint = sint;
        sTest1.sint = sint;

        new Thread() {
            public void run() {
                sTest.f("ta");
            }

            ;
        }.start();

        new Thread() {
            public void run() {
                sTest1.f("tb1");
            }

            ;
        }.start();

    }
}

class Sint {
    private int i = 0;
    private int m = 0;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }
}

class STest {
    static Integer ia = 0;
    AtomicInteger ai = new AtomicInteger(0);
    static final int count = 10000;
    Sint sint = new Sint();


    public synchronized void a(String s) {
        /*synchronized(ia)*/
        {
            System.out.println("jinru  " + s);
            int i = 0;
            while (i < count) {
                ia++;
                i++;
            }
        }
        System.out.println("this is " + s + " " + ia + " " + System.currentTimeMillis());
    }

    public void b(String s) {
        synchronized (this) {
            System.out.println("jinru  " + s);
            int i = 0;
            while (i < count) {
                ia++;
                i++;
            }
            System.out.println("this is " + s + " " + ia + " " + System.currentTimeMillis());
        }
    }

    /*
     * c方法没有预期的效果，结果很乱，应该是包装类本身的问题
     * 最后的结果也大概率不是20000
     */
    public void c(String s) {
        synchronized (ia) {
            System.out.println("jinru  " + s);
            int i = 0;
            while (i < count) {
                ia++;
                i++;
            }
            System.out.println("this is " + s + " " + ia + " " + System.currentTimeMillis());
        }
    }

    /*
     * d方法的输出和预期一致，锁住了sint对象的操作
     */
    public void d(String s) {
        synchronized (sint) {
            System.out.println("jinru  " + s);
            int i = 0;
            while (i < count) {
                ia++;
                sint.setI(ia);
                i++;
            }
            System.out.println("this is " + s + " " + ia + " " + System.currentTimeMillis());
        }
    }

    /*
     * d方法的输出和预期一致，锁住了sint对象的操作
     */
    public void d1(String s) {
        synchronized (sint) {
            System.out.println("jinru  " + s);
            int i = 0;
            while (i < count) {
                ia++;
                sint.setM(ia);
                i++;
            }
            System.out.println("this is " + s + " " + ia + " " + System.currentTimeMillis());
        }
    }

    public void f(String s) {
        synchronized (Sint.class) {
            System.out.println("jinru  " + s);
            int i = 0;
            while (i < count) {
                ia++;
                sint.setM(ia);
                i++;
            }
            System.out.println("this is " + s + " " + ia + " " + System.currentTimeMillis());
        }
    }

    /*
     * 使用了原子int，先结束的线程值大概率不是10000而是一个接近20000的数字
     * 但是最后结束的线程输出的值必然是20000,原子int自身维护了资源的访问
     */
    public void e(String s) {
        System.out.println("jinru  " + s);
        int i = 0;
        while (i < count) {
            ai.incrementAndGet();
            i++;
        }
        System.out.println("this is " + s + " " + ai.get() + " " + System.currentTimeMillis());
    }
}