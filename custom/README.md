## Authing 集成 自定义 DEMO

### 项目结构
- cn.authing.oauth.demo.config 自定义配置
- cn.authing.oauth.demo.controller 自定义响应及测试页面跳转
- cn.authing.oauth.demo.util 工具类
- resources/static 静态页面 用于测试

### 如何使用
1. 修改配置文件
> 修改 resources 
#### 授权码模式
```properties
server.port=8082
spring.security.oauth2.client.registration.authing.client-id= 
spring.security.oauth2.client.registration.authing.client-name=authing
spring.security.oauth2.client.registration.authing.client-secret= 
spring.security.oauth2.client.registration.authing.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.authing.client-authentication-method=POST
spring.security.oauth2.client.registration.authing.redirect-uri=http://localhost:${server.port}/callback
spring.security.oauth2.client.registration.authing.scope=profile
spring.security.oauth2.client.provider.authing.user-info-authentication-method=form
spring.security.oauth2.client.provider.authing.authorization-uri=https://{domain}.authing.cn/oauth/auth
spring.security.oauth2.client.provider.authing.user-name-attribute=username
spring.security.oauth2.client.provider.authing.token-uri=https://{domain}.authing.cn/oauth/token
spring.security.oauth2.client.provider.authing.user-info-uri=https://core.authing.cn/oauth/me

```
#### 密码模式
```properties
server.port=8080
spring.security.oauth2.client.registration.authing.client-id=
spring.security.oauth2.client.registration.authing.client-name=authing
spring.security.oauth2.client.registration.authing.client-secret=
spring.security.oauth2.client.registration.authing.authorization-grant-type=password
spring.security.oauth2.client.registration.authing.client-authentication-method=POST
spring.security.oauth2.client.registration.authing.scope=profile
spring.security.oauth2.client.provider.authing.token-uri=https://{domain}.authing.cn/oauth/token
```
#### 客户端模式
```properties
server.port=8080
spring.security.oauth2.client.registration.authing.client-id=
spring.security.oauth2.client.registration.authing.client-name=authing
spring.security.oauth2.client.registration.authing.client-secret=
spring.security.oauth2.client.registration.authing.authorization-grant-type=client_credentials
spring.security.oauth2.client.registration.authing.client-authentication-method=POST
spring.security.oauth2.client.registration.authing.scope=profile
spring.security.oauth2.client.provider.authing.token-uri=https://{domain}.authing.cn/oauth/token
```
#### 简易模式
```properties
server.port=8080
spring.security.oauth2.client.registration.authing.client-id=
spring.security.oauth2.client.registration.authing.client-name=authing
spring.security.oauth2.client.registration.authing.client-secret=
spring.security.oauth2.client.registration.authing.authorization-grant-type=implicit
spring.security.oauth2.client.registration.authing.client-authentication-method=POST
spring.security.oauth2.client.registration.authing.redirect-uri=http://localhost:${server.port}/callback
spring.security.oauth2.client.registration.authing.scope=profile
spring.security.oauth2.client.provider.authing.token-uri=https://{domain}.authing.cn/oauth/token
spring.security.oauth2.client.provider.authing.authorization-uri=https://{domain}.authing.cn/oauth/auth
```
2. 启动项目
```shell 
mvn spring-boot:run
```
3. 浏览器测试
