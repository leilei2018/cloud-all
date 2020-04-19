package com.fd.useradmin.feignservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "USER-SERVICE1",fallbackFactory = HelloFailbackFactory.class)
public interface IHelloService {
    @GetMapping("/haha")
    public String hello();

    @RequestMapping(value = "/query-by", method = RequestMethod.GET)
    public String queryBy(@RequestParam Map<String, Object> param);
}
