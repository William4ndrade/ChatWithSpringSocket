package com.chat.websocketchat.Models.DTOS.User;

import com.chat.websocketchat.Models.DTOS.User.ActiveUser;
import com.chat.websocketchat.Models.Resources.User;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;


@NoArgsConstructor
public class AllUsersOnline {

    int code;
    private  Set<ActiveUser> usersOnline;




    public AllUsersOnline(int code, Set<User> users) {
        this.code = code;
        this.usersOnline = users.stream().map(e -> new ActiveUser(e.getUsername(), e.getThumbnail())).collect(Collectors.toSet());;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public Set<ActiveUser> getUsersOnline() {
        return usersOnline;
    }
    public void setUsersOnline(Set<ActiveUser> usersOnline) {
        this.usersOnline = usersOnline;
    }


}
