# 使用官方的 OpenJDK 镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 将 JAR 文件复制到容器中
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar

# 暴露端口
EXPOSE 8081

# 启动应用
ENTRYPOINT ["java", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar"]
