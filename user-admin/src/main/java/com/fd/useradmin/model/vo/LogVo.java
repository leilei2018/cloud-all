package com.fd.useradmin.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LogVo {
    @NotNull(message = "uuid不能为空")
    private String date;
    private String age;
    private String name;

    @Override
    public String toString() {
        return "LogVo{" +
                "date='" + date + '\'' +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
