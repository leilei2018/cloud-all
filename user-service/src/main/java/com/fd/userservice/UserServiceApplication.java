package com.fd.userservice;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
@EnableEurekaClient //#没什么作用，标识而已
@RestController
public class UserServiceApplication {
    static Random r = new Random();


    @GetMapping("/haha")
    public String hello(){
        try {
            Thread.sleep(r.nextInt(100));
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        return "g2";
    }


    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
