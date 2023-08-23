package com.example.excelparser.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * The interface Excel processing strategy.
 *
 * @param <T> the type parameter
 */
public interface ExcelProcessingStrategy <T>{
    /**
     * Process excel list.
     *
     * @param inputStream the input stream
     * @return the list
     * @throws IOException the io exception
     */
    List<T> processExcel(InputStream inputStream) throws IOException;
}
