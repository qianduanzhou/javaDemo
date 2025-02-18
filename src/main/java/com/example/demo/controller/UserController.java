package com.example.demo.controller;

import com.example.demo.dto.ResponseResult;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController // 使用 @RestController 替代 @Controller，直接返回 JSON 数据
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello() {
        return "Hello SpringBoot!";
    }

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

    // 删除用户
    @PostMapping("/deleteById")
    public ResponseResult<Long> deleteUser(@RequestParam Long id) {
        Boolean isDelete = userService.deleteUser(id);
        if (isDelete) {
            return ResponseResult.success("删除成功", id);
        } else {
            return ResponseResult.fail(404, "用户不存在");
        }
    }

    // 根据关键字查询用户
    @GetMapping("/search")
    public Page<User> searchUsersByName(@RequestParam String username,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return userService.searchUsersByName(username, page, size);
    }

    // 获取token
    @GetMapping("/getToken")
    public ResponseResult<Object> getToken(@RequestParam String username) {
        Object token = userService.getToken(username);
        if (token != null) {
            return ResponseResult.success("获取token成功", token);
        }
        return ResponseResult.fail(404, "获取token失败");
    }
}