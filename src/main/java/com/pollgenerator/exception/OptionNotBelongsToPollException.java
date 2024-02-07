package com.pollgenerator.exception;

public class OptionNotBelongsToPollException extends RuntimeException {
    private final String message;

    public OptionNotBelongsToPollException(String message) {
        super(message);
        this.message = message;
    }
}