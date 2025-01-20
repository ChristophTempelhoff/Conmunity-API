package at.conmunity.API.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such user")
public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super("Could not find user with UserUUID: " + id);
    }
}
