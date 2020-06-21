package com.fd.userservice.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fd.userservice.handler.JacksonString2DateDeserializer;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class GreetVo implements Serializable {

    private String name;
    @NotNull(message = "xxxxxxxxx")
    @Valid
    private GreetConfig config;

    @JsonDeserialize(using = JacksonString2DateDeserializer.class)
    private Date startTime;
    @JsonDeserialize(using = JacksonString2DateDeserializer.class)
    private Date endTime;

}
