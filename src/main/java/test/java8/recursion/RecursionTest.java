package test.java8.recursion;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/7 17:45
 * @Description:
 */
public class RecursionTest
{

    /**
     * 阶乘计算 -- 尾递归解决
     *
     * @param factorial 上一轮递归保存的值
     * @param number    当前阶乘需要计算的数值
     * @return number!
     */
    public static int factorialTailRecursionOld(final int factorial, final int number) {
        if (number == 1) {return factorial;}
        else{ return factorialTailRecursionOld(factorial + number, number - 1);}
    }


    public static void main(String[] args) {
        System.out.println(factorialTailRecursionOld(  1,   5));


        System.out.println(factorialTailRecursion(  1,   5).invoke());


        long now=System.currentTimeMillis();
        System.out.println(FactorialStatic.fibonacciMemo(100)+"   "+(System.currentTimeMillis()-now));
        now=System.currentTimeMillis();
        System.out.println(fibonacciIter(100)+"   "+(System.currentTimeMillis()-now));
    }

    /**
     * 阶乘计算 -- 使用尾递归接口完成
     * @param factorial 当前递归栈的结果值
     * @param number 下一个递归需要计算的值
     * @return 尾递归接口,调用invoke启动及早求值获得结果
     */
    public static TailRecursion<Integer> factorialTailRecursion(final int factorial, final int number) {
        if (number == 1)
        { return TailInvoke.done(factorial);}
        else
        {   return TailInvoke.call(() -> factorialTailRecursion(factorial + number, number - 1));}
    }


    /**
     * 斐波那契的迭代解法,自底向上求解
     * @param n 第n个斐波那契数
     * @return 第n个斐波那契数
     */
    public static long fibonacciIter(int n) {
        long prev = 1;
        long next = 1;

        long accumulate = 0;
        for (int i = 2; i <= n; i++) {
            accumulate = prev + next;
            prev = next;
            next = accumulate;
        }
        return accumulate;
    }
}
