package com.example.unifiedauthentication.controller;

import com.example.unifiedauthentication.entity.SsoUser;
import com.example.unifiedauthentication.login.LoginHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author yenanren
 * @date 2022/3/7 0007
 * @Description
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @RequestMapping("login")
    public void login(HttpServletResponse response) {
        SsoUser ssoUser = new SsoUser<>();
        ssoUser.setUserId(12345);
        ssoUser.setUserName("老王");
        ssoUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        ssoUser.setCurrentStoreTime(System.currentTimeMillis());
        LoginHelper.login(response, ssoUser, false);
        System.out.println("登陆成功");


    }

    @RequestMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        LoginHelper.logout(request, response);
        System.out.println("登出成功");

    }

    @RequestMapping("checkLogin")
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) {
        boolean b = LoginHelper.checkLogin(request, response);
        System.out.println("检测登录: " + b);
    }

}
