package com.example.excelparser.service;

import com.example.excelparser.entities.Customer;
import com.example.excelparser.exception.ExcelProcessingException;
import com.example.excelparser.exception.ExportException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Iterator;


/**
 * The type Excel parser service.
 */
@Service
@Slf4j
public class ExcelParserService {

    /**
     * From excel file to text.
     *
     * @param excelFilePath the excel file path
     * @param txtFilePath   the txt file path
     * @throws IOException the io exception
     */
    public void fromExcelFileToText(String excelFilePath, String txtFilePath) throws IOException {

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = WorkbookFactory.create(fis);
             FileWriter writer = new FileWriter(txtFilePath)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    continue; // Skip the header row
                }

                writeExcelRowToFile(writer,row);
            }
        } catch (IOException e) {
            // Handle file I/O errors
            log.error("Error reading Excel file: " + e.getMessage());
            throw new ExportException("Error exporting Excel file: " + e.getMessage(), e);
        }
    }

    /**
     * Write excel row to file.
     *
     * @param writer the writer
     * @param row    the row
     */
    public void writeExcelRowToFile(FileWriter writer, Row row){
        try {
            // Export to TXT
            String line = String.format("INSERT INTO customer VALUES (%d,'%s','%s','%s','%s');%n",
                    (long) row.getCell(0).getNumericCellValue(),
                    row.getCell(1).getStringCellValue(),
                    row.getCell(2).getStringCellValue(),
                    row.getCell(3).getDateCellValue(),
                    row.getCell(4).getDateCellValue());
            writer.write(line);
        } catch (Exception e) {
            // Handle invalid data in the row
            log.error("Error processing row " + row.getRowNum() + ": " + e.getMessage());
            throw new ExcelProcessingException("Error reading Excel file: " + e.getMessage(), e);
        }
    }

    /*
    public void fromExcelFileToText(String excelFilePath, String txtFilePath) throws IOException {
        List<Customer> customers = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = WorkbookFactory.create(fis);
             FileWriter writer = new FileWriter(txtFilePath)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    continue; // Skip the header row
                }

                try {
                    Customer customer = new Customer();
                    customer.setCustomerId((long) row.getCell(0).getNumericCellValue());
                    customer.setName(row.getCell(1).getStringCellValue());
                    customer.setEmail(row.getCell(2).getStringCellValue());
                    customer.setDateDebut(row.getCell(3).getDateCellValue());
                    customer.setDateFin(row.getCell(4).getDateCellValue());

                    customers.add(customer);

                    // Export to TXT
                    String line = String.format("%d;%s;%s;%s;%s%n",
                            customer.getCustomerId(),
                            customer.getName(),
                            customer.getEmail(),
                            customer.getDateDebut(),
                            customer.getDateFin());
                    writer.write(line);
                } catch (Exception e) {
                    // Handle invalid data in the row
                    System.err.println("Error processing row " + row.getRowNum() + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            // Handle file I/O errors
            System.err.println("Error reading Excel file: " + e.getMessage());
            throw e;
        }
    }
*/


}