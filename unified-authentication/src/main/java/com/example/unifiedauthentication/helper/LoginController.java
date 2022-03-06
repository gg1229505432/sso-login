package com.example.unifiedauthentication.helper;

import com.example.unifiedauthentication.entity.User;
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
        User user = new User<>();
        user.setUserId(12345);
        user.setUserName("老王");
        user.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setCurrentStoreTime(System.currentTimeMillis());
        LoginHelper.login(response, user, false);
        System.out.println("登陆成功");

    }

    @RequestMapping("checkLogin")
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) {
        User user = new User<>();
        user.setUserId(12345);
        user.setUserName("老王");
        user.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setCurrentStoreTime(System.currentTimeMillis());
        boolean b = LoginHelper.checkLogin(request, response);
        System.out.println("检测登录: " + b);
    }
}
