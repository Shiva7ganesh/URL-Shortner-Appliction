package com.shivaganesh.Url_shortner_Application.exception;

public class DuplicateShortUrlException extends RuntimeException {
    public DuplicateShortUrlException(String message) {
        super(message);
    }
}