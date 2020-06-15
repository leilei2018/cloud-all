package com.fd.eurekaserver;

import com.fd.eurekaserver.vo.LogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableEurekaServer
@RestController
@EnableScheduling
@Slf4j
public class EurekaServerBootstrapApplication {

    @PostMapping("/qq2/{id}")
    public String weq2( @RequestBody Map<String,Object> params){
        System.out.println(params);
        return "qwe";
    }

    @PostMapping("/qq/{id}")
    public String weq( @RequestBody(required = false) LogVo lg){
        System.out.println("master");
        return "qwe";
    }

    public static void main(String[] args) {
        //SpringApplication.run(EurekaServerBootstrapApplication.class,args);
        log.info("---------------用户编号[{}]的提现流水号[{}]的交易流水结果:[{}]",new Object[]{"aa","bb","cc"});

    }
}
