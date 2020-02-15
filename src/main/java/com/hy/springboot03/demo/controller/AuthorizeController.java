package com.hy.springboot03.demo.controller;

import com.hy.springboot03.demo.dto.AccessTokenDTO;
import com.hy.springboot03.demo.dto.GithubUser;
import com.hy.springboot03.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
code by :hamish
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("github.client.id.}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String clientUrl;
    @GetMapping("/callback")
    public String callback(@RequestParam(name ="code")String code,
                           @RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
//      accessTokenDTO.setClient_id("9999c2056ce45a24a38d");
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_url(clientUrl);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
