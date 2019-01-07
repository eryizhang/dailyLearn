package test.fanshe;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Auther: zhangyahui
 * @Date: 2018/12/26 09:55
 * @Description:
 */
public class Test {


    public static void main(String[] args) {
        try {
            Method method=SonClass.class.getSuperclass().getDeclaredMethod("father");
            method.invoke(new SonClass());
            System.out.println(method.getName());
            Method[] methods=SonClass.class.getMethods();

            Arrays.asList(methods).forEach(
                    method1 -> {
                        System.out.println(method1.getName());
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
