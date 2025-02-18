package com.a00326153.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EntityAlreadyExistsException extends RuntimeException{

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

}
