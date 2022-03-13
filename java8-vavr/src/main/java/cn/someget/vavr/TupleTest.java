package cn.someget.vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import org.junit.jupiter.api.Test;

/**
 * TupleTest的演示测试类
 * @author oreoft
 * @date 2021-11-21 12:11
 */
public class TupleTest {

    @Test
    public void tupleTest() {
        Tuple2<Integer, String> t2 = Tuple.of(1, "1");
        System.out.println(t2._1);
        System.out.println(t2._2);
    }

    @Test
    public void tupleSeniorTest() {
        Tuple2<Integer, String> t2 = Tuple.of(1, "1");
        System.out.println(t2);

        Tuple2<Integer, String> t2s = t2.update1(2);
        System.out.println(t2s);

        Tuple3<Integer, String, Double> t3 = t2.append(1.0);
        System.out.println(t3);
    }

}
