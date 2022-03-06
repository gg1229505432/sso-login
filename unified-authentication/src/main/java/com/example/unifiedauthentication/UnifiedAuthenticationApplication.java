package com.example.unifiedauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class UnifiedAuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnifiedAuthenticationApplication.class, args);
    }

}
