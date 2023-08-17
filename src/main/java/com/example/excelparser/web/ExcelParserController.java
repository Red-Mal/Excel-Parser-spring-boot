package com.example.excelparser.web;

import com.example.excelparser.entities.Customer;
import com.example.excelparser.service.ExcelParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExcelParserController {

    private final ExcelParserService excelParserService;

 /*   @GetMapping("/readExcel")
    public List<Customer> readExcel(@RequestParam String filePath) throws IOException {
        return excelParserService.readExcelFile(filePath);
    }*/

    @GetMapping("/readExcel")
    public List<Customer> readExcel() throws IOException {
        return excelParserService.readExcelFile("C:\\Projects\\JavaProjects\\excel-parser\\src\\main\\resources\\static\\customers.xlsx");
    }

    @GetMapping("/fromExcelToText")
    public void fromExcelToText() throws IOException {
       excelParserService.fromExcelFileToText("C:\\Projects\\JavaProjects\\excel-parser\\src\\main\\resources\\static\\customers.xlsx","C:\\Projects\\JavaProjects\\excel-parser\\src\\main\\resources\\static\\customers-script.txt");
    }
}
