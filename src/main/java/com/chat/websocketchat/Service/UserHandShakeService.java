package com.chat.websocketchat.Service;

import com.sun.security.auth.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;


@Component
public class UserHandShakeService extends DefaultHandshakeHandler {


    private final Logger log = LoggerFactory.getLogger(UserHandShakeService.class);




    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        String id = UUID.randomUUID().toString();
        log.info("User with id '{}' is on", id);
        return new UserPrincipal(id);
    }
}
