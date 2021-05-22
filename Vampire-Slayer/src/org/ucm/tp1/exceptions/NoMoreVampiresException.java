package org.ucm.tp1.exceptions;

public class NoMoreVampiresException extends CommandExecuteException{
    public NoMoreVampiresException() {
    }

    public NoMoreVampiresException(String message) {
        super(message);
    }

    public NoMoreVampiresException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoreVampiresException(Throwable cause) {
        super(cause);
    }
}
