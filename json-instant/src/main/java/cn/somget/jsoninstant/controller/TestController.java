package cn.somget.jsoninstant.controller;

import cn.somget.jsoninstant.entity.TimeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试MVC自带的参数绑定对于Instant的支持
 *
 * @author zyf
 * @date 2021-10-31 01:10
 */
@RestController
public class TestController {

    /**
     * 接口测试样例请看同包下.http文件
     * @param timeDTO 测试实体类
     * @return 测试返回数据
     */
    @PostMapping("/test")
    public ResponseEntity<TimeDTO> parameterBindingTest(@RequestBody TimeDTO timeDTO) {
        return ResponseEntity.ok(timeDTO);
    }

}
