package test.java8;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/29 16:55
 * @version: 1.0
 */
import java.lang.annotation.*;

public class RepeatingAnnotations {
  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Filters {
    Filter[] value();
  }

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Repeatable(Filters.class)
  public @interface Filter {
    // String value();
    int value();
  }

  /*@Filter("filter1")
  @Filter("filter2")*/
  @Filter(1)
  @Filter(2)
  public interface Filterable {}

  public static void main(String[] args) {
    for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
      System.out.println(filter.value());
    }
  }
}
