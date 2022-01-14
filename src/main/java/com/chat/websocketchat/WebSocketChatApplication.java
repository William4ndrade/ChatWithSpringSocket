package com.chat.websocketchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class WebSocketChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketChatApplication.class, args);
    }

}
