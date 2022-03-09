package com.example.chatplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ChatPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatPlatformApplication.class, args);
    }

}
