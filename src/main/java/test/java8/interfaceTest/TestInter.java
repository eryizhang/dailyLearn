package test.java8.interfaceTest;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/26 14:10
 * @version: 1.0
 */
public class TestInter {

  public static void main(String[] args) throws Exception {
    Java8Interface.testFun();
    new OverrideFa().hasDefault();
    new FromFa().hasDefault();
  }
}
