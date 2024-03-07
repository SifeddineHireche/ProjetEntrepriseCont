package com.entreprise.app.rest.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntrepriseNotFoundException extends RuntimeException {
    public EntrepriseNotFoundException(String message) {
        super(message);
    }
}