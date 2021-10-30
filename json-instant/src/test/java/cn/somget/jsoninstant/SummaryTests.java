package cn.somget.jsoninstant;

import cn.hutool.json.JSONUtil;
import cn.somget.jsoninstant.entity.TimeDTO;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * 大致情况汇总
 *
 * @author zyf
 * @date 2021-10-31 00:10
 */
public class SummaryTests extends MixTests{

  /**
   {"instant":"2021-10-30T16:59:29.896Z","name":"FastJson序列化以后的结果"}
   {"name":"Jackson没注册时间模块序列化以后的结果","instant":{"epochSecond":1635613169,"nano":896000000}}
   {"name":"Jackson注册了时间模块序列化以后的结果","instant":1635613169.896000000}
   {"instant":1635613169896,"name":"HuTools工具序列化以后的结果"}
   */
  @Test
  @SneakyThrows
  void sumTest() {
    TimeDTO timeDTO = TimeDTO.builder().instant(instant).build();

    timeDTO.setName("FastJson序列化以后的结果");
    String str1 = JSON.toJSONString(timeDTO);
    timeDTO.setName("Jackson没注册时间模块序列化以后的结果");
    String str2 = objectMapper.writeValueAsString(timeDTO);
    timeDTO.setName("Jackson注册了时间模块序列化以后的结果");
    String str3 = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(timeDTO);
    timeDTO.setName("HuTools工具序列化以后的结果");
    String str4 = JSONUtil.toJsonStr(timeDTO);
    System.out.println(str1);
    System.out.println(str2);
    System.out.println(str3);
    System.out.println(str4);
  }

}
