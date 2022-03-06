package com.example.unifiedauthentication.helper;

import com.example.unifiedauthentication.entity.User;

public class SessionAndCookieHelper {
    private static final String SESSION_KEY = "sso_yenanren_sessionKey";
    private static final String SESSION_SYMBOL = "#";
    private static final String COOKIE_SYMBOL = "_";

    public static String makeSessionKey(int userId) {
        return SESSION_KEY.concat(SESSION_SYMBOL).concat(String.valueOf(userId));
    }

    public static String makeCookieValue(User user) {
        return String.valueOf(user.getUserId()).concat(COOKIE_SYMBOL).concat(user.getVersion());
    }
}
