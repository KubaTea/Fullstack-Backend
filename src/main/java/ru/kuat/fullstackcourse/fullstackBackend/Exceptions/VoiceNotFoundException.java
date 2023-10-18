package ru.kuat.fullstackcourse.fullstackBackend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VoiceNotFoundException extends RuntimeException{
    public VoiceNotFoundException(String message){
        super(message);
    }
}
