package cn.someget.service.controller;

import cn.someget.domain.bo.BaseBO;
import cn.someget.domain.bo.StudentBO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接受business过来的请求
 * 假装在这里直接模拟查询数据返回
 *
 * @author zyf
 * @date 2021-10-31 15:10
 */
@Slf4j
@RestController
@RequestMapping("/data")
public class ReceiveController {

  /**
   * 接受一个学生数据, 然后返回
   *
   * @return 一个学生的bo
   */
  @PostMapping("/student")
  public ResponseEntity<StudentBO> returnStudent(@RequestBody StudentBO bo) {
    log.info("return [{}]", bo);
    return ResponseEntity.ok(bo);
  }


  /**
   * 接受一堆学生的数据 然后返回
   *
   * @return 一堆学生的bo
   */
  @PostMapping("/studentList")
  public ResponseEntity<List<StudentBO>> returnStudentList(@RequestBody List<StudentBO> list) {
    log.info("return {}", list);
    return ResponseEntity.ok(list);
  }


  /*   --------------------------------- 上面是正常业务场景  -------------------------------------   */

  /*   ----------------------- 下面增加接口通用性,使用不确定泛型来实现  ------------------------------   */


  /**
   * 接受一个不确定泛型bo数据 然后返回
   *
   * @return 一个bo
   */
  @PostMapping("/generic")
  public <T extends BaseBO> ResponseEntity<T> returnGeneric(@RequestBody T bo) {
    log.info("return [{}]", bo);
    return ResponseEntity.ok(bo);
  }


  /**
   * 接受一堆不确定泛型bo的数据 然后返回
   *
   * @param list 一群bo
   * @return 无
   */
  @PostMapping("/genericList")
  public <T extends BaseBO> ResponseEntity<List<T>> returnGenericList(@RequestBody List<T> list) {
    log.info("return [{}]", list);
    return ResponseEntity.ok(list);
  }


}
