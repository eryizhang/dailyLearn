package test.synchronize_wait_notify;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/7 10:17
 * @version: 1.0
 */
public class MyThreadPrinter2 implements Runnable {

    private final String name;
    private final Object prev;
    private final Object self;
    private static int ount = 1;

    private MyThreadPrinter2(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            MyThreadPrinter2.ount++;

            synchronized (prev) {

                synchronized (self) {
                    System.out.print(name);
                    count--;
                    //self.notifyAll();
                    self.notify();

                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (MyThreadPrinter2.ount % 4 == 1) {
                    //System.out.println(MyThreadPrinter2.ount);
                }

            }
        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        Object d = new Object();
        Object e = new Object();
        Object f = new Object();
        MyThreadPrinter2 pa = new MyThreadPrinter2("A", f, a);
        MyThreadPrinter2 pb = new MyThreadPrinter2("B", a, b);
        MyThreadPrinter2 pc = new MyThreadPrinter2("C", b, c);
        MyThreadPrinter2 pd = new MyThreadPrinter2("D", c, d);
        MyThreadPrinter2 pe = new MyThreadPrinter2("E", d, e);
        MyThreadPrinter2 pf = new MyThreadPrinter2("F", e, f);
        new Thread(pa).start();
        new Thread(pb).start();
        new Thread(pc).start();
        new Thread(pd).start();
        //Thread.sleep(10);

        new Thread(pe).start();
        new Thread(pf).start();

    }
}
