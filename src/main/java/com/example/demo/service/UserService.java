package com.example.demo.service;

import com.example.demo.bean.PageBean;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * 注册
     * @param user 用户账号和密码
     * @return 是否注册成功
     */
    boolean registerUser(User user);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 用户实体
     */
    User loginUser(String username, String password);

    /**
     * 删除用户
     * @param id 删除的id
     * @return 是否删除成功
     */
    boolean deleteUser(Long id);

    /**
     * 根据关键字查询用户
     * @param username 用户名
     * @param page 页面
     * @param size 一页数量
     * @return 分页数据
     */
    PageBean<User> searchUsersByName(String username, int page, int size);

    /**
     * 查询用户token
     * @param username 用户名
     * @return 用户token
     */
    Object getToken(String username);
}