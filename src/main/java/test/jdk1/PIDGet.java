package test.jdk1;

import java.io.InputStream;

/**
 * @Auther: zhangyahui
 * @Date: 2019/2/1 17:21
 * @Description:
 */
public class PIDGet {

    public static void main(String[] args) throws Exception {
        //1.9特性，查询pid
        // System.out.println("Your pid is " + Process.getCurrentPid());

        Process proc = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo $PPID"});

        if (proc.waitFor() == 0) {
            InputStream in = proc.getInputStream();
            int available = in.available();
            byte[] outputBytes = new byte[available];

            in.read(outputBytes);
            String pid = new String(outputBytes);

            System.out.println("Your pid is " + pid);
        }
    }
}
