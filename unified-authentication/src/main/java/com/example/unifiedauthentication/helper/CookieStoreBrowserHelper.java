package com.example.unifiedauthentication.helper;


import com.example.unifiedauthentication.conf.Conf;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yenanren
 * @date 2022/3/6 0006
 * @Description 用来存储到浏览器的应用层工具类
 */
public class CookieStoreBrowserHelper {

    private static final int MAX_EXPIRE = 4320;//默认三天的存在时间
    private static final String DEFAULT_PATH = "/";

    public static void set(HttpServletResponse response, String key, String value, boolean ifRemember) {
        int expire = ifRemember ? MAX_EXPIRE * 60 : -1;
        set(response, key, value, null, DEFAULT_PATH, expire);
    }

    public static void set(HttpServletResponse response, String key, String value, String domain, boolean ifRemember) {
        int expire = ifRemember ? MAX_EXPIRE * 60 : -1;
        set(response, key, value, domain, DEFAULT_PATH, expire);
    }

    private static void set(HttpServletResponse response, String key, String value, String domain, String path, int expire) {
        Cookie cookie = new Cookie(key, value);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(expire);//-1为关闭浏览器即消失,0为立马删除,正值为存在时间
        cookie.setHttpOnly(true);//防止js读取cookie信息,防止xss攻击
        response.addCookie(cookie);
    }

    public static String getValue(HttpServletRequest request) {
        String value = getValue(request, Conf.COOKIE_KEY);
        return value;
    }

    public static String getValue(HttpServletRequest request, String key) {
        Cookie cookie = get(request, key);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    public static Cookie get(HttpServletRequest request) {
        Cookie cookie = get(request, Conf.COOKIE_KEY);
        return cookie;
    }

    public static Cookie get(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static boolean remove(HttpServletRequest request, HttpServletResponse response) {
        boolean remove = remove(request, response, Conf.COOKIE_KEY);
        return remove;
    }

    public static boolean remove(HttpServletRequest request, HttpServletResponse response, String key) {
        boolean result = false;
        Cookie cookie = get(request, key);
        if (cookie != null) {
            set(response, key, null, null, null, 0);
            result = true;
        }
        return result;
    }


}
