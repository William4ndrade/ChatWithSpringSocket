package com.chat.websocketchat.Exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User Offline")
public class OfflineUserException extends RuntimeException {
    public OfflineUserException(String message) {
        super(message);
    }
}
