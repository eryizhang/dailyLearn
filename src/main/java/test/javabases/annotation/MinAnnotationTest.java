package test.javabases.annotation;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/29 17:40
 * @version: 1.0
 */
@Data
@Valid
public class MinAnnotationTest {

  @Min(2)
  public List<String> listStr;

  @Min(2)
  int i;

  @NotNull(message = "不能为null")
  String s;

  public MinAnnotationTest() {}

  public MinAnnotationTest(List<String> listStr, int i, String s) {
    this.listStr = listStr;
    this.i = i;
    this.s = s;
  }

  public static void main(String[] args) {

    @Valid MinAnnotationTest minAnnotationTest = new MinAnnotationTest();
    List<String> ls = new ArrayList<>();
    minAnnotationTest.setListStr(ls);
    minAnnotationTest.setI(1);
    minAnnotationTest.setS(null);

    @Valid MinAnnotationTest minAnnotationTest1 = new MinAnnotationTest(ls, 1, null);
  }
}
