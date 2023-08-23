package com.example.excelparser.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * The type Excel processor.
 *
 * @param <T> the type parameter
 */
public class ExcelProcessor<T> {
    private  ExcelProcessingStrategy<T> strategy;

    /**
     * Sets strategy.
     *
     * @param strategy the strategy
     */
    public void setStrategy(ExcelProcessingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    /**
     * Process excel file list.
     *
     * @param inputStream the input stream
     * @return the list
     * @throws IOException the io exception
     */
    public List<T> processExcelFile(InputStream inputStream) throws IOException{
        if(strategy==null)
            throw new IllegalStateException("No strategy set");

        return strategy.processExcel(inputStream);
    }
}
