package cn.someget.vavr;

import java.util.Collections;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

/**
 * 集合相关演示
 *
 * @author oreoft
 * @date 2021-11-21 12:11
 */
public class CollectionsTest {


    @Test
    public void garbageTest() {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        System.out.println(list);
        List<Integer> unmodifiableList = Collections.unmodifiableList(list);
        System.out.println(unmodifiableList);

        list.add(1);
        System.out.println(list);
        System.out.println(unmodifiableList);

        unmodifiableList.add(1);
    }

    @Test
    public void listTest() {
        io.vavr.collection.List<Integer> list = io.vavr.collection.List.of(1, 2);
        // 增加一个元素
        io.vavr.collection.List<Integer> appendList = list.append(3);
        // 丢掉一个元素
        io.vavr.collection.List<Integer> dropList = list.drop(1);

        // 变成java的可变list
        List<Integer> javaList = list.asJava();
    }

}
