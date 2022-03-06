package com.example.unifiedauthentication.helper;

import com.example.unifiedauthentication.conf.Conf;
import com.example.unifiedauthentication.entity.User;

public class SessionAndCookieHelper {

    public static String makeSessionKey(int userId) {
        return Conf.SESSION_KEY.concat(Conf.SESSION_SYMBOL).concat(String.valueOf(userId));
    }

    public static String makeCookieValue(User user) {
        return String.valueOf(user.getUserId()).concat(Conf.COOKIE_SYMBOL).concat(user.getVersion());
    }

    public static String parseCookieValueToUserId(String cookieValue) {
        if (cookieValue != null && cookieValue.indexOf(Conf.COOKIE_SYMBOL) > -1) {
            String[] userInfo = cookieValue.split(Conf.COOKIE_SYMBOL);
            if (userInfo.length == 2 && userInfo[0] != null && userInfo[0].length() > 0) {
                return userInfo[0];
            }
        }
        return null;
    }

    public static String parseCookieValueToVersion(String cookieValue) {
        if (cookieValue != null && cookieValue.indexOf(Conf.COOKIE_SYMBOL) > -1) {
            String[] userInfo = cookieValue.split(Conf.COOKIE_SYMBOL);
            if (userInfo.length == 2 && userInfo[1] != null && userInfo[1].length() > 0) {
                return userInfo[1];
            }
        }
        return null;
    }


}
