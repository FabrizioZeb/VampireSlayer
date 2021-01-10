package org.ucm.tp1.exceptions;

public class CommandParseException extends GameException {

    public CommandParseException(){
        super();
    }

    public CommandParseException(String message) {
        super(message);
    }

    public CommandParseException(String message, Throwable cause) {
        super(message,cause);
    }

    public CommandParseException(Throwable cause) {
        super(cause);
    }

/*    Exception (String message, Throwable cause, boolean enableSupression, boolean writeableStackTrace){
        super(message, cause,enableSupression,writeableStackTrace);
    }*/
}
