package jdklearn.oom.proxy.jdkproxy;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/25 13:30
 * @Description:
 */
public class Student  implements People{

    @Override
    public People work(String workName) {
        System.out.println("工作内容是"+workName);
        return this;
    }

    @Override
    public String work() {
        System.out.println("Student work()");
        return "Student work()";
    }

    @Override
    public String time() {
        return "2018-06-12";
    }

}
