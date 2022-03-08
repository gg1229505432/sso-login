package com.example.unifiedauthentication.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用来测试的,无实际意义,可以传到User<T>泛型中
 */
@Data
public class Student implements Serializable {
    public static final long serialVersionUID = 42L;
    private String studentName;
    private int sex;
}
