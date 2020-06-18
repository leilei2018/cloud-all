package com.fd.swagger.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@RequestMapping
public class HelloController {
    @RequestMapping("/addMember")
    @ApiOperation(nickname = "addMember",value="添加用户",httpMethod = "POST")
    @ResponseBody
    public String hello(){
        return null;
    }

}
