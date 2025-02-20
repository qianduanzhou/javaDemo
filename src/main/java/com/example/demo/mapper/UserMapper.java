package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    // 根据用户名查询
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    // 根据id判断用户是否存在
    @Select("SELECT EXISTS(SELECT 1 FROM user WHERE id = #{id})")
    boolean checkUserExistsById(@Param("id") Long id);

    // 根据用户名判断用户是否存在
    @Select("SELECT EXISTS(SELECT 1 FROM user WHERE username = #{username})")
    boolean checkUserExistsByUsername(@Param("username") String username);

    // 分页查询用户名列表
    @Select("SELECT * FROM user WHERE username LIKE CONCAT('%', #{username}, '%') LIMIT #{offset}, #{size}")
    List<User> findListByUsername(@Param("username") String username, @Param("offset") int offset, @Param("size") int size);

    // 插入用户
    @Insert("INSERT INTO user(username, password) VALUES(#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    // 更新用户
    @Update("UPDATE user SET username = #{username}, password = #{password} WHERE id = #{id}")
    int update(User user);

    // 删除用户
    @Delete("DELETE FROM user WHERE id = #{id}")
    void delete(Long id);

    // 根据用户名查询总数
    @Select("SELECT COUNT(*) FROM user WHERE username LIKE CONCAT('%', #{username}, '%')")
    int getTotalCountByUsername(@Param("username") String username);
}