package com.wssccc.yujian.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {


        String username = "cccssw";

        String password = "123456";
        String up = username + password;
        String pwd = DigestUtils.md5Hex(up);
        String pwdSHA = DigestUtils.sha256Hex(pwd);

        System.out.println(pwd);
        System.out.println(pwdSHA);
        System.out.println();
    }
}
