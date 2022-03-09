package com.example.unifiedauthentication.controller;

import com.sso.ssoCore.entity.SsoUser;
import com.sso.ssoCore.login.LoginHelper;
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
public class LoginController {

    @RequestMapping("login")
    public String login(HttpServletResponse response) {
        SsoUser ssoUser = new SsoUser<>();
        ssoUser.setUserId(12345);
        ssoUser.setUserName("老王");
        ssoUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        ssoUser.setCurrentStoreTime(System.currentTimeMillis());
        LoginHelper.login(response, ssoUser, true);
        System.out.println("登陆成功");
        return "登录成功";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        LoginHelper.logout(request, response);
        System.out.println("登出成功");
        return "登录成功";

    }

    @RequestMapping("checkLogin")
    public String checkLogin(HttpServletRequest request, HttpServletResponse response) {
        boolean b = LoginHelper.checkLogin(request, response);
        System.out.println("检测登录: " + b);
        return "检测登录: " + b;

    }

}
