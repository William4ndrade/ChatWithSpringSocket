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
public class User {

    @NotNull(message = "Username não pode ser nulo")
    private String username;
    @NotNull(message = "id não pode ser nulo")
    private String id;
    @NotNull(message = "data não pode ser nulo")
    private Date date;
    @NotNull(message = "thumbnail não pode ser nulo")
    private String thumbnail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
