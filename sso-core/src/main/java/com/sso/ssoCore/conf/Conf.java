package com.sso.ssoCore.conf;


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
    /**
     * 在Filter用的,用来getInitParameter中的值,进行下一步拦截操作的
     */
    public static final String SSO_SERVER = "ssoServer";
    /**
     * 在Filter用的,用来getInitParameter中的值,进行下一步拦截操作的
     */
    public static final String SSO_EXCLUDED_PATHS = "excludedPaths";
    /**
     * 用于返回login页面
     */
    public static final String SSO_LOGIN = "/login";
    /**
     * 放置cookie的key,value中存储的是所有的用户的信息(不安全,但此处是为了减少redis并发访问)
     */
    public static final String SSO_USER = "ssoUser";


}
