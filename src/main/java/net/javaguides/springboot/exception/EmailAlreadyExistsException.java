package net.javaguides.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST) //no need to use this annotation if we are using global handler
public class EmailAlreadyExistsException extends RuntimeException{
private String message;
public EmailAlreadyExistsException(String message){
    super(message);
}
}
