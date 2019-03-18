package test.threadpool.schedule;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/12 14:50
 * @Description:
 */
public class AtFixedRate {

    public static void main(String[] args) {
        atFixed();
    }


    public static void atFixed() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        long time = System.currentTimeMillis();
        for( int i=0;i<6;i++) {
            final int j=i;
            Integer[] is = {1, 2};
            List<Integer> list = Arrays.asList(is);
            Iterator<Integer> it = list.iterator();
            service.scheduleWithFixedDelay(new Runnable() {

                int k=j;
                @Override
                public void run() {
                    if (it.hasNext()) {
                        System.out.println(k+"*****"+it.next() + "******" +this.hashCode()+"*****"+ (System.currentTimeMillis() - time));
                        try {
                            //Thread.sleep(1000L);
                            Thread.sleep(50L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else {
                        //service.shutdown();
                        System.out.println(k+"无动作" + this.hashCode()+"service"+service.hashCode());
                    }
                   // System.out.println(k+"无动作" + this.hashCode());
                }
            }, 0, 1000, TimeUnit.MILLISECONDS);
        }
    }


    public static void withFixed() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
        long time = System.currentTimeMillis();
        for( int i=0;i<10;i++) {
            final int j=i;
            Integer[] is = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            List<Integer> list = Arrays.asList(is);
            Iterator<Integer> it = list.iterator();
            service.scheduleWithFixedDelay(new Runnable() {

                int k=j;
                @Override
                public void run() {
                    if (it.hasNext()) {
                        System.out.println(k+"*****"+it.next() + "******" +this.hashCode()+"*****"+ (System.currentTimeMillis() - time));
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else {
                        service.shutdown();
                    }
                    // System.out.println(k+"无动作" + this.hashCode());
                }
            }, 100, 100, TimeUnit.MILLISECONDS);
        }
    }

}
