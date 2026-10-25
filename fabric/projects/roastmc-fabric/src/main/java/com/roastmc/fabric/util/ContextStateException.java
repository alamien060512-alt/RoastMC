package com.roastmc.fabric.util;

/**
 * @author Mgazul
 * @date 2026/3/28 03:41
 */
public class ContextStateException extends RuntimeException {

    public ContextStateException() {
        super();
    }

    public ContextStateException(String message) {
        super(message);
    }

    public ContextStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContextStateException(Throwable cause) {
        super(cause);
    }
}
