package com.example.excelparser.web;

import com.example.excelparser.entities.Customer;
import com.example.excelparser.service.ExcelParserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExcelParserController {

    private final ExcelParserService excelParserService;

 /*   @GetMapping("/readExcel")
    public List<Customer> readExcel(@RequestParam String filePath) throws IOException {
        return excelParserService.readExcelFile(filePath);
    }*/

    @GetMapping("/fromExcelToText")
    @ResponseStatus(HttpStatus.OK)
    public void fromExcelToText() throws IOException {
        log.info("Read Excel File and Save it in File");
        String excelFilePath="C:\\Projects\\JavaProjects\\excel-parser\\src\\main\\resources\\static\\customers-.xlsx";
        String txtFilePath="C:\\Projects\\JavaProjects\\excel-parser\\src\\main\\resources\\static\\customers-script.txt";

        excelParserService.fromExcelFileToText(excelFilePath,txtFilePath);
    }
}
