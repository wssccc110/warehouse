package com.wssccc.yujian.controller;

import com.wssccc.yujian.common.CommonResponse;
import com.wssccc.yujian.constant.CookieConstant;
import com.wssccc.yujian.constant.RedisConstant;
import com.wssccc.yujian.entity.User;
import com.wssccc.yujian.enums.ResultEnum;
import com.wssccc.yujian.exception.USException;
import com.wssccc.yujian.form.LoginForm;
import com.wssccc.yujian.service.UserService;
import com.wssccc.yujian.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    //首页
    @RequestMapping("/home")
    public ModelAndView home() {

        return new ModelAndView("home");
    }




    @ResponseBody
    @GetMapping("/getAllUser")
    public ModelAndView getAllUser(Map<String, Object> map) {
        List<User> userList = userService.getAllUser();
        map.put("userList", userList);
        return new ModelAndView("/user/list", map);
    }


}
