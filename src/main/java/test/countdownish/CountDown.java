package test.countdownish;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/10/29 10:15
 * @version: 1.0
 */
public class CountDown {
    public static void main(String[] args) {
        ExecutorService e= Executors.newFixedThreadPool(2);
        CountDownLatch c=new CountDownLatch(1);
        e.execute(new Runnable()
        {

            @Override
            public void run() {
                try {
                    System.out.println("11");
                    long l=System.currentTimeMillis();
                    if(c.await(2, TimeUnit.SECONDS))
                    {
                        System.out.println("及时");
                    }
                    else
                    {
                        System.out.println("超时");
                    }
                    //c.await();
                    System.out.println(c.getCount());

                    System.out.println("44"+(System.currentTimeMillis()-l));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    System.out.println("55");
                    e.printStackTrace();
                }

                System.out.println("1结束");
            }

        });
        e.execute(new Runnable()
        {

            @Override
            public void run() {
                try {
                    System.out.println("22");
                    long l=System.currentTimeMillis();
                    Thread.sleep(2500);
                    c.countDown();
                    System.out.println("33"+(System.currentTimeMillis()-l));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("2结束");
            }

        });

        // 向学生传达“问题解答完毕后请举手示意！”
        e.shutdown();

        // 向学生传达“XX分之内解答不完的问题全部带回去作为课后作业！”后老师等待学生答题
        // (所有的任务都结束的时候，返回TRUE)
		        /*if(!e.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)){
		            // 超时的时候向线程池中所有的线程发出中断(interrupted)。
		            e.shutdownNow();
		        } */
        //} catch (InterruptedException e1) {
        // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
		        /*System.out.println("awaitTermination interrupted: " + e);
		        //e.shutdownNow();
		    }  */


    }
}
