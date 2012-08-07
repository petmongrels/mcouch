package mcouch.core.http;

public class MCouchException extends RuntimeException {
    public MCouchException(Throwable cause) {
        super(cause);
    }
}