package test.java8;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/20 11:13
 * @version: 1.0
 */
public class SortTest {

  private static final List<User> users =
      Lists.newArrayList(
          new User("jack", 17, 10),
          new User("jack", 18, 10),
          new User("jack", 19, 11),
          new User("apple", 25, 15),
          new User("tommy", 23, 8),
          new User("jessica", 15, 13));

  public static void main(String[] args) throws Exception {

    SortTest.traditionCombinationCompareInJava8();
  }

  public static void traditionCompareByName() {
    Collections.sort(
        SortTest.users,
        new Comparator<User>() {
          @Override
          public int compare(User o1, User o2) {
            return o1.getAge() - o2.getAge();
          }
        });

    for (User user : SortTest.users) {
      System.out.println(user);
    }
  }

  public static void traditionCompareByNameInJava8() {

    /// 第一种法法和它的实现类
    SortTest.users.sort((o1, o2) -> o1.getAge() - o2.getAge());
    SortTest.users.sort(
        new Comparator<User>() {
          @Override
          public int compare(User o1, User o2) {
            return o1.getAge() - o2.getAge();
          }
        });

    // 第二种方式和他的实现类
    SortTest.users.sort(comparingInt(User::getAge));

    SortTest.users.sort(
        comparingInt(
            new ToIntFunction<User>() {
              @Override
              public int applyAsInt(User value) {
                return value.getAge();
              }
            }));
  }

  // 传统反向排序
  public void traditionCompareByNameReverse() {
    users.sort((o1, o2) -> o1.getAge() - o2.getAge());
  }

  // java8反向排序
  public void traditionCompareByNameInJava8Reverse() {
    users.sort(comparingInt(User::getAge).reversed());
  }

  // 根据姓名，年龄，积分排序洗涤
  public static void traditionCombinationCompare() {
    Collections.sort(
        SortTest.users,
        new Comparator<User>() {
          @Override
          public int compare(User o1, User o2) {
            if (o1.getName().equals(o2.getName())) {
              if (o1.getAge().equals(o2.getAge())) {
                return o1.getAge() - o2.getAge();
              } else {
                return o1.getCredits() - o2.getCredits();
              }
            } else {
              return o1.getName().compareTo(o2.getName());
            }
          }
        });
  }

  private static void traditionCombinationCompareInJava8() {
    SortTest.users.sort(
        comparing(User::getName).thenComparing(User::getAge).thenComparing(User::getCredits));
    SortTest.users.sort(
        comparing(
                new Function<User, String>() {
                  @Override
                  public String apply(User user) {
                    return user.getName();
                  }
                })
            .thenComparing(
                user -> {
                  return user.getAge();
                })
            .thenComparing(User::getCredits));

    SortTest.users.forEach(user -> System.out.println(JSON.toJSONString(user)));
  }
}

@Data
class User {
  private final String name;
  private final Integer age;
  private final Integer credits;

  User(String name, Integer age, Integer credits) {
    this.name = name;
    this.age = age;
    this.credits = credits;
  }
}
