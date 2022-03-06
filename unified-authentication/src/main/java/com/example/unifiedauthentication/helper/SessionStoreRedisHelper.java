package com.example.unifiedauthentication.helper;

import com.example.unifiedauthentication.entity.User;
import com.example.unifiedauthentication.utils.JedisUtil;

/**
 * @author yenanren
 * @date 2022/3/6 0006
 * @Description 用来存储到redis的应用层工具类
 */
public class SessionStoreRedisHelper {
    /**
     * 默认存在1440分钟,24小时
     */
    private static final int redisExpireMinite = 1440;

    /**
     * 构建好sessionKey并且存入redis
     *
     * @param user
     */
    public static boolean setex(User user) {
        String sessionKey = SessionAndCookieHelper.makeSessionKey(user.getUserId());
        return JedisUtil.setex(sessionKey, user, redisExpireMinite * 60);
    }

    /**
     * 构建好sessionKey并从redis中取出对象
     *
     * @param userId
     * @return
     */
    public static User get(int userId) {
        String sessionKey = SessionAndCookieHelper.makeSessionKey(userId);
        User user = (User) JedisUtil.getObjectValue(sessionKey);
        return user;
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
//        User<Student> studentUser = new User<>();
//        studentUser.setDefineUser(student);
//        studentUser.setUserId(123);
//        studentUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
//        setex(studentUser);
//
//        Object obj = JedisUtil.getObjectValue(SessionAndCookieHelper.makeSessionKey(studentUser.getUserId()));
//        System.out.println(obj.toString());
//    }
}
