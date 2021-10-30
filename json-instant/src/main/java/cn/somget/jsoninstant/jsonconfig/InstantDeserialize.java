package cn.somget.jsoninstant.jsonconfig;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import io.vavr.control.Try;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.List;

/**
 * 把xxx.xxx格式的格式序列化成Instant
 * 如果要使用这个自定义反序列化. 请在要使用的字段上打@JSONField(deserializeUsing = InstantDeserialize.class)
 * @author oreoft
 * @date 2021-10-30 23:10
 */
public class InstantDeserialize implements ObjectDeserializer {

  @Override
  @SuppressWarnings("unchecked")
  public Instant deserialze(DefaultJSONParser parser, Type type, Object o) {
    // 参数在parser里面, o是参数名字(虽然用object接收, 其实是字符串)
    Object value = parser.parse(o);
    // 通过'.'分割, 然后拿到list
    List<String> split = StrUtil.split(Convert.toStr(value), '.');
    // 把前部分变成秒, 后部分变成纳秒, 然后生成Instant返回. 如果发生异常 返回一个null
    return Try.of(() -> Instant.ofEpochSecond(Convert.toInt(split.get(0)), Convert.toInt(split.get(1)))).getOrNull();
  }

  @Override
  public int getFastMatchToken() {
    return 0;
  }
}
