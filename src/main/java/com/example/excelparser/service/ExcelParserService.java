package com.example.excelparser.service;

import com.example.excelparser.entities.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class ExcelParserService {
    public List<Customer> readExcelFile(String filePath) throws IOException {
        List<Customer> customers = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

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

                } catch (Exception e) {
                    // Handle invalid data in the row
                    log.error("Error processing row " + row.getRowNum() + ": " + e.getMessage());
                }



            }
        }
        catch (IOException e) {
            // Handle file I/O errors
            log.error("Error reading Excel file: " + e.getMessage());
            throw e;
        }

        return customers;
    }
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

            throw e;
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
        }
    }


}