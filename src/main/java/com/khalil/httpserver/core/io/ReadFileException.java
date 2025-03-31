package com.khalil.httpserver.core.io;

public class ReadFileException extends Exception {

    public ReadFileException(String message) {
        super(message);
    }

    public ReadFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
