package test.javabases.fanxing;

import lombok.Data;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/29 11:42
 * @version: 1.0
 */
@Data
public class FanxingLei<T, M, N> {
  List<T> fanJihe;

  M m;

  N n;

  public /*static*/ <V> V fanFun(M m) {
    return null;
  }

  /**
   * 泛型方法的基本介绍
   *
   * @param tClass 传入的泛型实参
   * @return T 返回值为T类型 说明： 1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
   *     2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。 3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
   *     4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
   */
  private static <TA> TA genericMethod(Class<TA> tClass)
      throws IllegalAccessException, InstantiationException {
    TA instance = tClass.newInstance();
    return instance;
  }

  public static <K, WW> void staticFun(WW w) {}

  public static void main(String[] args) throws IllegalAccessException, InstantiationException {

    FanxingLei<Ta, Ma, Na> fxl = new FanxingLei<>();
    fxl.setM(new Ma());
    fxl.setN(new Na());
    List<Ta> taList = new ArrayList<>();
    taList.add(new Ta(1));
    fxl.setFanJihe(taList);
    Ta tta = FanxingLei.genericMethod(Ta.class);
    System.out.println(tta.ta);
  }
}

class Ta {
  @Min(0)
  int ta = 1;

  Ta(int t) {
    ta = t;
  }
}

class Ma {
  public final int ma = 1;
}

class Na {
  public final int na = 1;
}
