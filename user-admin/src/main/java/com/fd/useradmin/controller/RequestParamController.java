package com.fd.useradmin.controller;

import com.fd.useradmin.feignservice.IHelloService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qp")
public class RequestParamController {
    @Autowired
    private IHelloService service;

    @RequestMapping(value = "/q1",method = RequestMethod.GET)
    public String helloV1_1(@RequestParam("uuid") String uuid){
        return service.helloV1_1(uuid);
    }

    @RequestMapping(value = "/q2",method = RequestMethod.GET)
    public String helloV1_2(@RequestParam String uuid){
        return service.helloV1_2(uuid);
    }

    @RequestMapping(value = "/q3",method = RequestMethod.GET)
    public String helloV1_3(String uuid){
        return service.helloV1_3(uuid);
    }

    @GetMapping(value = "/q4")
    public String helloV2_1(@RequestParam("uuid") String uuid){
        return service.helloV2_1(uuid);
    }

    @GetMapping(value = "/q5")
    public String helloV2_2(@RequestParam String uuid){
        return service.helloV2_2(uuid);
    }

    @GetMapping(value = "/q6")
    public String helloV2_3(String uuid){
        return service.helloV2_3(uuid);
    }
}
