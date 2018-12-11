package test.java8;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/29 17:03
 * @version: 1.0
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

public class Annotations {
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
  public @interface NonEmpty {
    // String value();
  }

  public static class Holder<@NonEmpty T> extends @NonEmpty Object {
    public void method() throws @NonEmpty Exception {}
  }

  public static void main(String[] args) {
    Holder<String> holder = new @NonEmpty Holder<>();
    @NonEmpty Collection<@NonEmpty String> strings = new ArrayList<>();
  }
}
