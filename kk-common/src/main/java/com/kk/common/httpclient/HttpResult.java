package com.kk.common.httpclient;

/**
 * @ClassName HttpResult
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/6 0:09
 */
public class HttpResult {

    private Integer code;
    private String data;

    public HttpResult() {

    }

    public HttpResult(Integer code, String data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
