package com.example.excelparser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * The type Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle excel processing exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(ExcelProcessingException.class)
    public ResponseEntity<String> handleExcelProcessingException(ExcelProcessingException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred during Excel processing: " + ex.getMessage());
    }

    /**
     * Handle export exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(ExportException.class)
    public ResponseEntity<String> handleExportException(ExportException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred during export: " + ex.getMessage());
    }
}
