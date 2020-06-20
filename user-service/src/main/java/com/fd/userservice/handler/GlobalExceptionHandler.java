package com.fd.userservice.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map fpayExceptionHandler(HttpServletRequest req, Exception e) {
        Map map = new HashMap();
        map.put("code","0000");
        map.put("msg",e.getMessage());
        return map;
    }
}
