package com.example.excelparser.exception;

/**
 * The type Export exception.
 */
public class ExportException extends RuntimeException {
    /**
     * Instantiates a new Export exception.
     *
     * @param message the message
     */
    public ExportException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Export exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ExportException(String message, Throwable cause) {
        super(message, cause);
    }

}
