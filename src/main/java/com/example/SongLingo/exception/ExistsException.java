package com.example.SongLingo.exception;

public class ExistsException extends RuntimeException {
    public ExistsException(String message) {
        super(message);
    }
}

