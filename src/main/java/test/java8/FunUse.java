package test.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/29 16:21
 * @version: 1.0
 */
public class FunUse {
  public static void main(String[] args) throws Exception {
    // 第一种方法引用的类型是构造器引用，语法是Class::new，或者更一般的形式：Class<T>::new。注意：这个构造器没有参数。
    Car car = Car.create(Car::new);

    List<Car> cars = new ArrayList<>();
    cars.add(car);

    // 第二种方法引用的类型是静态方法引用，语法是Class::static_method。注意：这个方法接受一个Car类型的参数。
    cars.forEach(Car::follow);

    // 第三种方法引用的类型是某个类的成员方法的引用，语法是Class::method，注意，这个方法没有定义入参：
    cars.forEach(Car::repair);

    // 第四种方法引用的类型是某个实例对象的成员方法的引用，语法是instance::method。注意：这个方法接受一个Car类型的参数：
    Car cart = Car.create(Car::new);
    cars.forEach(cart::collide);

    // 不能接受非本对象类型的参数
    // cars.forEach(cart::count);
  }
}

class Car {
  static Car create(Supplier<Car> supplier) {
    return supplier.get();
  }

  Car() {}

  void collide(Car car) {
    System.out.println("Collided " + car.toString());
  }

  static void follow(Car another) {
    System.out.println("Following the " + another.toString());
  }

  void repair() {
    System.out.println("Repaired " + toString());
  }

  void count(String s) {
    System.out.println("Repaired " + toString() + "   " + s);
  }
}
