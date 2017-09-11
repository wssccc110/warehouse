package com.wssccc.yujian.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(200, "成功"),
    PARAM_ERROR(10, "参数不正确"),
    LOGIN_FAILED(11, "用户名或密码不正确"),
    USER_NOT_EXIST(12,"用户名不存在"),
    ;
    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
