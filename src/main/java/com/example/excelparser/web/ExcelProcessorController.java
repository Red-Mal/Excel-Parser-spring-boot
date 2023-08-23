package com.example.excelparser.web;

import com.example.excelparser.adapter.CustomerStrategy;
import com.example.excelparser.adapter.ExcelProcessingStrategy;
import com.example.excelparser.adapter.ExcelProcessor;
import com.example.excelparser.adapter.TextFileStrategy;
import com.example.excelparser.entities.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * The type Excel processor controller.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ExcelProcessorController {


    /**
     * Process excel file.
     *
     * @param file         the file
     * @param strategyName the strategy name
     * @throws IOException the io exception
     */
    @PostMapping("/upload-excel-v1")
    @ResponseStatus(HttpStatus.OK)
    public void processExcelFile(
            @RequestParam("file")MultipartFile file,
            @RequestParam String strategyName) throws IOException {

        try (InputStream excelInputStream = file.getInputStream()) {

            switch (strategyName.toLowerCase()) {
                case "customer":
                    ExcelProcessor<Customer> excelProcessor = new ExcelProcessor<>();
                    excelProcessor.setStrategy(new CustomerStrategy());
                    List<Customer> customers = excelProcessor.processExcelFile(excelInputStream);
                    // Do something with the list of customers
                    log.info(customers.toString());
                    break;

                case "textfile":
                    ExcelProcessor<String> excelProcessor1 = new ExcelProcessor<>();
                    String txtFilePath="C:\\Projects\\JavaProjects\\excel-parser\\src\\main\\resources\\static\\customers-script.txt";
                    excelProcessor1.setStrategy(new TextFileStrategy(txtFilePath));
                    List<String> textContent = excelProcessor1.processExcelFile(excelInputStream);
                    // Do something with the text content
                    log.info(textContent.toString());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid strategy: " + strategyName);
            }
        }


    }

}
