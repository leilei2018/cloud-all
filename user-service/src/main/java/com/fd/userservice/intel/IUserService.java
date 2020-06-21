package com.fd.userservice.intel;

import com.fd.userservice.model.vo.GreetVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserService {
    @PostMapping("/greet")
    public GreetVo haha(@Validated @RequestBody GreetVo vo) ;
}
