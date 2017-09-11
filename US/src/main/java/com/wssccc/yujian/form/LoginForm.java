package com.wssccc.yujian.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty(message = "用户名不能为空")
    @Length(min = 6, message = "用户名长度不能少于6位")
    private String username;
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能少于6位")
    private String password;
}
