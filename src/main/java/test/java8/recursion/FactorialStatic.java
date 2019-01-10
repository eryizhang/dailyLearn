package test.java8.recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/8 09:05
 * @Description:
 */
public class FactorialStatic {


    /**
     * 使用统一封装的备忘录模式 对外开放的方法,在内部执行具体的斐波那契策略 {@link #fibonacciCallMemo(Function, Integer)}
     * @param n 第n个斐波那契数
     * @return 第n个斐波那契数
     */
    public static long fibonacciMemo(int n) {
        return callMemo(FactorialStatic::fibonacciCallMemo, n);
    }

    /**
     * 私有方法,服务于{@link #fibonacciMemo(int)} ,内部实现为斐波那契算法策略
     * @param fib 斐波那契算法策略自身,用于递归调用, 在{@link #callMemo(BiFunction, Object)} 中通过传入this来实例这个策略
     * @param n 第n个斐波那契数
     * @return 第n个斐波那契数
     */
    private static long fibonacciCallMemo(Function<Integer,Long> fib,Integer n){
        if (n == 0 || n == 1) {
            return 1;}
        return fib.apply(n -1 ) + fib.apply(n-2);
    }

    /**
     * 备忘录模式 函数封装
     * @param function 递归策略算法
     * @param input 输入值
     * @param <T> 输出值类型
     * @param <R> 返回值类型
     * @return 将输入值输入递归策略算法，计算出的最终结果
     */
    public static <T, R> R callMemo(final BiFunction<Function<T, R>, T, R> function, final T input) {
        Function<T, R> memo = new Function<T, R>() {
            private final Map<T, R> cache = new HashMap<>(64);
            @Override
            public R apply(final T input) {
                return cache.computeIfAbsent(input, key -> function.apply(this, key));
            }
        };

        return memo.apply(input);
    }
}
