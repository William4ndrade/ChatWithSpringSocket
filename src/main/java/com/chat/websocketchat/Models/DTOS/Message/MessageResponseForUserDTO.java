package com.chat.websocketchat.Models.DTOS.Message;

import com.chat.websocketchat.Models.Resources.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseForUserDTO {

    int code;
    String statusText;
    Message message;
}
