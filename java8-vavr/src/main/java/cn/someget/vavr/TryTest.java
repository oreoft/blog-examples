package cn.someget.vavr;

import com.alibaba.fastjson.JSON;
import io.vavr.control.Try;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Try的演示测试类
 *
 * @author oreoft
 * @date 2021-11-21 12:11
 */
public class TryTest {

    @Test
    public void tryTest() {
        Try<Integer> result = Try.of(() -> 1 / 0);
        // 返回是否失败
        System.out.println(result.isSuccess());
        // 返回异常原因, 如果没有异常进行获取则会UOE
        System.out.println(result.getCause());
        // 获取返回值, 如果有异常则返回null
        System.out.println(result.getOrNull());
        // 获取返回值, 如果有异常则返回设置的默认值
        System.out.println(result.getOrElse(0));
    }

    @Test
    public void trySeniorTest() {
        List<Integer> list = Collections.emptyList();
        try {
            list = JSON.parseArray("json", Integer.class);
        }catch (Exception ignore){};
        System.out.println(list);
    }

}
