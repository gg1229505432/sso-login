package com.example.unifiedauthentication.helper;

import com.example.unifiedauthentication.conf.Conf;
import com.example.unifiedauthentication.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yenanren
 * @date 2022/3/6 0006
 * @Description 一个登录的应用层工具类, 包含登录, 登出, 检测是否登录三个方法
 */
public class LoginHelper {

    /**
     * 登录,如果redis中有数据也会覆盖掉
     *
     * @param response
     * @param user
     * @param ifRemember
     */
    public static void login(HttpServletResponse response, User user, boolean ifRemember) {
        SessionStoreRedisHelper.setex(user);

        String cookieValue = SessionAndCookieHelper.makeCookieValue(user);
        if (cookieValue == null) {
            throw new NullPointerException("-----------------cookieValue is null!");
        }
        CookieStoreBrowserHelper.set(response, Conf.COOKIE_KEY, cookieValue, ifRemember);
    }

    /**
     * 登出,把redis和cookie中的信息移除掉
     *
     * @param request
     * @param response
     */
    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        if (!checkLogin(request, response)) {
            throw new IllegalStateException("未登录,无法取消登录!");
        }
        String cookieValue = CookieStoreBrowserHelper.getValue(request);
        if (cookieValue == null) {
            throw new NullPointerException("-----------------cookieValue is null!");
        }

        String userId = SessionAndCookieHelper.parseCookieValueToUserId(cookieValue);
        if (userId == null || userId.trim().length() == 0) {
            throw new NullPointerException("-----------------userId is null!");
        }


        SessionStoreRedisHelper.remove(Integer.valueOf(userId));
        CookieStoreBrowserHelper.remove(request, response);
    }

    /**
     * 检查是否登录,检测版本号是否一致,如果都是,刷新redis时间,返回true,否则返回false
     *
     * @param request
     * @return
     */
    public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) {
        String cookieValue = CookieStoreBrowserHelper.getValue(request);
        if (cookieValue == null) {
            return false;
        }

        //如果cookies有信息
        String userId = SessionAndCookieHelper.parseCookieValueToUserId(cookieValue);
        User user = SessionStoreRedisHelper.get(Integer.valueOf(userId));
        //如果redis里面还有当前用户信息
        if (user != null) {
            String version = SessionAndCookieHelper.parseCookieValueToVersion(cookieValue);

            //如果版本相同,则更新redis的缓存时间,并且刷新当前时间
            if (version.equals(user.getVersion())) {
                if (System.currentTimeMillis() - user.getCurrentStoreTime() > SessionStoreRedisHelper.getRedisExpireMinite() / 2) {
                    user.setCurrentStoreTime(System.currentTimeMillis());
                    SessionStoreRedisHelper.setex(user);
                    return true;
                }
            }
        }

        //如果redis中没有用户信息 或者 cookie和redis中用户的版本号不一样(别人登陆过),则移除当前浏览器的cookie
        CookieStoreBrowserHelper.remove(request, response);
        return false;
    }

}
