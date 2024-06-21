package io.axtrading.botfallback.exceptions;

public class BotFallbackException extends Exception {
    int errorCode;

    public BotFallbackException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
