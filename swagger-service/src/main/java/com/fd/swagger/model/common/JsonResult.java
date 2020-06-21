package com.fd.swagger.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author pan
 * @since 2018-08-13
 */
@Data
@ApiModel("统一rest返回对象")
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 返回码 */
    @ApiModelProperty(value = "0成功，其他失败")
    private Integer code;
    /** 返回信息 */
    @ApiModelProperty(value = "返回信息")
    private String msg;
    /** 返回结果 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "返回对象")
    private T data;
    /** 签名 */
    private String sign;

    public JsonResult() {
    }

    public JsonResult(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    public JsonResult(Integer code, String msg, T data, String sign){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.sign = sign;
    }

    public static <T> JsonResult<T> ok() {
        return new JsonResult<>(0, "SUCCESS", null);
    }

    public static <T> JsonResult<T> ok(T data) {
        return new JsonResult<>(0, "SUCCESS", data);
    }
    
    public static <T> JsonResult<T> ok(T data, String sign) {
        return new JsonResult<>(0, "SUCCESS", data, sign);
    }

    public static <T> JsonResult<T> fail() {
        return new JsonResult<>(1, "FAIL", null);
    }

    public static <T> JsonResult<T> fail(String msg) {
        return new JsonResult<>(1, msg, null);
    }

    public static <T> JsonResult<T> fail(Integer code, String msg) {
        return new JsonResult<>(code, msg, null);
    }
}
