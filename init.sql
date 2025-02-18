-- 创建数据库表
-- 创建 users 表
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,  -- 用户名字段，唯一
    password VARCHAR(255) NOT NULL          -- 密码字段
);

-- 插入一些示例数据
INSERT INTO users (username, password) VALUES
('user1', 'password1'),
('user2', 'password2');
