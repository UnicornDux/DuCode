package com.edu.api.exception;

import lombok.Data;

@Data
public class ArgumentException extends RuntimeException{
    public String message;

    public ArgumentException(String message) {
        super(message);
    }

    public ArgumentException(String message, Throwable e) {
        super(message, e);
    }
}
