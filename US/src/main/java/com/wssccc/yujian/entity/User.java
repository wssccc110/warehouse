package com.wssccc.yujian.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wssccc.yujian.enums.UserStatusEnum;
import com.wssccc.yujian.util.Date2LongSerializer;
import com.wssccc.yujian.util.EnumUtil;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date ct;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date ut;
    private Integer status = UserStatusEnum.IS_USING.getCode();

    //登录时间
    private Date lt;

    @JsonIgnore
    public UserStatusEnum getUserStatusEnum() {
        return EnumUtil.getByCode(status, UserStatusEnum.class);
    }


}
