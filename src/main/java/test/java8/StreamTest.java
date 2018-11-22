package test.java8;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/10 10:08
 * @version: 1.0
 */
public class StreamTest {
  public static void main(String[] args) throws Exception {
    String[] ss = {"a", "b", "c", "d", "e", "f", "g", "e", "f", "f", "b", "d", "c"};
    List<String> allArtists = Arrays.asList(ss);
    StreamTest.java8Stream(allArtists);
    StreamTest.java8Collect(allArtists);
    StreamTest.java8limit(allArtists);
    StreamTest.java8skip(allArtists);
    StreamTest.java8map();
    StreamTest.java8flatmap();
    StreamTest.java8minmax();
    StreamTest.java8reduce();
    StreamTest.java8Optional();
  }

  private static void java8Optional() {
    System.out.println("java8Optional");
    String s = null;
    Optional.ofNullable(s)
        .ifPresent(
            new Consumer<String>() {
              @Override
              public void accept(String s) {
                System.out.println(s);
              }
            });
  }

  private static void java8reduce() {
    System.out.println("java8reduce");

    String[] ss = {"aZ", "badfaZ", "dasdfaasdf", "dsdf"};
    List<String> allArtists = Arrays.asList(ss);
    String sa = allArtists.stream().reduce((s, s2) -> s + s2) /* .reduce(
                new BinaryOperator<String>() {
                  @Override
                  public String apply(String s, String s2) {
                    return s + s2;
                  }
                })*/.get();
    System.out.println(sa);
    sa =
        allArtists
            .stream()
            .reduce(
                "开始reduce",
                (s1, s2) -> {
                  return "**" + s1 + "**" + s2;
                });
    /*            .reduce(
    "***",
    new BinaryOperator<String>() {
      @Override
      public String apply(String s, String s2) {
        return s + s2;
      }
    })*/
    System.out.println(sa);
    System.out.println();
    Integer isa =
        allArtists
            .stream()
            .reduce(
                0,
                new BiFunction<Integer, String, Integer>() {
                  @Override
                  public Integer apply(Integer u, String s) {
                    System.out.println("s" + s + "****u" + u);
                    return u + s.length();
                  }
                },
                new BinaryOperator<Integer>() {
                  @Override
                  public Integer apply(Integer u, Integer u2) {
                    System.out.println("u" + u + "###u2" + u2);
                    return u + u2;
                  }
                });
    System.out.println("isa" + isa);
    /*            .reduce(
    "***",
    new BinaryOperator<String>() {
      @Override
      public String apply(String s, String s2) {
        return s + s2;
      }
    })*/

    System.out.println("BinaryOperator测试");
    String s =
        allArtists
            .parallelStream()
            .reduce(
                "0",
                new BiFunction<String, String, String>() {
                  @Override
                  public String apply(String u, String s) {
                    System.out.println("s" + s + "****u" + u);
                    return u + s.length();
                  }
                },
                (a, b) -> a + b
                /* new BinaryOperator<String>() {
                  @Override
                  public String apply(String u, String u2) {
                    System.out.println("u" + u + "###u2" + u2);
                    return u + u2;
                  }
                }*/ );

    System.out.println("s" + s);
  }

  private static void java8minmax() {
    String[] ss = {"aZ", "badfaZ", "dasdfaasdf", "dsdf"};
    List<String> allArtists = Arrays.asList(ss);
    System.out.println("java8minmax");
    String min =
        allArtists
            .stream()
            .min(
                Comparator.comparing(
                    new Function<String, Integer>() {
                      @Override
                      public Integer apply(String s) {
                        Integer l = s.length();
                        return l;
                      }
                    }))
            // .min(Comparator.comparing(s -> s.length()))
            /*.min(
            new Comparator<String>() {
              @Override
              public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                  return 0;
                }
                if (o1.length() > o2.length()) {
                  return 1;
                }
                return -1;
              }
            })*/
            .get();
    System.out.println(min);
  }

  private static void java8flatmap() {
    System.out.println("java8flatmap");

    String[] ss = {"aZ", "bZ"};
    List<String> allArtists = Arrays.asList(ss);
    List<char[]> sa =
        allArtists
            .stream()
            .flatMap(
                s -> {
                  char[] sar = s.toCharArray();
                  return Arrays.asList(sar).stream();
                })
            .collect(Collectors.toList());
    sa.stream().forEach(s -> System.out.println(s));
  }
  /**
   * @Description:
   *
   * <p>map只需要返回新的list，方法中会将它包装成stream
   *
   * <p>flat需要自己生成list，包装成stream返回
   *
   * @param: []
   * @return: void
   * @author: zhangyahui @Date: 2018/11/14
   */
  private static void java8map() {
    System.out.println("java8map");
    String[] ss = {"aZ", "bZ"};
    List<String> allArtists = Arrays.asList(ss);
    List<char[]> dd =
        allArtists
            .stream()
            .map(
                new Function<String, char[]>() {
                  @Override
                  public char[] apply(String s) {
                    return s.toCharArray();
                  }
                })
            .collect(Collectors.toList());
    dd.stream().forEach(chars -> System.out.println(chars.length + "**" + chars));

    System.out.println("***********换个写法*****************");
    dd = allArtists.stream().map(artist -> artist.toCharArray()).collect(Collectors.toList());
    dd.stream().forEach(chars -> System.out.println(chars.length + "**" + chars));
    allArtists
        .stream()
        .map(artist -> artist.toCharArray())
        .collect(
            Collectors.averagingInt(
                new ToIntFunction() {
                  @Override
                  public int applyAsInt(Object value) {
                    return 0;
                  }
                }));
  }

  private static void java8skip(List<String> allArtists) {
    System.out.println("java8skip");
    long count = allArtists.stream().filter(s -> s.equals("d")).skip(0).count();
    System.out.println(count);
    count = allArtists.stream().skip(5).filter(s -> s.equals("d")).count();
    System.out.println(count);
  }

  /**
   * @Description:
   *
   * <p>1.filter之后的流只剩下filter里符合要求的，
   *
   * <p>2.limit（N）之后的流是里的元素只有N个
   *
   * <p>3.count是及早求值，返回的是流的个数
   *
   * @param: [allArtists]
   * @return: void
   * @author: zhangyahui @Date: 2018/11/14
   */
  private static void java8limit(List<String> allArtists) {
    System.out.println("java8limit");
    long count = allArtists.stream().filter(s -> s.equals("d")).limit(1).count();
    System.out.println(count);
    count = allArtists.stream().limit(6).filter(s -> s.equals("d")).count();
    System.out.println(count);
    allArtists.stream().limit(6).forEach(s -> System.out.print(s));
    System.out.println();
    allArtists.stream().filter(s -> s.equals("d")).forEach(s -> System.out.print(s));
    System.out.println();
  }

  private static void java8Collect(List<String> allArtists) {
    System.out.println("java8Collect");
    Set<String> sets = allArtists.stream().collect(Collectors.toSet());
    sets.forEach(s -> System.out.print(s));
  }

  private static void java8Stream(List<String> allArtists) {
    long count =
        allArtists
            .stream()
            .filter(
                new Predicate<String>() {
                  @Override
                  public boolean test(String s) {
                    if (s.equals("d")) {
                      return true;
                    }
                    return false;
                  }
                })
            .count();
    System.out.println(count);

    count = allArtists.stream().filter(s -> s.equals("d")).count();
    System.out.println(count);

    count =
        allArtists
            .stream()
            .filter(
                s -> {
                  if (s.equals("d")) {
                    return true;
                  }
                  return false;
                })
            .count();
    System.out.println(count);

    allArtists
        .stream()
        .forEach(
            s -> {
              System.out.print(s);
            });
  }
}
