package com.fd.useradmin.model.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class GreetVo implements Serializable {

    @NotNull(message = "姓名不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
