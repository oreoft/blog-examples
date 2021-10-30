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
   * FastJSON, 序列化Instant
   * FastJSON, 反序列化(解析)Instant

   result:
   {
   "instant":"2021-10-30T08:00:03.210Z",
   "name":"fastJson测试"
   }
   TimeDTO(name=fastJson测试, instant=2021-10-30T08:00:03.210Z)
   */
  @Test
  void allFastJsonTest() {
    TimeDTO timeDTO = TimeDTO.builder().
        name("fastJson测试").
        instant(instant).build();
    // fastjson序列化(序列化好看一点, 然后打印出来)
    String json = JSON.toJSONString(timeDTO, SerializerFeature.PrettyFormat);
    System.out.println(json);

    // 然后在使用fastjson反序列化
    TimeDTO obj = JSON.parseObject(json, TimeDTO.class);
    System.out.println(obj);
  }


  /**
   * 不注册java8模块的jackson, 序列化Instant
   * 不注册java8模块的jackson, 反序列化(解析)Instant

   result:

   {
   "name" : "jackson不注册java8时间模块序列化",
   "instant" : {
   "epochSecond" : 1635582033,
   "nano" : 590000000
      }
   }
   com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
   Cannot construct instance of `java.time.Instant` (no Creators, like default constructor, exist)
   */
  @Test
  @SneakyThrows
  void allJacksonWithNonJava8Test() {
    TimeDTO timeDTO = TimeDTO.builder().
        name("jackson不注册java8时间模块序列化").
        instant(instant).build();

    // jackson序列化(序列化好看一点, 然后打印出来)
    String json = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(timeDTO);
    System.out.println(json);

    // 然后再使用jackson反序列化
    TimeDTO obj = objectMapper.readValue(json, TimeDTO.class);
    System.out.println(obj);
  }


  /**
   * 注册java8模块的jackson, 序列化Instant
   * 注册java8模块的jackson, 反序列化(解析)Instant

   result:
   {
   "name" : "jackson注册java8时间模块序列化",
   "instant" : 1635582344.024000000
   }
   TimeDTO(name=jackson注册java8时间模块序列化, instant=2021-10-30T08:25:44.024Z)
   */
  @Test
  @SneakyThrows
  void allJacksonWithJava8Test() {
    TimeDTO timeDTO = TimeDTO.builder().
        name("jackson注册java8时间模块序列化").
        instant(instant).build();

    // 给全局变量的objectMapper注册一下java8时间模块
    objectMapper.registerModule(new JavaTimeModule());
    // jackson序列化(序列化好看一点, 然后打印出来)
    String json = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(timeDTO);
    System.out.println(json);

    // 然后再使用jackson反序列化
    TimeDTO obj = objectMapper.readValue(json, TimeDTO.class);
    System.out.println(obj);
  }


  /**
   * 注册java8模块的jackson, 序列化Instant
   * 没有注册java8模块的jackson, 反序列化(解析)Instant

   result:
   {
   "name" : "jackson注册java8时间模块序列化, 然后用没有注册java8时间模块的jackson反序列化",
   "instant" : 1635582847.359000000
   }

   com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
   Cannot construct instance of `java.time.Instant` (no Creators, like default constructor, exist)
   */
  @Test
  @SneakyThrows
  void JacksonWithJava8ToJacksonWithNonJava8Test() {
    TimeDTO timeDTO = TimeDTO.builder().
        name("jackson注册java8时间模块序列化, 然后用没有注册java8时间模块的jackson反序列化").
        instant(instant).build();

    // 给全局变量的objectMapper注册一下java8时间模块
    objectMapper.registerModule(new JavaTimeModule());
    // jackson序列化(序列化好看一点, 然后打印出来)
    String json = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(timeDTO);
    System.out.println(json);

    // 然后再使用重新创建一个jackson反序列化(和这个是没有注册java8时间模块的)
    TimeDTO obj = new ObjectMapper().readValue(json, TimeDTO.class);
    System.out.println(obj);

  }

  /**
   * 没有注册java8模块的jackson, 序列化Instant
   * 注册java8模块的jackson, 反序列化(解析)Instant

   result:
   {
   "name" : "jackson注册java8时间模块序列化, 然后用没有注册java8时间模块的jackson反序列化",
   "instant" : {
   "epochSecond" : 1635583012,
   "nano" : 867000000
      }
   }

   java.lang.NoSuchMethodError:
   com.fasterxml.jackson.databind.DeserializationContext.extractScalarFromObject
   */
  @Test
  @SneakyThrows
  void JacksonNonWithJava8ToJacksonWithJava8Test() {
    TimeDTO timeDTO = TimeDTO.builder().
        name("jackson注册java8时间模块序列化, 然后用没有注册java8时间模块的jackson反序列化").
        instant(instant).build();

    // 用不注册java8时间模块的jackson序列化(序列化好看一点, 然后打印出来)
    String json = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(timeDTO);
    System.out.println(json);

    // 然后给全局变量的objectMapper注册一下java8时间模块
    objectMapper.registerModule(new JavaTimeModule());
    // 然后再jackson反序列化(现在已经注册java8时间模块的)
    TimeDTO obj = objectMapper.readValue(json, TimeDTO.class);
    System.out.println(obj);
  }

}
