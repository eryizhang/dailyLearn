package test.synchronize_wait_notify_lock.synchronizedp;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/31 19:37
 * @Description:
 */
public class SynchronizedDiff {

    public static void main(String[] args) throws InterruptedException {

        STest1 sTest = new STest1();

        int count = 5;

        new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    sTest.bbbb("2222");
                }
            }

            ;
        }.start();
        new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    sTest.aaaa("1111");
                }
            }

            ;
        }.start();


        new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    sTest.cccc("3333");
                }
            }

            ;
        }.start();

    }


}

class TMS {
    int i = 0;
}

class STest1 {
    static Integer ia = 0;
    TMS tms = new TMS();

    public /*synchronized*/ void aaaa(String s) {
        synchronized (TMS.class) {
            System.out.println("进入aaaa  " + s);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ia++;
            System.out.println("离开aaaa  " + s);
        }
    }

    public synchronized void bbbb(String s) {
        System.out.println("进入bbbb  " + s);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ia++;
        cccc(s);
        System.out.println("离开bbbb  " + s);
        /*System.out.println("funb调用funa");
        funa(s);*/
    }


    public void cccc(String s) {
        synchronized (tms) {
            System.out.println("进入cccc  " + s);
            /*try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            ia++;
            System.out.println("离开cccc  " + s);
        /*System.out.println("funb调用funa");
        funa(s);*/
        }
    }
}