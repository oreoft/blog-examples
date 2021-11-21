package cn.someget.vavr;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.API.run;
import static io.vavr.Predicates.isIn;

import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

/**
 * 匹配模式相关演示代码
 *
 * @author oreoft
 * @date 2021-11-21 12:11
 */
public class PatternMatchingTest {

  @Test
  public void showTest() {
    int input = 2;
    String result = Match(input).of(
        Case($(1), "one"),
        Case($(2), "two"),
        Case($(3), "three"),
        Case($(), "?"));
    System.out.println(result);
  }

  @Test
  public void isInTest() {
    int input = 1;
    String result = Match(input).of(
        Case($(isIn(0, 1)), "zero or one"),
        Case($(2), "two"),
        Case($(3), "three"),
        Case($(), "?"));
    System.out.println(result);
  }

  @Test
  public void customTest() {
    int i = 5;
    List<Integer> container = Lists.newArrayList(1, 2, 3, 4);

    String result = Match(i).of(
        Case($(container::contains), "Even Single Digit"),
        Case($(), "Out of range"));
    System.out.println(result);
  }

  @Test
  public void sideEffectsTest() {
    int i = 4;
    Match(i).of(
        Case($(isIn(2, 4, 6, 8)), () -> run(() -> System.out.println("这是第一类"))),
        Case($(isIn(1, 3, 5, 7, 9)), () -> run(() -> System.out.println("这是第二类"))),
        Case($(), o -> run(() -> System.out.println("没有找到"))));
  }


}
