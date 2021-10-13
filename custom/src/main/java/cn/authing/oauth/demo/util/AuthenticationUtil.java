package cn.authing.oauth.demo.util;

import cn.authing.core.auth.AuthenticationClient;

public class AuthenticationUtil {
    /**
     * 应用ID
     */
    private static String appId="";
    /**
     * 应用秘钥
     */
    private static String appSecret="";
    /**
     * 应用地址
     */
    private static String appHost="";
    
    private static String redirectUri="";
    
    /**
     * 获取认证客户端
     * @return
     */
    public static AuthenticationClient getAuthenticationClient(){
        AuthenticationClient authenticationClient = new AuthenticationClient(appId, appHost);
        authenticationClient.setSecret(appSecret);
        authenticationClient.setRedirectUri(redirectUri);
        return authenticationClient;
    };
}
