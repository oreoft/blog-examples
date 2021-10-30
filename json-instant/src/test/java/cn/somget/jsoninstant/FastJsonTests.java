package cn.somget.jsoninstant;

import cn.somget.jsoninstant.entity.TimeDTO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.jupiter.api.Test;

/**
 * 序列号instant遇到的问题总结启动类
 * 这里主要是测试各个JSON序列化工具instant的兼容性情况
 * @author oreoft
 */
class FastJsonTests extends MixTests {

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

}
