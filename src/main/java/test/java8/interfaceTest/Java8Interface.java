package test.java8.interfaceTest;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/26 14:02
 * @version: 1.0
 */
public interface Java8Interface {
  void toImple();

  /**
   * @Description:
   *
   * <p>java8中接口可以定义静态方法
   *
   * @param: []
   * @return: void
   * @author: zhangyahui @Date: 2018/11/26
   */
  static void testFun() {
    System.out.println("Java8Interface testFun");
  }

  /**
   * @Description:
   *
   * <p>java8中接口可以定义成员方法的默认实现
   *
   * @param: []
   * @return: void
   * @author: zhangyahui @Date: 2018/11/26
   */
  default void hasDefault() {
    System.out.println("Java8Interface hasDefault");
  }
}
