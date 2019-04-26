package net.servletDatabase.model.exception;

public class DaoBusinesException extends DaoException {
    public DaoBusinesException(String message) {
        super(message);
    }

    public DaoBusinesException(String message, Throwable cause) {
        super(message, cause);
    }
}
