package com.fd.useradmin.feignservice;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
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
