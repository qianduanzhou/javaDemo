package com.example.demo.controller;

import com.example.demo.dto.ResponseResult;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController // 使用 @RestController 替代 @Controller，直接返回 JSON 数据
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseResult<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseResult.success("注册成功", registeredUser);
    }

    @PostMapping("/login")
    public ResponseResult<User> loginUser(@RequestParam String username, @RequestParam String password) {
        User user = userService.loginUser(username, password);
        if (user != null) {
            return ResponseResult.success("登录成功", user);
        } else {
            return ResponseResult.fail(401, "用户名或密码错误");
        }
    }
}