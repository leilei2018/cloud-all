package com.fd.userservice.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GreetConfig {

    @NotNull(message = "config不能为空")
    private String config;
}
