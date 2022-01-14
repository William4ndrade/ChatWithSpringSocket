package com.chat.websocketchat.Exceptions;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Full User List, Try again later")
public class FullUserListException extends RuntimeException {

    public FullUserListException(String message) {
        super(message);
    }
}
