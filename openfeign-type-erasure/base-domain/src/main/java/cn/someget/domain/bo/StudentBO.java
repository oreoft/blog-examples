package cn.someget.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * 测试进行feige传输的学生bo
 *
 * @author zyf
 * @date 2021-10-31 14:10
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentBO  extends BaseBO {

    /**
     * 学生的私有字段
     */
    private Boolean hadHomework;

    /**
     * 我自己还想测试的序列号的一个字段
     */
    private Instant instant;

}
