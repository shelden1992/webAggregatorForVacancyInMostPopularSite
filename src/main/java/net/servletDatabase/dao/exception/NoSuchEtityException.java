package net.servletDatabase.dao.exception;

public class NoSuchEtityException extends DaoBusinesException {
    public NoSuchEtityException(String message) {
        super(message);
    }

    public NoSuchEtityException(String message, Throwable cause) {
        super(message, cause);
    }
}
