package cn.someget;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * 打乱一张扑克牌
 *
 * @author zyf
 * @date 2021-11-26 21:11
 */
public class ShuffleTest {

  /**
   * 花色的list
   */
  Set<String> colors = Sets.newHashSet("黑桃", "红心", "梅花", "方块");

  /**
   * 字面值的list. 使用双流合并, 因为很懒不愿意打2-10, 就用IntStream来生成了
   */
  Set<String> numbers = Stream.concat(IntStream.rangeClosed(2, 10).mapToObj(String::valueOf),
      Stream.of("A", "J", "K", "Q")).collect(Collectors.toSet());


  @Test
  public void collectionShuffleTest() {
    // 进行组合
    List<String> result = colors.stream()
        .flatMap(color -> numbers.stream().map(number -> color + number))
        .collect(Collectors.toList());

    // 进行打乱
    Collections.shuffle(result);
    // 在console输出(为了方便看, 转成pretty的json)
    System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
  }

}
