package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository extends JpaRepository<User, Long> {
    // 查找用户
    User findByUsername(String username);
    // 关键字查询：根据名字模糊查询
    Page<User> findByUsernameContaining(String username, Pageable pageable);
}