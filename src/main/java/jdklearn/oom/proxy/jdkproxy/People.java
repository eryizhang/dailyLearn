package jdklearn.oom.proxy.jdkproxy;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/25 10:20
 * @Description:
 */
public interface People {
    String work();
    String time();
    People work(String workName);
}
