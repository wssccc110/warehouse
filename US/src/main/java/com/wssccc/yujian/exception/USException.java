package com.wssccc.yujian.exception;

import com.wssccc.yujian.enums.ResultEnum;

public class USException extends RuntimeException {
    private Integer code;

    public USException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public USException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
