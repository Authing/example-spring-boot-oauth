package cn.authing.oauth.demo.controller;

import cn.authing.core.auth.AuthenticationClient;
import cn.authing.core.types.ProtocolEnum;
import cn.authing.oauth.demo.util.AuthenticationUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RestController
public class OauthController {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    /**
     * 回调地址
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("authing");
        AuthorizationGrantType type = clientRegistration.getAuthorizationGrantType();

        if(Objects.equals(AuthorizationGrantType.AUTHORIZATION_CODE,type)){
            // 授权码模式
            AuthenticationClient client = AuthenticationUtil.getAuthenticationClient();
            client.setProtocol(ProtocolEnum.OAUTH);
            String code = request.getParameter("code");

            // 使用 code 换取访问令牌
            Object result = client.getAccessTokenByCode(code).execute();
            String token=((LinkedTreeMap) result).get("access_token").toString();
            // 使用令牌获取用户信息
            Object user = client.getUserInfoByAccessToken(token).execute();
            // TODO 后续逻辑

        } else if (Objects.equals(AuthorizationGrantType.PASSWORD,type)){
            //密码模式
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            try {
                Response callResp = client.newCall(new Request.Builder()
                                                        .url(clientRegistration.getProviderDetails().getTokenUri())
                                                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                                        .post(new FormBody.Builder().add("client_id", clientRegistration.getClientId())
                                                                .add("client_secret", clientRegistration.getClientSecret())
                                                                .add("grant_type", "password")
                                                                .add("scope", String.join(",",clientRegistration.getScopes()))
                                                                .add("username", request.getParameter("username"))
                                                                .add("password", request.getParameter("password"))
                                                                .build())
                                                        .build())
                                            .execute();
                if (Objects.equals(callResp.code(),200)){
                    String json = callResp.body().string();
                    Result result = new Gson().fromJson(json, Result.class);
                    System.out.println(result);
                    AuthenticationClient authenticationClient = AuthenticationUtil.getAuthenticationClient();
                    Object userInfo=authenticationClient.getUserInfoByAccessToken(result.getAccess_token());
                    //TODO 业务处理

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (Objects.equals(AuthorizationGrantType.CLIENT_CREDENTIALS,type)){
            // 客户端模式
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            try {
                Response callResp = client.newCall(new Request.Builder()
                                                                .url(clientRegistration.getProviderDetails().getTokenUri())
                                                                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                                                .post(new FormBody.Builder().add("client_id",  clientRegistration.getClientId())
                                                                        .add("client_secret", clientRegistration.getClientSecret())
                                                                        .add("grant_type", "client_credentials")
                                                                        .add("scope", String.join(",",clientRegistration.getScopes()))
                                                                        .build())
                                                                .build()).execute();

                if (Objects.equals(callResp.code(),200)){
                    String json = callResp.body().string();
                    Result result = new Gson().fromJson(json, Result.class);
                    System.out.println(result);
                    AuthenticationClient authenticationClient = AuthenticationUtil.getAuthenticationClient();
                    Object userInfo=authenticationClient.getUserInfoByAccessToken(result.getAccess_token());
                    //TODO 业务处理

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Result{
        private String access_token;
        private String token_type;
        private String expires_in;
        private String refresh_token;
        private String scope;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(String expires_in) {
            this.expires_in = expires_in;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }
    }

}
