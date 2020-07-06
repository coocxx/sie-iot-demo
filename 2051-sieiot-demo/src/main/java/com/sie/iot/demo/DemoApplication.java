package com.sie.iot.demo;


import com.sie.iot.common.application.CommonApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({"com.sie.iot.demo.controller","com.sie.iot.demo.config"})
@ImportResource({"classpath*:com/sie/iot/demo/config/spring.*.cfg.xml"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class DemoApplication extends CommonApplication {
    public static void main(String[] args) {
        args = new String[1];
        args[0] = "--spring.profiles.active=sieiot2051";
        new SpringApplicationBuilder(DemoApplication.class).run(args);
    }



}

