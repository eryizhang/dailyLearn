package jdklearn.oom.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/25 11:32
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        testWork();
    }

    static void testWork()
    {
        //要代理的真实对象
        People people = new Teacher();
        //代理对象的调用处理程序，我们将要代理的真实对象传入代理对象的调用处理的构造函数中，最终代理对象的调用处理程序会调用真实对象的方法
        InvocationHandler handler = new WorkHandler(people);
        /**
         * 通过Proxy类的newProxyInstance方法创建代理对象，我们来看下方法中的参数
         * 第一个参数：people.getClass().getClassLoader()，使用handler对象的classloader对象来加载我们的代理对象
         * 第二个参数：people.getClass().getInterfaces()，这里为代理类提供的接口是真实对象实现的接口，这样代理对象就能像真实对象一样调用接口中的所有方法
         * 第三个参数：handler，我们将代理对象关联到上面的InvocationHandler对象上
         */
        People proxy = (People) Proxy.newProxyInstance(handler.getClass().getClassLoader(), people.getClass().getInterfaces(), handler);
        //System.out.println(proxy.toString());
        System.out.println(proxy.work());
        InvocationHandler handler1=Proxy.getInvocationHandler(proxy);
        System.out.println(handler1.getClass());
        Class p=Proxy.getProxyClass(handler.getClass().getClassLoader(),proxy.getClass());
        System.out.println(p.getClass());
    }

    static void testStudentWork()
    {
        People people = new Student();
        InvocationHandler handler = new WorkStudentHandler(people);

        People proxy = (People)Proxy.newProxyInstance(people.getClass().getClassLoader(), people.getClass().getInterfaces(), handler);
        People p = proxy.work("写代码").work("开会").work("上课");

        System.out.println("打印返回的对象");
        System.out.println(p.getClass());

        String time = proxy.time();
        System.out.println(time);

    }



}
