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
   */
  private Instant instant;

}
