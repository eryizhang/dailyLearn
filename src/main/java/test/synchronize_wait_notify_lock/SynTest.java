package test.synchronize_wait_notify_lock;

import java.util.ArrayList;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/31 16:34
 * @Description:
 */
public class SynTest {

    public static void main(String[] args)  {
        final InsertData insertData = new InsertData();

        new Thread() {
            public void run() {
                insertData.insert(Thread.currentThread());
            };
        }.start();


        new Thread() {
            public void run() {
                insertData.insert(Thread.currentThread());
            };
        }.start();
    }
}

class InsertData {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public void insert(Thread thread){
        for(int i=0;i<5;i++){
            System.out.println(thread.getName()+"在插入数据"+i);
            arrayList.add(i);
        }
    }
}
