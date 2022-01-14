package com.chat.websocketchat.Controllers;


import com.chat.websocketchat.Models.DTOS.Message.MessageResponseForUserDTO;
import com.chat.websocketchat.Models.DTOS.User.AllUsersOnline;
import com.chat.websocketchat.Models.DTOS.Message.MessageResponse;
import com.chat.websocketchat.Models.DTOS.User.ResponseAddNewUserDTO;
import com.chat.websocketchat.Models.Resources.Message;
import com.chat.websocketchat.Models.Resources.StateClassWebSocketChat;
import com.chat.websocketchat.Models.Resources.User;
import com.chat.websocketchat.Service.MessageServices;
import com.chat.websocketchat.Service.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.security.Principal;
import java.util.*;

@Controller

public class WsController {


    private final SimpMessageSendingOperations SocketOperations;
    private final StateClassWebSocketChat state = new StateClassWebSocketChat();
    private final Logger logger = LoggerFactory.getLogger(WsController.class);
    private final UserServices userServices;
    private final MessageServices messageServices;




    @Autowired
    public WsController(SimpMessageSendingOperations socketOperations, UserServices userServices, MessageServices messageServices, SimpUserRegistry userRegistry) {
        SocketOperations = socketOperations;
        this.userServices = userServices;
        this.messageServices = messageServices;
    }


    @MessageMapping("/newmessage-private")
    @SendToUser("/topic/messageToUser")
    public MessageResponseForUserDTO CreateNewMessage(@Payload Message message, Principal principal){
        return messageServices.NewMessage(message, state);
    }



    @MessageMapping("/newuser-private")
    @SendToUser("/topic/privateUser")
    public ResponseAddNewUserDTO JoinNewUser(@Payload User user, Principal principal, SimpMessageHeaderAccessor simpMessageHeaderAccessor){
        return userServices.JoinNewUser(user, state, simpMessageHeaderAccessor);
    }


    @EventListener
    public void HandleSubscribeEvent(SessionSubscribeEvent session){
        String Destination = (String) session.getMessage().getHeaders().get("simpDestination");
        assert Destination != null;
        if(Destination.startsWith("/topic/users")){
            System.out.println("entrou aqui");
            SocketOperations.convertAndSend("/topic/users", new AllUsersOnline(200,state.getOnlineUsers()));
        }else if(Destination.startsWith("/topic/message")){
            SocketOperations.convertAndSend("/topic/message", new MessageResponse(200, state.getMessages(), "All Messages") );
        }
    }



    @EventListener
    public void  SessionDisconnect(SessionDisconnectEvent event){
        logger.info("Disconnect '{}'", event);
        StompHeaderAccessor wrap =  StompHeaderAccessor.wrap(event.getMessage());
     if(Objects.requireNonNull(wrap.getSessionAttributes()).containsKey("UserSession")){
            User actual = (User) wrap.getSessionAttributes().get("UserSession");
            if(state.getOnlineUsers().remove(actual)){
                 if(state.deleteMessageByUser(actual)){
                     logger.info("Mensagens do user '{}' foram apagadas ", actual);
                     SocketOperations.convertAndSend("/topic/message", new MessageResponse(200, state.getMessages(),"Mensagens apagas por disconnect" ));
                 }
                SocketOperations.convertAndSend("/topic/users", new AllUsersOnline(200, state.getOnlineUsers()));
            }



        }



    }


}
