package com.fd.userservice;

import com.alibaba.csp.sentinel.Constants;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleUtil;
import com.fd.userservice.enums.WeekEnums;
import com.fd.userservice.intel.IUserService;
import com.fd.userservice.model.vo.GreetVo;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
@EnableEurekaClient //#没什么作用，标识而已
@RestController
@RequestMapping("/api-v1")
public class UserServiceApplication implements IUserService {
    static Random r = new Random();

    static{
        FlowRule fr = new FlowRule();
        fr.setResource("qaz");
        fr.setCount(4);
        FlowRuleManager.loadRules(Lists.newArrayList(fr));
    }

    @GetMapping("/haha")
    public static String hello(Long id) throws BlockException, InterruptedException {
        System.out.println("user-service:hello:"+id);
        return "g2";
    }

    @Override
    public GreetVo haha( GreetVo vo){

        System.out.println(vo.getStartTime());
        System.out.println(vo.getEndTime());
        int a = 100;

        return vo;
    }


    public static void main(String[] args)  {
       SpringApplication.run(UserServiceApplication.class, args);


    }
}
