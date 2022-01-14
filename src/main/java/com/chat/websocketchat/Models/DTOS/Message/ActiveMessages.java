package com.chat.websocketchat.Models.DTOS.Message;

import com.chat.websocketchat.Models.DTOS.User.ActiveUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveMessages {

    private String text;
    private ActiveUser user;
    private Date data;


}
