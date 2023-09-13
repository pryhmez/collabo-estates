package com.pryhmez.collabomain.properties;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PropertyExceptions {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class PropertyNotFoundException extends RuntimeException {
        public PropertyNotFoundException(String message) {
            super(message);
        }

//        public PropertyNotFoundException(String message, Throwable cause) {
//            super(message, cause);
//        }
    }

}

