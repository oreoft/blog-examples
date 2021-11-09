package cn.someget.service;

import cn.someget.domain.bo.StudentBO;
import cn.someget.domain.bo.TeacherBO;
import cn.someget.service.feignservice.SaveDbFeignService;
import java.time.Instant;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
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
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceBusinessApplication.class)
public class ServiceBusinessTests {

  @Resource
  private SaveDbFeignService service;

  /**
   * 初始化一下测试数据
   */
  private final StudentBO MARK = StudentBO.builder()
      .instant(Instant.now())
      .hadHomework(false)
      .name("mark")
      .id(1L).build();

  private final StudentBO LEO = StudentBO.builder()
      .instant(Instant.now())
      .hadHomework(false)
      .name("leo")
      .id(2L).build();

  private final TeacherBO ANDREW = TeacherBO.builder()
      .name("andrew")
      .hasBoyfriend(false)
      .id(3L).build();

  private final TeacherBO JACKSON = TeacherBO.builder()
      .name("jackson")
      .hasBoyfriend(false)
      .id(4L).build();

  private final List<StudentBO> STUDENT_LIST = Lists.newArrayList(MARK, LEO);

  private final List<TeacherBO> TEACHER_LIST = Lists.newArrayList(ANDREW, JACKSON);


  /**
   * 确定的泛型发送和接受
   */
  @Test
  public void returnStudent() {
    StudentBO body = service.returnStudent(LEO).getBody();
    log.info("receive [{}]", body);
  }


  /**
   * 确定的泛型集合发送和接受
   */
  @Test
  public void returnStudentList() {
    List<StudentBO> body = service.returnStudentList(STUDENT_LIST).getBody();
    log.info("receive [{}]", body);
  }


  /*   --------------------------------- 上面是正常业务场景  -------------------------------------   */

  /*   ----------------------- 下面增加接口通用性,使用不确定泛型来实现  ------------------------------   */


  /**
   * 发送一个不确定的泛型, 然后进行返回
   * 模拟不确定泛型插入和查询
   */
  @Test
  public void fetchDataTest() {
    TeacherBO body = service.returnGeneric(ANDREW).getBody();
    log.info("receive [{}]", body);
  }


  /**
   * 发送一个不确定的泛型数组, 然后进行返回
   * 模拟不确定批量泛型插入和查询
   */
  @Test
  public void fetchDataListTest() {
    List<TeacherBO> body = service.returnGenericList(TEACHER_LIST).getBody();
    log.info("receive [{}]", body);
  }


}
