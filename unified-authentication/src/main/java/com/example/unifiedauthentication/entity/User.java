package com.example.unifiedauthentication.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private int userId;
    private String userName;
    private String version;
}
