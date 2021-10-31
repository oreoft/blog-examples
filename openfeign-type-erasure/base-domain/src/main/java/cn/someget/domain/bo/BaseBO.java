package cn.someget.domain.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 基础BO
 *
 * @author zyf
 * @date 2021-10-31 15:10
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseBO {

  /**
   * pk
   */
  private Long id;

  /**
   * name
   */
  private String name;

}
