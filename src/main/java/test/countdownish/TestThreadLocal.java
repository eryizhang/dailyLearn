package test.countdownish;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/29 16:48
 * @Description:
 */
public class TestThreadLocal {
    private static final ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    private  static final AtomicInteger count=new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new MyThread(i)).start();
        }
    }

    static class MyThread implements Runnable {
        private int index;

        public MyThread(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("线程" + index + "的初始value:" + value.get()+"初始count"+count.get());
            for (int i = 0; i < 10; i++) {
                value.set(value.get() + i);
                count.incrementAndGet();
            }
            System.out.println("线程" + index + "的累加value:" + value.get()+"累加count"+count.get());
        }
    }
}

