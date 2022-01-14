package com.chat.websocketchat.Service;

import com.chat.websocketchat.Exceptions.UserAlreadyExistsException;
import com.chat.websocketchat.Models.DTOS.User.ActiveUser;
import com.chat.websocketchat.Models.DTOS.User.AllUsersOnline;
import com.chat.websocketchat.Models.DTOS.User.ResponseAddNewUserDTO;
import com.chat.websocketchat.Models.Resources.StateClassWebSocketChat;
import com.chat.websocketchat.Models.Resources.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServices {


    private final Logger logger = LoggerFactory.getLogger(UserServices.class);
    private final SimpMessageSendingOperations SocketOperations;



    @Autowired
    public UserServices(SimpMessageSendingOperations socketOperations) {
        SocketOperations = socketOperations;

    }

    public ResponseAddNewUserDTO JoinNewUser(User user, StateClassWebSocketChat state, SimpMessageHeaderAccessor headerAccessor){
        logger.info("Trying Login: '{}'", user);
        if(state.getOnlineUsers().size() >= 30){
            logger.warn("Full list Exception for User: '{}'", user );
            return new ResponseAddNewUserDTO(null, "Full list" , 400);
        }
        boolean IsUserAdd = state.getOnlineUsers().add(user);
        if(IsUserAdd){
            logger.info("User '{}' in ", user);
            Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("UserSession", user);
            SocketOperations.convertAndSend("/topic/users", new AllUsersOnline(200, state.getOnlineUsers()));
            return new ResponseAddNewUserDTO(user, "successfully added", 200);
        }else{
            logger.warn("Already Exists User: '{}'", user );
            return new ResponseAddNewUserDTO(null, "User Already Exists", 400);
        }


    }







}
