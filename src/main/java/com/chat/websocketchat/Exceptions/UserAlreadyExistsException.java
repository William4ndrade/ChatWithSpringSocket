package com.chat.websocketchat.Exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User Already Exists")
public class UserAlreadyExistsException extends  RuntimeException{

}
