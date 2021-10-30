package cn.somget.jsoninstant.entity;

import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 测试的包含Instant的实体类
 *
 * @author zyf
 * @date 2021-10-30 15:10
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class TimeDTO {

  /**
   * 充数字段
   */
  private String name;

  /**
   * 重点测试字段
   * todo 这个注解注释, 给你测xxx.xxx解析的时候使用
   */
//  @JSONField(deserializeUsing = InstantDeserialize.class)
  private Instant instant;

}
