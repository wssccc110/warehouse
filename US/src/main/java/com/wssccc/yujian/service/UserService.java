package com.wssccc.yujian.service;

import com.wssccc.yujian.dao.UserDao;
import com.wssccc.yujian.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    public void add(User user) {
        userDao.add(user);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
