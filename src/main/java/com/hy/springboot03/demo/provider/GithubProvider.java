package com.hy.springboot03.demo.provider;

import com.alibaba.fastjson.JSON;
import com.hy.springboot03.demo.dto.AccessTokenDTO;
import com.hy.springboot03.demo.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * @Component：把类初始化spring的上下文;
 * 点击登陆，调用github的authorize接口redirect_url到我们的地址，携带code
 * 再使用code调用access_token接口，携带code返回access_token，再使用user接口，和返回的access_token，得到用户名称
 * 存入session
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            String[] str_result = result.split("&");
            String tokenstr = str_result[0];
            String token = tokenstr.split("=")[1];
            System.out.println(result);
            System.out.println(token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(" https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().toString();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

