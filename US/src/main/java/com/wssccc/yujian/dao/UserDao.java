package com.wssccc.yujian.dao;

import com.wssccc.yujian.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    //查找所有用户信息
    @Select(//language=MySQL
            "SELECT  id,username,password,ct,ut FROM user_info")
    List<User> getAllUser();


    //增加用户
    @Insert(//language=MySQL
            "INSERT INTO user_info(username,password)VALUES (#{username},#{password})"
    )
    int add(User user);

    //删除用户信息
    //修改用户信息

    //根据用户名查询单个用户信息
    @Select(//language=MySQL
            "SELECT id,username,password,ct,ut FROM user_info WHERE username=#{username}")
    User getUserByUsername(@Param("username") String username);


}
