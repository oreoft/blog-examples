package cn.somget.jsoninstant;

import cn.somget.jsoninstant.entity.TimeDTO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Instant;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * 序列号instant遇到的问题总结启动类
 * 这里主要是测试各个JSON序列化工具instant的兼容性情况
 * @author oreoft
 */
class MixTests {

  /**
   * 固定Instant实例
   */
  protected final Instant instant = Instant.now();

  /**
   * jackson的类全局使用
   */
  protected final ObjectMapper objectMapper = new ObjectMapper();


  /**
   * 使用没用注册java8时间模块的jackson进行序列化
   * 使用fastjson进行反序列化
   *
   result:
   {
   "name" : "jackson不注册java8时间模块序列化, 然后用FastJson反序列化",
   "instant" : {
   "epochSecond" : 1635610701,
   "nano" : 940000000
      }
   }
   TimeDTO(name=jackson不注册java8时间模块序列化, 然后用FastJson反序列化, instant=2021-10-30T16:18:21.940Z)
   */
  @Test
  @SneakyThrows
  void JackSonWithNonJava8ToFastJSON() {
    TimeDTO timeDTO = TimeDTO.builder().
        name("jackson不注册java8时间模块序列化, 然后用FastJson反序列化").
        instant(instant).build();

    // 用没用注册java8时间模块的jackson序列化
    String json = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(timeDTO);
    System.out.println(json);

    // 用fastjson直接反序列化
    TimeDTO obj = JSON.parseObject(json, TimeDTO.class);
    System.out.println(obj);
  }

  /**
   * 使用注册java8时间模块的jackson进行序列化
   * 使用fastjson进行反序列化
   *
   result:
   {
   "name" : "jackson注册java8时间模块序列化, 然后用FastJson反序列化",
   "instant" : 1635610909.898000000
   }

   todo 可以使用自定义反序列化器对其配置, 详情请释放实体类上注解
   Caused by: java.lang.UnsupportedOperationException
   at com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec.deserialze
   */
  @Test
  @SneakyThrows
  void JackSonWithJava8ToFastJSON() {
    TimeDTO timeDTO = TimeDTO.builder().
        name("jackson注册java8时间模块序列化, 然后用FastJson反序列化").
        instant(instant).build();

    // 用注册java8时间模块的jackson序列化
    objectMapper.registerModule(new JavaTimeModule());
    String json = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(timeDTO);
    System.out.println(json);

    // 用fastjson直接反序列化
    TimeDTO obj = JSON.parseObject(json, TimeDTO.class);
    System.out.println(obj);
  }


  /**
   * 使用fastjson进行序列化
   * 使用没用注册java8时间模块的jackson进行反序列化
   *
   result:
   {
   "instant":"2021-10-30T16:27:11.054Z",
   "name":"使用FastJson序列化, 然后使用没用注册java8时间模块的jackson反序列化"
   }

   com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
   Cannot construct instance of `java.time.Instant` (no Creators, like default constructor, exist)
   */
  @Test
  @SneakyThrows
  void FastJSONToJackSonWithNonJava8() {
    TimeDTO timeDTO = TimeDTO.builder().
        name("使用FastJson序列化, 然后使用没用注册java8时间模块的jackson反序列化").
        instant(instant).build();

    // 使用FastJson进行序列化
    String json = JSON.toJSONString(timeDTO, SerializerFeature.PrettyFormat);
    System.out.println(json);

    // 使用没用注册java8时间模块的jackson反序列化
    TimeDTO obj = objectMapper.readValue(json, TimeDTO.class);
    System.out.println(obj);
  }


  /**
   * 使用fastjson进行序列化
   * 使用注册java8时间模块的jackson进行反序列化
   *
   result:
   {
   "instant":"2021-10-30T16:30:07.833Z",
   "name":"使用FastJson序列化, 然后使用注册java8时间模块的jackson反序列化"
   }
   TimeDTO(name=使用FastJson序列化, 然后使用注册java8时间模块的jackson反序列化, instant=2021-10-30T16:30:07.833Z)
   */
  @Test
  @SneakyThrows
  void FastJSONToJackSonWithJava8() {
    TimeDTO timeDTO = TimeDTO.builder().
        name("使用FastJson序列化, 然后使用注册java8时间模块的jackson反序列化").
        instant(instant).build();

    // 使用FastJson进行序列化
    String json = JSON.toJSONString(timeDTO, SerializerFeature.PrettyFormat);
    System.out.println(json);

    // 使用注册java8时间模块的jackson反序列化
    objectMapper.registerModule(new JavaTimeModule());
    TimeDTO obj = objectMapper.readValue(json, TimeDTO.class);
    System.out.println(obj);
  }

}
