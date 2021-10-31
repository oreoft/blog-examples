package cn.someget.service.controller;

import cn.someget.domain.bo.BaseBO;
import cn.someget.domain.bo.StudentBO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接受business过来的请求
 *
 * @author zyf
 * @date 2021-10-31 15:10
 */
@RestController
@RequestMapping("/data")
public class ReceiveController {

  /**
   * 获取一个学生数据
   *
   * @return 一个学生的bo
   */
  @GetMapping("/student")
  ResponseEntity<StudentBO> fetchStudent() {
    return null;
  }


  /**
   * 获取一堆学生的数据
   *
   * @return 一堆学生的bo
   */
  @GetMapping("/studentList")
  ResponseEntity<List<StudentBO>> fetchStudentList() {
    return null;
  }

  /**
   * 保存一个学生的数据
   *
   * @param studentBO 一个学生的bo
   * @return 无
   */
  @PostMapping("/student")
  ResponseEntity<?> saveStudent(@RequestBody StudentBO studentBO) {
    return null;
  }


  /**
   * 保存一堆学生的数据
   *
   * @param studentBOList 一个学生的bo
   * @return 无
   */
  @PostMapping("/studentList")
  ResponseEntity<?> saveStudentList(@RequestBody List<StudentBO> studentBOList) {
    return null;
  }


  /*   --------------------------------- 上面是正常业务场景  -------------------------------------   */

  /*   ----------------------- 下面增加接口通用性,使用不确定泛型来实现  ------------------------------   */


  /**
   * 获取一个bo数据
   *
   * @return 一个bo
   */
  @GetMapping("/data")
  <T extends BaseBO> ResponseEntity<T> fetchData() {
    return null;
  }


  /**
   * 获取一堆bo的数据
   *
   * @return 一堆bo
   */
  @GetMapping("/dataList")
  <T extends BaseBO> ResponseEntity<List<T>> fetchDataList() {
    return null;
  }

  /**
   * 保存一个bo的数据
   *
   * @param bo 一个bo
   * @return 无
   */
  @PostMapping("/data")
  <T extends BaseBO> ResponseEntity<?> saveData(@RequestBody T bo) {
    return null;
  }


  /**
   * 保存一堆bo的数据
   *
   * @param studentBOList 一个bo
   * @return 无
   */
  @PostMapping("/dataList")
  <T extends BaseBO> ResponseEntity<?> saveDataList(@RequestBody List<T> studentBOList) {
    return null;
  }

}
