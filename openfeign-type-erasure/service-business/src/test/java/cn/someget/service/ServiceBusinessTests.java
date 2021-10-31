package cn.someget.service;

import cn.someget.domain.bo.StudentBO;
import cn.someget.service.feignservice.SaveDbFeignService;
import java.time.Instant;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * service1发起feign调用service2的测试类
 *
 * @author zyf
 * @date 2021-10-31 14:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceBusinessApplication.class)
public class ServiceBusinessTests {

  @Resource
  private SaveDbFeignService service;


  /**
   * 获取一个学生数据
   */
  @Test
  public void fetchStudentTest() {
    System.out.println(service.fetchStudent());
  }


  /**
   * 获取一堆学生的数据
   */
  @Test
  public void fetchStudentListTest() {

  }

  /**
   * 保存一个学生的数据
   */
  @Test
  public void saveStudentTest() {
    StudentBO bo = StudentBO.builder()
        .instant(Instant.now())
        .id(1L)
        .name("小王")
        .hadHomework(false).build();
    service.saveStudent(bo);
  }


  /**
   * 保存一堆学生的数据
   */
  @Test
  public void saveStudentListTest() {

  }


  /*   --------------------------------- 上面是正常业务场景  -------------------------------------   */

  /*   ----------------------- 下面增加接口通用性,使用不确定泛型来实现  ------------------------------   */


  /**
   * 获取一个bo数据
   */
  @Test
  public void fetchDataTest() {

  }


  /**
   * 获取一堆bo的数据
   */
  @Test
  public void fetchDataListTest() {

  }

  /**
   * 保存一个bo的数据
   */
  @Test
  public void saveDataTest() {

  }


  /**
   * 保存一堆bo的数据
   */
  @Test
  public void saveDataListTest() {

  }


}
