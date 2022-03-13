package cn.someget.vavr;

import io.vavr.control.Option;
import java.util.Optional;
import org.junit.Test;

/**
 * Option的演示测试类
 *
 * @author oreoft
 * @date 2021-11-21 12:11
 */
public class OptionTest {
    @Test
    public void optionTest() {
        Integer num = null;
        Option<Integer> opt = Option.of(num);

        // 这个和optional一样
        Integer result = opt.getOrElse(0);
        System.out.println(result);

        // 如果是None则会返回ture
        boolean isEmpty = opt.isEmpty();
        System.out.println(isEmpty);

        // 变成java的optional
        Optional<Integer> optional = opt.toJavaOptional();
    }


}
