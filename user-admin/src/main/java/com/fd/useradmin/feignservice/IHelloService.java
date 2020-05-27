package com.fd.useradmin.feignservice;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "USER-SERVICE",fallbackFactory = IHelloService.HelloFailbackFactory.class)
public interface IHelloService {
    @GetMapping("/haha")
    public String hello();

    @RequestMapping(value = "/query-by", method = RequestMethod.GET)
    public String queryBy(@RequestParam Map<String, Object> param);


    public class HelloFailbackFactory implements FallbackFactory<IHelloService> {
        @Override
        public IHelloService create(Throwable cause) {
            return new IHelloService() {
                @Override
                public String hello() {
                    return "系统超时";
                }

                @Override
                public String queryBy(Map<String, Object> param) {
                    return null;
                }
            };
        }
    }

}
