package com.example.excelparser.exception;

/**
 * The type Excel processing exception.
 */
public class ExcelProcessingException extends RuntimeException {

    /**
     * Instantiates a new Excel processing exception.
     *
     * @param message the message
     */
    public ExcelProcessingException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Excel processing exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ExcelProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
