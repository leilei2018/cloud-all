package com.fd.eurekaserver.service.impl;

import com.fd.eurekaserver.service.ValidateService;
import com.fd.eurekaserver.vo.LogVo;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Service
public class ValidateServiceImpl implements ValidateService {
    @Override
    public boolean vad1( String uuid) {
        return false;
    }

    @Override
    public boolean vad2(LogVo logVo) {
        return false;
    }
}
