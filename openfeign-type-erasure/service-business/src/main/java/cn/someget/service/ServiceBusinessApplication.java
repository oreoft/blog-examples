package cn.someget.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 模拟service1
 * @author oreoft
 */
@EnableFeignClients
@SpringBootApplication
public class ServiceBusinessApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceBusinessApplication.class, args);
  }

}
