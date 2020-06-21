package com.fd.useradmin.feignservice.impl;

import com.fd.useradmin.feignservice.FeignStandard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * RequestParamParameterProcessor
 * 1:以下接口声明，启动都正常
 */
public interface RequestParamFeignStandard extends FeignStandard {
    @RequestMapping(value = "/q1",method = RequestMethod.GET)
    public String helloV1_1(@RequestParam("uuid") String uuid);  // success

    @RequestMapping(value = "/q1",method = RequestMethod.GET)
    public String helloV1_2(@RequestParam String uuid);        // success

    @RequestMapping(value = "/q1",method = RequestMethod.GET)
    public String helloV1_3(String uuid);


    @GetMapping(value = "/q1")
    public String helloV2_1(@RequestParam("uuid") String uuid); // success

    @GetMapping(value = "/q1")
    public String helloV2_2(@RequestParam String uuid);  // success

    @GetMapping(value = "/q1")
    public String helloV2_3(String uuid);

    /**
     * 结论：
     * 使用
     * 1：@RequestMapping(value = "/q1",method = RequestMethod.GET)  @GetMapping 都可以，并没有报错
     * 2：要想feign传参成功，必须添加@RequestParam(加不加name都可以，按照规范还是建议加)
     * ***不加@RequestParam，传参不成功***
     */
}
