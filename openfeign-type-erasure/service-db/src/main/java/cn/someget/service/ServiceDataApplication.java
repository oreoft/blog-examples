package cn.someget.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 模拟service2
 * @author oreoft
 */
@EnableFeignClients
@SpringBootApplication
public class ServiceDataApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceDataApplication.class, args);
  }

}
