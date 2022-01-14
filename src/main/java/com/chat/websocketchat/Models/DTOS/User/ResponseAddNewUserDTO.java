package com.chat.websocketchat.Models.DTOS.User;

import com.chat.websocketchat.Models.Resources.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAddNewUserDTO {

    User me;
    String statusText;
    int code;



}
