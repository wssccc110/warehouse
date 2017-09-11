package com.wssccc.yujian.common;

import lombok.Data;

import java.util.Map;

@Data
public class CommonResponse {
    private int code;
    private String message;
    private Object data;

    public CommonResponse(Object data) {
        this.data = data;
    }

    public CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
