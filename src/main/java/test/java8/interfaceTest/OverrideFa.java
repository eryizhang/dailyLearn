package test.java8.interfaceTest;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/26 14:03
 * @version: 1.0
 */
public class OverrideFa implements Java8Interface {
  @Override
  public void toImple() {}

  @Override
  public void hasDefault() {
    System.out.println("OverrideFa  hasDefault");
  }
}
