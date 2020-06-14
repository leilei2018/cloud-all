package com.fd.useradmin.feignservice;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "USER-SERVICE",fallbackFactory = IHelloService.HelloServiceFallbackFactory.class)
public interface IHelloService {
    @GetMapping("/haha")
    public String hello();

    @RequestMapping(value = "/query-by", method = RequestMethod.GET)
    public String queryBy(@RequestParam Map<String, Object> param);

    @Component
    class HelloServiceFallbackFactory implements FallbackFactory<IHelloService> {

        @Override
        public IHelloService create(Throwable cause) {
           //cause => FeignException  默认返回状态不等于200，404，就会抛出异常

           return new IHelloService() {
               @Override
               public String hello() {
                   return "系统超时"+cause.getMessage();
               }

               @Override
               public String queryBy(Map<String, Object> param) {
                   return null;
               }
           };
        }
    }

}
