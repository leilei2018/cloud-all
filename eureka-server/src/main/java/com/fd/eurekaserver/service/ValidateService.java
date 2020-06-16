package com.fd.eurekaserver.service;

import com.fd.eurekaserver.vo.LogVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface ValidateService {

    public boolean vad1(@Validated @NotNull(message = "uuid不能为空") String uuid);
    public boolean vad2(@Valid LogVo logVo);

}
