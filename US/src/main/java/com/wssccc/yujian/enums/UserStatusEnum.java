package com.wssccc.yujian.enums;

import com.wssccc.yujian.util.CodeEnum;
import lombok.Getter;

@Getter
public enum UserStatusEnum implements CodeEnum {
    IS_USING(1, "已启用"),
    NOT_USING(0, "已禁用"),;
    private Integer code;
    private String status;

    UserStatusEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }
}
