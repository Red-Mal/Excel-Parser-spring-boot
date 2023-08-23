package com.example.excelparser.adapter;

import com.example.excelparser.exception.ExcelProcessingException;
import com.example.excelparser.exception.ExportException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class TextFileStrategy implements ExcelProcessingStrategy<String>{
    private String txtFilePath;

    public TextFileStrategy(String txtFilePath) {
        this.txtFilePath = txtFilePath;
    }


    @Override
    public List<String> processExcel(InputStream inputStream) throws IOException {

        log.info("Processing Excel file using TextFileStrategy");
        List<String> stringList= new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(inputStream);
             FileWriter writer = new FileWriter(txtFilePath)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    continue; // Skip the header row
                }

                stringList.add(writeExcelRowToFile(writer, row));

            }

        } catch (IOException e) {
            // Handle file I/O errors
            log.error("Error reading Excel file: " + e.getMessage());
            throw new ExportException("Error exporting Excel file: " + e.getMessage(), e);
        }
        return stringList;


    }

    public String writeExcelRowToFile(FileWriter writer, Row row){
        try {
            // Export to TXT
            String line = String.format("INSERT INTO customer VALUES (%d,'%s','%s','%s','%s');%n",
                    (long) row.getCell(0).getNumericCellValue(),
                    row.getCell(1).getStringCellValue(),
                    row.getCell(2).getStringCellValue(),
                    row.getCell(3).getDateCellValue(),
                    row.getCell(4).getDateCellValue());
            writer.write(line);
            return line;
        } catch (Exception e) {
            // Handle invalid data in the row
            log.error("Error processing row " + row.getRowNum() + ": " + e.getMessage());
            throw new ExcelProcessingException("Error reading Excel file: " + e.getMessage(), e);
        }
    }
}
