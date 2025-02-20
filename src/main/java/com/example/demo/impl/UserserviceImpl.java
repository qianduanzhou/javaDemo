package com.example.demo.impl;

import com.example.demo.bean.PageBean;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserserviceImpl implements UserService {
    private final UserMapper userMapper;  // 注入MyBatis Mapper

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean registerUser(User user) {
        String username = user.getUsername();
        boolean flag = userMapper.checkUserExistsByUsername(username);
        if (!flag) {
            userMapper.insert(user);
            return true;
        };
        return false;
    }

    @Override
    public User loginUser(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            redisUtil.set(username, username);
            return user;
        }
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userMapper.checkUserExistsById(id)) {
            userMapper.delete(id);
            System.out.println("User deleted successfully.");
            return true;
        } else {
            System.out.println("User not found, deletion skipped.");
            return false;
        }
    }

    @Override
    public PageBean<User> searchUsersByName(String username, int page, int size) {
        int offset = (page - 1) * size; // 计算偏移量
        int totalCount = userMapper.getTotalCountByUsername(username); // 获取总数
        List<User> list = userMapper.findListByUsername(username, offset, size);
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setRows(list);
        pageBean.setTotalCount(totalCount);
        return pageBean;
    }

    @Override
    public Object getToken(String username) {
        if (redisUtil.hasKey(username)) {
            return redisUtil.get(username);
        }
        return null;
    }
}
