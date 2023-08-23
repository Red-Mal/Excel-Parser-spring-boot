package com.example.excelparser.adapter;

import com.example.excelparser.entities.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The type Customer strategy.
 */
@Slf4j
public class CustomerStrategy implements ExcelProcessingStrategy<Customer>{
    @Override
    public List<Customer> processExcel(InputStream inputStream) throws IOException {
        log.info("Processing Excel file using CustomerStrategy");
        List<Customer> customers = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(inputStream);) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) continue; // Skip the header row

                customers.add(mapToCustomer(row));
            }

            return customers;
        } catch (IOException e) {
            // Handle file I/O errors
            log.error("Error reading Excel file: " + e.getMessage());
            throw e;
        }
    }

    private Customer mapToCustomer(Row row) {

        log.info("Mapping to Customer");
        Customer customer = new Customer();
        try {
            customer.setCustomerId((long) row.getCell(0).getNumericCellValue());
            customer.setName(row.getCell(1).getStringCellValue());
            customer.setEmail(row.getCell(2).getStringCellValue());
            customer.setDateDebut(row.getCell(3).getDateCellValue());
            customer.setDateFin(row.getCell(4).getDateCellValue());

        } catch (Exception e) {
            // Handle invalid data in the row
            log.error("Error processing row " + row.getRowNum() + ": " + e.getMessage());

        }
        return customer;
    }
}
