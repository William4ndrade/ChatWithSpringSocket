package com.chat.websocketchat.Service;

import com.chat.websocketchat.Exceptions.OfflineUserException;
import com.chat.websocketchat.Models.DTOS.Message.MessageResponse;
import com.chat.websocketchat.Models.DTOS.Message.MessageResponseForUserDTO;
import com.chat.websocketchat.Models.Resources.Message;
import com.chat.websocketchat.Models.Resources.StateClassWebSocketChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class MessageServices {

    private Logger logger = LoggerFactory.getLogger(MessageServices.class);
    private final SimpMessageSendingOperations SocketOperations;

    @Autowired
    public MessageServices(SimpMessageSendingOperations socketOperations) {
        SocketOperations = socketOperations;
    }




    public MessageResponseForUserDTO NewMessage(Message msg, StateClassWebSocketChat state){
        if(state.IsUserOnline(msg.getUser())){
            if(msg.getText().length() > 0){
                logger.info("New Message: '{}'", msg);
                state.AddNewMessage(msg);
                SocketOperations.convertAndSend("/topic/message", new MessageResponse(200, state.getMessages(), ""));
                return new MessageResponseForUserDTO(200, "Successfully added", msg);

            }else{
                return new MessageResponseForUserDTO(400, "Blank text", null);
            }
        }

        return new MessageResponseForUserDTO(400, "User Offline", null);


    }

}
