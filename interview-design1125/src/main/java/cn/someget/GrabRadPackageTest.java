package cn.someget;

import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 抢红包的算法
 *
 * @author zyf
 * @date 2021-11-25 21:11
 */
public class GrabRadPackageTest {

  /**
   * 总金额, 为了方便介绍思想, 所以使用double
   */
  private int money = 100;

  /**
   * 人数
   */
  private int person = 30;

  /**
   * 最后的红包金额
   */
  private List<Integer> result = new ArrayList<>(person);

  /**
   * 二倍均值法
   */
  @Test
  public void binaryMeanTest() {
    System.out.printf("%d个人抢%d分钱\n", person, money);

    // 如果还有人就一直循环
    for (int i = 1, count = this.person; i < count; i++) {
      // 计算上界
      double max = (double) money / this.person * 2;
      this.person--;
      // 下界默认是1
      int min = 1;
      int current = (int) Math.floor(RandomUtil.randomDouble(min, max));
      result.add(current);
      money -= current;
      System.out.printf("第%d抢到了%d分, 还剩下%d分, 区间是[%d, %f]\n", i, current, money, min, max);
    }
    result.add(money);
    System.out.printf("最后一个已经抢完, 金额为%d\n", money);
    System.out.printf("已经抢完, 总共金额为%d分\n", result.stream().mapToInt(e -> e).sum());

  }


}
