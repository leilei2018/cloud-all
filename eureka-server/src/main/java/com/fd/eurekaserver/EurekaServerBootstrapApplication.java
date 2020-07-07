package com.fd.eurekaserver;

import com.fd.eurekaserver.annotation.ResponseBodyMessage;
import com.fd.eurekaserver.annotation.SimpleRestController;
import com.fd.eurekaserver.service.IFeignService;
import com.fd.eurekaserver.service.ValidateService;
import com.fd.eurekaserver.vo.LogVo;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.MimeType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class, RedissonAutoConfiguration.class})
//@EnableEurekaServer
@SimpleRestController
@EnableScheduling
@Slf4j
@EnableCaching
public class EurekaServerBootstrapApplication  {

    RedissonClient redissonClient;

    RBloomFilter<Integer> goods_query_bf;

    Random r = new Random();

    public void init(){
        goods_query_bf = redissonClient.getBloomFilter("goods_query_bf");
        goods_query_bf.tryInit(10000,0.01);
        for (int i=0;i<10000;i++){
            goods_query_bf.add(r.nextInt(10000));
        }
    }

    @GetMapping("/add")
    public void add(int q){
        goods_query_bf.add(q);
    }

    @GetMapping("/query")
    public boolean query(Integer key){
        int i = r.nextInt(10000);
        if (key!=null){
            i = key;
        }
        boolean contains = goods_query_bf.contains(i);
        log.info("生成数字为："+i+",是否存在结果："+contains);

        if (!contains){
            log.info("不存在的ID访问，直接返回NULL");
        }else{
            log.info("访问一个存在的ID，先查询缓存，如果不存在，再查询db");
        }

        return contains;
    }

    @Autowired
    private ValidateService validateService;


    @PostConstruct
    public void cache(){

    }

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder()
                .sources(EurekaServerBootstrapApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        ValidateService validateService = ctx.getBean(ValidateService.class);
        LogVo log = new LogVo();
        log.setName("we");
        validateService.vad1(log);
        validateService.vad1(log);


        LogVo log2 = new LogVo();
        log2.setName("qwer");
        validateService.vad1(log);
        validateService.vad1(log);
        validateService.vad1(log2);
        validateService.vad1(log2);

        //SpringApplication.run(EurekaServerBootstrapApplication.class,args);
        //log.info("---------------用户编号[{}]的提现流水号[{}]的交易流水结果:[{}]",new Object[]{"aa","bb","cc"});



    }
}
