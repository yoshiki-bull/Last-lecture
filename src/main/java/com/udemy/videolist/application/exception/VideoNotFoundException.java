package com.udemy.videolist.application.exception;

public class VideoNotFoundException extends RuntimeException {

    public VideoNotFoundException() {
        super();
    }

    public VideoNotFoundException(String message, Throwable cause) {
        super(message,cause);
    }

    public VideoNotFoundException(String message) {
        super(message);
    }

    public VideoNotFoundException(Throwable cause) {
        super(cause);
    }
}
