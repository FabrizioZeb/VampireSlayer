package org.ucm.tp1.exceptions;

public class DraculaIsAliveException extends CommandExecuteException{
    public DraculaIsAliveException() {
    }

    public DraculaIsAliveException(String message) {
        super(message);
    }

    public DraculaIsAliveException(String message, Throwable cause) {
        super(message, cause);
    }

    public DraculaIsAliveException(Throwable cause) {
        super(cause);
    }
}
