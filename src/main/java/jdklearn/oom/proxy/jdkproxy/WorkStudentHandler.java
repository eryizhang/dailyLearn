package jdklearn.oom.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/25 13:33
 * @Description:
 */
public class WorkStudentHandler implements InvocationHandler {

    private Object obj;

    public WorkStudentHandler(Object obj) {
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before 动态代理...");
        System.out.println(proxy.getClass().getName());
        System.out.println(this.obj.getClass().getName());
        if(method.getName().equals("work")) {
            method.invoke(this.obj, args);
            System.out.println("after 动态代理...");
            return proxy;
        } else {
            System.out.println("after 动态代理...");
            return method.invoke(this.obj, args);
        }
    }

}
