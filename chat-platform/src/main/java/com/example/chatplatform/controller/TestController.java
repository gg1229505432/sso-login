package com.example.chatplatform.controller;

import com.example.unifiedauthentication.conf.Conf;
import com.example.unifiedauthentication.entity.SsoUser;
import com.example.unifiedauthentication.helper.CookieStoreBrowserHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yenanren
 * @date 2022/3/9 0009
 * @Description
 */
@RestController
public class TestController {

    @RequestMapping("/")
    public String hello(HttpServletRequest request) {
        String value = CookieStoreBrowserHelper.getValue(request);
        return "8081端口:   " + value;
    }

    @RequestMapping("ha")
    public String ha(HttpServletRequest request) {
        final SsoUser ssoUser = (SsoUser) request.getAttribute(Conf.SSO_USER);
        return "8081端口:   " + ssoUser;
    }
}
