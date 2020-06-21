package com.fd.eurekaserver;

import com.fd.eurekaserver.annotation.ResponseBodyMessage;
import com.fd.eurekaserver.annotation.SimpleRestController;
import com.fd.eurekaserver.service.ValidateService;
import com.fd.eurekaserver.vo.LogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.MimeType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableEurekaServer
@SimpleRestController
@EnableScheduling
@Slf4j
public class EurekaServerBootstrapApplication {

    @Autowired
    ValidateService validateService;

    @RequestMapping("/q1")
    //springboot-validate验证框架并不会验证 基本数据属性 @Valid @notNull,需要加切面自己调用validateUtil去校验
    public String q1(@Valid @NotNull(message = "uuid不能为空") String uuid){
        //validateService.vad1(null);
        return uuid;
    }

    @GetMapping(value = "/q2/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBodyMessage("q2保存成功")
    public String q2(@PathVariable Long id, HttpServletRequest request){ //bad request
        log.info("请求头信息："+request.getContentType());
        //validateService.vad2(new LogVo());
        return "q2";
    }

    @GetMapping("/q3")
    public String weq32(@Validated @RequestBody LogVo logVo){
        log.info(logVo.toString());
        return "q3";
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerBootstrapApplication.class,args);
        //log.info("---------------用户编号[{}]的提现流水号[{}]的交易流水结果:[{}]",new Object[]{"aa","bb","cc"});



    }
}
