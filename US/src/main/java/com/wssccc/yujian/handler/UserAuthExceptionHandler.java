package com.wssccc.yujian.handler;

import com.wssccc.yujian.exception.UserAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UserAuthExceptionHandler {
//    拦截登陆异常
    @ExceptionHandler(value = UserAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:/");
    }
}
