package cn.someget.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 测试的老师BO
 *
 * @author zyf
 * @date 2021-10-31 15:10
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeacherBO extends BaseBO {

  /**
   * 老师的自定义字段
   */
  private Boolean hasBoyfriend;

}
