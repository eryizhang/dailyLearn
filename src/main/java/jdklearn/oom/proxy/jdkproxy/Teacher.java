package jdklearn.oom.proxy.jdkproxy;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/25 10:21
 * @Description:
 */
public class Teacher  implements People{

    @Override
    public String work() {
        System.out.println("老师教书育人...");
        return "教书";
    }

    @Override
    public String time() {
        System.out.println("Teacher time()");
        return "Teacher time()";
    }

    @Override
    public People work(String workName) {
        System.out.println("Teacher work(String workName)");
        return this;
    }
}
