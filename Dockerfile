# 使用官方的 OpenJDK 镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 将 JAR 文件复制到容器中
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar

# 下载 wait-for-it 脚本
RUN apt-get update && apt-get install -y curl && \
    curl -o /wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh && \
    chmod +x /wait-for-it.sh \

# 暴露端口
EXPOSE 8081

# 启动应用
# ENTRYPOINT ["java", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar"]

# 启动 Spring Boot 应用（等待 MySQL 服务启动后再启动）
CMD /wait-for-it.sh mysql:3306 -- java -jar /app/demo-0.0.1-SNAPSHOT.jar
