package com.chat.websocketchat.Models.Resources;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @NotNull(message = "User não pode ser nulo")
    private User user;
    @NotNull(message = "texto não pode ser nulo")
    private String text;
    @NotNull
    private Date data;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return getUser().equals(message.getUser()) && getText().equals(message.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getText());
    }
}
