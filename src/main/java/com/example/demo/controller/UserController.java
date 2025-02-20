package com.example.demo.controller;

import com.example.demo.bean.PageBean;
import com.example.demo.dto.ResponseResult;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello SpringBoot!";
    }

    @PostMapping("/register")
    public ResponseResult<User> registerUser(@RequestBody User user) {
        boolean flag = userService.registerUser(user);
        if (!flag) return  ResponseResult.fail(500, "该用户已被注册");
        return ResponseResult.success("注册成功", user);
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
    public ResponseResult<PageBean<User>> searchUsersByName(@RequestParam String username,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return ResponseResult.success("查询成功", userService.searchUsersByName(username, page, size));
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
