package at.conmunity.API.Exception.Handlers;

import at.conmunity.API.Exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class MemberNotFoundAdvice {
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void UserNotFoundAdvice(MemberNotFoundException ex){
        throw new RuntimeException(ex);
    }
}
