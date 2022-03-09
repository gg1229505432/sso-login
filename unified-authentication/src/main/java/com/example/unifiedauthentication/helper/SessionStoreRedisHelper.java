package com.example.unifiedauthentication.helper;

import com.example.unifiedauthentication.entity.SsoUser;
import com.example.unifiedauthentication.utils.JedisUtil;

/**
 * @author yenanren
 * @date 2022/3/6 0006
 * @Description 用来存储到redis的应用层工具类
 */
public class SessionStoreRedisHelper {
    /**
     * 默认存在1440分钟,24小时.也可以自己设置,只需要在
     */
    private static int redisExpireMinite = 1440;

    public static void setRedisExpireMinite(int redisExpireMinite) {
        if (redisExpireMinite < 30) {
            redisExpireMinite = 30;
        }
        SessionStoreRedisHelper.redisExpireMinite = redisExpireMinite;
    }

    public static int getRedisExpireMinite() {
        return redisExpireMinite;
    }


    /**
     * 构建好sessionKey并且存入redis
     *
     * @param ssoUser
     */
    public static boolean setex(SsoUser ssoUser) {
        String sessionKey = SessionAndCookieHelper.makeSessionKey(ssoUser.getUserId());
        return JedisUtil.setex(sessionKey, ssoUser, redisExpireMinite * 60);
    }

    /**
     * 构建好sessionKey并从redis中取出对象
     *
     * @param userId
     * @return
     */
    public static SsoUser get(int userId) {
        String sessionKey = SessionAndCookieHelper.makeSessionKey(userId);
        SsoUser ssoUser = (SsoUser) JedisUtil.getObjectValue(sessionKey);
        return ssoUser;
    }

    /**
     * 构建好sessionKey并从redis中移除对象
     *
     * @param userId
     * @return
     */
    public static boolean remove(int userId) {
        String sessionKey = SessionAndCookieHelper.makeSessionKey(userId);
        return JedisUtil.del(sessionKey);
    }


//    public static void main(String[] args) {
//        String address = "127.0.0.1";
//        int port = 6379;
//        JedisUtil.init(address, port);
//
//        //测试redis的存储和取出
//        Student student = new Student();
//        student.setStudentName("王毅");
//
//        SsoUser<Student> studentUser = new SsoUser<>();
//        studentUser.setDefineUser(student);
//        studentUser.setUserId(123);
//        studentUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
//        setex(studentUser);
//
//        Object obj = JedisUtil.getObjectValue(SessionAndCookieHelper.makeSessionKey(studentUser.getUserId()));
//        System.out.println(obj.toString());
//    }
}
