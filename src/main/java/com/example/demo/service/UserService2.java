package com.example.demo.service;

import com.example.demo.bean.PageBean;
import com.example.demo.model.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService2 {

    private final UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    public UserService2(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Boolean registerUser(User user) {
        String username = user.getUsername();
        Boolean flag = userMapper.checkUserExistsByUsername(username);
        if (!flag) {
            userMapper.insert(user);
            return true;
        };
        return false;
    }

    public User loginUser(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            redisUtil.set(username, username);
            return user;
        }
        return null;
    }

    // 删除用户
    public Boolean deleteUser(Long id) {
        if (userMapper.checkUserExistsById(id)) {
            userMapper.delete(id);
            System.out.println("User deleted successfully.");
            return true;
        } else {
            System.out.println("User not found, deletion skipped.");
            return false;
        }
    }

    // 根据关键字查询用户
    public PageBean<User> searchUsersByName(String username, int page, int size) {
        int offset = (page - 1) * size; // 计算偏移量
        int totalCount = userMapper.getTotalCountByUsername(username); // 获取总数
        List<User> list = userMapper.findListByUsername(username, offset, size);
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setRows(list);
        pageBean.setTotalCount(totalCount);
        return pageBean;
    }

    // 查询用户信息
    public Object getToken(String username) {
        if (redisUtil.hasKey(username)) {
            return redisUtil.get(username);
        }
        return null;
    }
}