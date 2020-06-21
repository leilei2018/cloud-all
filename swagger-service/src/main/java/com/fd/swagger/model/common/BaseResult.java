package com.fd.swagger.model.common;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *  返回结果集封装
 * @author lironghua
 * @date 2019-12-04
 */
@Api(tags = "接口统一返回")
@Data
public class BaseResult<T> implements Serializable {

    /**
     * 请求成功返回码
     */
    public static String SUCCESS_CODE = "0000";

    /**
     * 请求失败返回码
     */
    public static String FAIL_CODE = "500";

    private static final long serialVersionUID = 1L;

    /**
     * 通讯状态码
     */
    @ApiModelProperty(name = "rspCode", value = "通讯状态码")
    private String rspCode;
    /**
     * 通讯描述
     */
    @ApiModelProperty(name = "rspMsg", value = "通讯描述")
    private String rspMsg;
    /**
     * 商户号
     */
    @ApiModelProperty(name = "merchantId", value = "商户号")
    private String merchantId;
    /**
     * 代理商编号
     */
    @ApiModelProperty(name = "agentNo", value = "代理商编号")
    private String agentNo;
    /**
     * 签名
     */
    @ApiModelProperty(name = "sign", value = "签名")
    private String sign;

    /**
     *  响应结果集
     */
    @ApiModelProperty(name = "data", value = "响应结果集")
    private T data;

    public BaseResult(){

    }

    public BaseResult(String rspCode, String rspMsg, T data){
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
        this.data = data;
    }

    public static <T> BaseResult<T> success(T data) {
        BaseResult<T> result = new BaseResult<>(SUCCESS_CODE, "成功", data);
        return result;
    }

    public static <T> BaseResult<T> success(T data, String message) {
        BaseResult<T> result = new BaseResult<>(SUCCESS_CODE, message, data);
        return result;
    }

    public static <T> BaseResult<T> fail(String message) {
        return fail(FAIL_CODE, message);
    }

    public static <T> BaseResult<T> fail(String code, String message) {
        return fail(code, message, null);
    }

    public static <T> BaseResult<T> fail(String code, String message, T data) {
        BaseResult<T> result = new BaseResult<>(code, message, data);
        return result;
    }

    /**
     * 返回结果集
     * @param result
     * @return
     */
    public static <T> T resultData(BaseResult<T> result){
        if(result == null) {
            throw new RuntimeException("系统错误");
        }
        
        if(!SUCCESS_CODE.equals(result.getRspCode())){
            throw new RuntimeException("系统错误：" + result.getRspMsg());
        }
        return result.getData();
    }

    public static <T> boolean isOk(BaseResult<T> result){
        return SUCCESS_CODE.equals(result.getRspCode());
    }
}
