package com.wssccc.yujian.controller;

import com.wssccc.yujian.constant.CookieConstant;
import com.wssccc.yujian.constant.RedisConstant;
import com.wssccc.yujian.entity.User;
import com.wssccc.yujian.enums.ResultEnum;
import com.wssccc.yujian.form.LoginForm;
import com.wssccc.yujian.service.UserService;
import com.wssccc.yujian.util.CookieUtil;
import com.wssccc.yujian.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ModelAndView login(@Valid LoginForm loginForm,
                              BindingResult bindingResult,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpServletResponse response,
                              HttpServletRequest request,
                              Map<String, Object> map) {
        //对输入内容进行校验  为空   字符长度
        if (bindingResult.hasErrors()) {
            log.error("【输入的参数不正确】,loginForm={}" + loginForm);
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "user/index");
            return new ModelAndView("common/error", map);
        }
        //在redis中查询用户信息
        String pwdRedis = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, username));
        //查到就和输入的内容进行对比，正确则登录成功，错误即登录失败
        if (!StringUtils.isEmpty(pwdRedis)) {
            String pwdSalt = username + password;
            String pwdMD5 = DigestUtils.md5Hex(pwdSalt);
            String sha = DigestUtils.sha256Hex(pwdMD5);

            if (!sha.equals(pwdRedis)) {

                map.put("msg", ResultEnum.LOGIN_FAILED.getMessage());
                return new ModelAndView("common/error", map);
            }
            CookieUtil.set(response, CookieConstant.TOKEN, username, RedisConstant.EXPIRE);

            return new ModelAndView("redirect:/user/home");
        }
        //查不到则进入数据库查询    正确则登录成功，错误即登录失败  将登录信息存储 cookie和redis
        User user = userService.getUserByUsername(username);
        if (user != null) {
            String pwdSalt = username + password;
            String pwdMD5 = DigestUtils.md5Hex(pwdSalt);
            String sha = DigestUtils.sha256Hex(pwdMD5);
            if (sha.equals(user.getPassword())) {
                //生成一个随机token值  并返回给前端
                String token = KeyUtil.getUniqueKey();
                //存redis
                Integer exprie = RedisConstant.EXPIRE;
                redisTemplate.opsForValue().set(token, sha, exprie, TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, username), sha, exprie, TimeUnit.SECONDS);
                //存cookie
                CookieUtil.set(response, CookieConstant.TOKEN, username, exprie);
                CookieUtil.set(response, "tid", token, exprie);
                map.put("username", user.getUsername());
                return new ModelAndView("forward:" + "/user/home", map);
            }
        }
        map.put("msg", ResultEnum.LOGIN_FAILED.getMessage());
        return new ModelAndView("common/error", map);
    }


    //登录页
    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("login");
    }


}
