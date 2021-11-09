package cn.someget.service.feignservice;

import cn.someget.domain.bo.BaseBO;
import cn.someget.domain.bo.StudentBO;
import cn.someget.domain.constant.ServiceNameConstants;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 远程调用db服务,插入数据的feign接口
 *
 * @author zyf
 * @date 2021-10-31 14:10
 */
@RequestMapping("/data")
@FeignClient(value = ServiceNameConstants.SERVICE_DATA)
public interface SaveDbFeignService {


  /**
   * 接受一个学生数据, 然后返回
   * @param bo 一个学生的bo
   * @return 一个学生的bo
   */
  @PostMapping("/student")
  ResponseEntity<StudentBO> returnStudent(@RequestBody StudentBO bo);


  /**
   * 接受一堆学生的数据 然后返回
   * @param list 一堆学生的boList
   * @return 一堆学生的bo
   */
  @PostMapping("/studentList")
  ResponseEntity<List<StudentBO>> returnStudentList(@RequestBody List<StudentBO> list);


  /*   --------------------------------- 上面是正常业务场景  -------------------------------------   */

  /*   ----------------------- 下面增加接口通用性,使用不确定泛型来实现  ------------------------------   */


  /**
   * 接受一个不确定泛型bo数据 然后返回
   * @param bo 不确定泛型bo数据
   * @return 一个bo
   */
  @PostMapping("/generic")
  <T extends BaseBO> ResponseEntity<T> returnGeneric(@RequestBody T bo);


  /**
   * 接受一堆不确定泛型bo的数据 然后返回
   *
   * @param list 一群bo
   * @return 无
   */
  @PostMapping("/genericList")
  <T extends BaseBO> ResponseEntity<List<T>> returnGenericList(@RequestBody List<T> list);


}
