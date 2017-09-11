package com.wssccc.yujian.service;

import com.wssccc.yujian.dao.UserDao;
import com.wssccc.yujian.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceImplTest {

//    private final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    private UserDao userDao;

    @Test
    public void loggerTest() {
        log.info("info");
        log.debug("debug");
        log.error("error");
    }


    @Test
    public void add() throws Exception {
        User user = new User();
        user.setUsername("wssccc");
        user.setPassword("123456");
        int result = userDao.add(user);
        Assert.assertEquals(1, result);
    }

    @Test
    public void getUserByUsername() {
        String username = "wssccc";
        User result = userDao.getUserByUsername(username);
        log.info("wssccc");
        Assert.assertNotNull(result);
    }

}