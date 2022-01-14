package com.chat.websocketchat.Models.Resources;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@NoArgsConstructor
@Data
public class StateClassWebSocketChat {

    private final Set<User> OnlineUsers = new HashSet<>();
    private final Queue<Message> Messages = new LinkedList<Message>();


    public boolean IsUserOnline(User user){
        return OnlineUsers.contains(user);
    }

    public boolean deleteMessageByUser(User user){
        return Messages.removeIf((e) -> e.getUser().getUsername().equals(user.getUsername()));
    }

    public boolean AddNewMessage(Message message){
        if(Messages.size() == 200){
            Messages.poll();
            return Messages.add(message);
        }
       return Messages.add(message);


    }



    public Map<String, Integer> StateNumbers(){
        Map<String,Integer> Numbers =  new HashMap<>();
        Numbers.put("Users-Online", OnlineUsers.size());
        Numbers.put("AllMessages", Messages.size());
        return Numbers;
    }


}
