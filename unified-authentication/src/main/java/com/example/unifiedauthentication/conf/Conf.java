package com.example.unifiedauthentication.conf;


/**
 * 常量名
 */
public class Conf {
    /**
     * redis存储的key
     */
    public static final String SESSION_KEY = "sso_yenanren_sessionKey";
    /**
     * Cookie存储的key
     */
    public static final String COOKIE_KEY = "sso_yenanren_cookieKey";

    /**
     * 做Session连接用的,例如:sso_yenanren_sessionKey#2
     */
    public static final String SESSION_SYMBOL = "#";
    /**
     * 做Cookie连接用的,例如:2_uuid随机生成的字符串作为版本号
     */
    public static final String COOKIE_SYMBOL = "_";
}
