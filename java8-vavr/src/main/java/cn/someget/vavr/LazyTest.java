package cn.someget.vavr;

import io.vavr.Lazy;
import org.junit.Test;

/**
 * Lazy相关演示
 *
 * @author oreoft
 * @date 2021-11-21 12:11
 */
public class LazyTest {

    @Test
    public void lazyTest() {
        // 生成一个随机数给到lazy容器
        Lazy<Double> lazy = Lazy.of(Math::random);

        // 判断是否已经获取过了
        System.out.println(lazy.isEvaluated());

        // 正式获取lazy的值
        System.out.println(lazy.get());

        // 看看现在是否计算了
        System.out.println(lazy.isEvaluated());

        // 再次获取lazy的值
        System.out.println(lazy.get());
    }
}
