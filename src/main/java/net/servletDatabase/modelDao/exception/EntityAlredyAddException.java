package net.servletDatabase.modelDao.exception;

public class EntityAlredyAddException extends DaoBusinesException{
    public EntityAlredyAddException(String message) {
        super(message);
    }

    public EntityAlredyAddException(String message, Throwable cause) {
        super(message, cause);
    }
}
