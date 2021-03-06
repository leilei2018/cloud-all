package com.fd.useradmin;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.fd.useradmin.feignservice.IHelloService;
import com.fd.useradmin.model.vo.GreetVo;
import com.fd.useradmin.model.vo.LogVo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableFeignClients
//@EnableCircuitBreaker
public class UserAdminApplication {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IHelloService helloService;

    @Bean
    public RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //factory.setConnectTimeout(5000);
       //factory.setReadTimeout(10000);
        return new RestTemplate(factory);
    }

   
  /*  @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "20000")
            }
    )*/
    @PostMapping("/hello")
    //@SentinelResource("haha")
    public Map<String,Object> hello(@Validated  @RequestBody GreetVo vo){
        Map<String,Object> map = new HashMap<>();
       /* String hello = helloService.hello();
        GreetVo greetVo = helloService.hello2();*/
        //String hello = restTemplate.getForObject("http://USER-SERVICE/haha?n=" + n, String.class);

        //map.put("q3",helloService.weq32(1L));
        return map;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserAdminApplication.class,args);
    }
}
