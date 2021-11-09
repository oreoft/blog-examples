package cn.somget.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 为了模拟功能随便找的一个注册中心
 * @author oreoft
 */
@EnableEurekaServer
@SpringBootApplication
public class MiddleEurekaApplication {

  public static void main(String[] args) {
    SpringApplication.run(MiddleEurekaApplication.class, args);
  }

}
