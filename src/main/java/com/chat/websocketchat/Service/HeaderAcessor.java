package com.chat.websocketchat.Service;

import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public class HeaderAcessor extends SimpMessageHeaderAccessor {

    public HeaderAcessor(Message<?> message) {
        super(message);
    }
}
