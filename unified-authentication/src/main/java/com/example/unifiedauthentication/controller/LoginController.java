package com.example.unifiedauthentication.controller;

import com.example.unifiedauthentication.entity.User;
import com.example.unifiedauthentication.login.LoginHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
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
        User user = new User<>();
        user.setUserId(12345);
        user.setUserName("老王");
        user.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setCurrentStoreTime(System.currentTimeMillis());
        LoginHelper.login(response, user, false);
        System.out.println("登陆成功");

        final Class<? extends User> aClass = user.getClass();
        final Field[] declaredFields = aClass.getDeclaredFields();
        final String name = declaredFields[0].getName();
        final String type = name.substring(0, 1).toUpperCase() + name.substring(1);


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
