package com.chat.websocketchat.Models.DTOS.Message;

import com.chat.websocketchat.Models.DTOS.Message.ActiveMessages;
import com.chat.websocketchat.Models.DTOS.User.ActiveUser;
import com.chat.websocketchat.Models.Resources.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class MessageResponse {

    private int code;
    private Queue<ActiveMessages> messagesActive;
    private String statustext;


    public MessageResponse(int code, Queue<Message> messages, String statustext) {
        this.code = code;
        this.messagesActive = messages.stream().map(e -> new ActiveMessages(e.getText(), new ActiveUser(e.getUser().getUsername(), e.getUser().getThumbnail()), e.getData())).collect(Collectors.toCollection(LinkedList::new));
        this.statustext = statustext;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Queue<ActiveMessages> getMessages() {
        return messagesActive;
    }

    public void setMessages(Queue<ActiveMessages> messages) {
        this.messagesActive = messages;
    }

    public String getStatustext() {
        return statustext;
    }

    public void setStatustext(String statustext) {
        this.statustext = statustext;
    }
}
