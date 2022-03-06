package com.example.unifiedauthentication.conf;


/**
 * 常量名
 */
public class Conf {
    /**
     * 做Session连接用的,例如:sso_yenanren_sessionKey#2,防止重复,更好搜索
     */
    public static final String SESSION_KEY = "sso_yenanren_sessionKey";
    /**
     * 做Session连接用的,例如:sso_yenanren_sessionKey#2
     */
    public static final String SESSION_SYMBOL = "#";
    /**
     * 做Cookie连接用的,例如:2_uuid随机生成的字符串作为版本号
     */
    public static final String COOKIE_SYMBOL = "_";
}
