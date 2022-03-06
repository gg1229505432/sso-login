package com.example.unifiedauthentication.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 提供泛型,开发者自己定义user
 * @param <T>
 */
@Data
public class User<T> implements Serializable {
    private int userId;
    private String userName;
    private String version;
    private long currentStoreTime;
    /**
     * 开发者可以自己定义user
     */
    private T defineUser;
}
