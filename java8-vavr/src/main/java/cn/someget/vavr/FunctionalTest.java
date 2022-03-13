package cn.someget.vavr;

import cn.hutool.core.util.StrUtil;
import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.Function4;
import org.junit.Test;

/**
 * 函数式相关特性演示类
 *
 * @author oreoft
 * @date 2021-11-21 12:11
 */
public class FunctionalTest {

    @Test
    public void multiFunctionTest() {
        Function4<String, String, Boolean, Integer, String> func =
                (country, name, isMan, score) -> String.format("%s-%s-%s-%d", country, name, isMan ? "男" : "女", score);
        System.out.println(func.apply("中国", "小明", true, 10));
    }

    @Test
    public void andThenTest() {
        Function4<String, String, Boolean, Integer, String> func1 =
                (country, name, isMan, score) -> String.format("%s-%s-%s-%d", country, name, isMan ? "男" : "女", score);
        // andThen就是把fun1的返回值然后在进行接下来的func2操作
        Function4<String, String, Boolean, Integer, String> func2 = func1
                .andThen(str -> String.join(":", StrUtil.split(str, '-')));
        System.out.println(func2.apply("中国", "小明", true, 10));
    }

    @Test
    public void composeTest() {
        Function1<Long, String> func1 = num -> num + "%";
        // 先执行分compose里面的apply, 然后把结果放入func1的apply中
        Function1<Double, String> func2 = func1.compose((Double num) -> Math.round(num));
        System.out.println(func2.apply(12.25));
    }

    @Test
    public void partialApplyTest() {
        Function4<String, String, Boolean, Integer, String> func1 =
                (country, name, isMan, score) -> String.format("%s-%s-%s-%d", country, name, isMan ? "男" : "女", score);

        Function3<String, Boolean, Integer, String> func2 = func1.apply("中国");
        System.out.println(func2.apply("小明", true, 10));

        Function2<Boolean, Integer, String> func3 = func1.apply("中国", "小明");
        System.out.println(func3.apply(true, 10));

        Function1<Integer, String> func4 = func1.apply("中国", "小明", true);
        System.out.println(func4.apply(10));

        System.out.println(func1.apply("中国", "小明", true, 10));
    }

    @Test
    public void curriedTest() {
        Function4<String, String, Boolean, Integer, String> func1 =
                (country, name, isMan, score) -> String.format("%s-%s-%s-%d", country, name, isMan ? "男" : "女", score);

        Function1<String, Function1<Boolean, Function1<Integer, String>>> func2 = func1.curried().apply("中国");
        Function1<Boolean, Function1<Integer, String>> func3 = func2.apply("小明");
        Function1<Integer, String> func4 = func3.apply(true);
        String result = func4.apply(10);

        System.out.println(result);
    }

    @Test
    public void memorizeTest() {
        Function0<Double> hashCache = Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        System.out.println(randomValue1);
        double randomValue2 = hashCache.apply();
        System.out.println(randomValue2);

    }



}
