package com.hy.springboot03.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication是一个方便的注释，它添加了以下所有内容:
 * @Configuration:将类标记为应用程序上下文的bean定义源。
 * @EnableAutoConfiguration:告诉Spring Boot根据类路径设置、其他bean和各种属性设置开始添加bean。
 * 例如，如果spring-webmvc位于类路径上，则该注释将应用程序标记为web应用程序并激活关键行为，例如设置DispatcherServlet。
 * @ComponentScan:告诉Spring在com/example包中查找其他组件、配置和服务，让它找到控制器。
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
