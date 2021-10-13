# Authing 集成 Spring Security Demo
## 简介
该项目用于展示使用Spring Security 框架集成 Authing 服务
其中包括单点登录，引用 Authing SDK 及通过 Authing SDK 进行自定义扩展
## 项目结构
- server-file SSO 测试项目 文件服务
- server-pic SSO 测试项目 图片服务
- custom 自定义响应项目

## SSO 测试方法

1. 修改项目配置 server-file 并启动 
    1. 修改配置文件 server-file\resources\application.properties
    2. 启动项目
        ```shell 
           mvn spring-boot:run
        ```
2. 修改项目配置 server-pic 并启动
    1. 修改配置文件 server-pic\resources\application.properties
    2. 启动项目
        ```shell 
           mvn spring-boot:run
        ```
