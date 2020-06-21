package com.fd.useradmin.feignservice;

import com.alibaba.fastjson.JSONObject;
import com.fd.useradmin.model.vo.GreetVo;
import com.fd.useradmin.model.vo.JsonVo;
import com.fd.useradmin.model.vo.LogVo;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "eureka-server",fallbackFactory = IHelloService.HelloServiceFallbackFactory.class)
public interface IHelloService {

    /**
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/q1",method = RequestMethod.GET)
    public String helloV1_1(@RequestParam("uuid") String uuid);

    @RequestMapping(value = "/q1",method = RequestMethod.GET)
    public String helloV1_2(@RequestParam String uuid);

    @RequestMapping(value = "/q1",method = RequestMethod.GET)
    public String helloV1_3(String uuid);


    @GetMapping(value = "/q1")
    public String helloV2_1(@RequestParam("uuid") String uuid);

    @GetMapping(value = "/q1")
    public String helloV2_2(@RequestParam String uuid);

    @GetMapping(value = "/q1")
    public String helloV2_3(String uuid);



    /**
     * feign坑
     * 1、feign不支持@GetMapping 和 @PostMapping 注解，只能使用 @RequestMapping 并且 method = RequestMethod.GET必须指定get或post
     *
     * 2、当feign使用get方式请求时，@PathVariable注解@RequestParam必须设置value属性（@PathVariable或@RequestParam 注解在MVC中不设置value 默认使用当前变量的，但feign中必须设置）
     *
     * 3、feign接口如果参数为复杂参数，必须使用post方式
     */


    @Component
    class HelloServiceFallbackFactory implements FallbackFactory<IHelloService> {

        @Override
        public IHelloService create(Throwable cause) {
           //cause => FeignException  默认返回状态不等于200，404，就会抛出异常
           cause.printStackTrace();
           return null;
        }
    }

}
