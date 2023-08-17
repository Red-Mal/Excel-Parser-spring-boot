package com.example.excelparser;

import com.example.excelparser.entities.Customer;
import com.example.excelparser.service.ExcelParserService;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExcelParserServiceTest {
   // @Mock
    private FileInputStream fileInputStream;

   // @Mock
    private Workbook workbook;

    private ExcelParserService excelParserService;

   // @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        excelParserService = new ExcelParserService();
    }

   // @Test
    void testReadExcelFile() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("C:\\Projects\\JavaProjects\\excel-parser\\src\\main\\resources\\static\\customers.xlsx"); // Provide a test Excel file
        when(fileInputStream.read()).thenReturn(-1); // Simulate end of stream
        when(workbook.getSheetAt(0)).thenReturn(mock(Sheet.class));
        when(workbook.iterator()).thenReturn(mock(Iterator.class));
       // when(workbook.close()).thenReturn(null);

        List<Customer> customers = excelParserService.readExcelFile("test.xlsx");

        assertNotNull(customers);
        assertEquals(3, customers.size()); // Assuming there are 3 rows in the test.xlsx file
        // Add more assertions for expected customer data
    }

   // @Test
    void testReadExcelFileIOException() throws IOException {
        when(fileInputStream.read()).thenThrow(new IOException("Mocked IOException"));

        assertThrows(IOException.class, () -> excelParserService.readExcelFile("test.xlsx"));
    }

   // @Test
    void testReadExcelFileInvalidCellType() throws IOException {
        when(fileInputStream.read()).thenReturn(-1); // Simulate end of stream
        Sheet sheet = mock(Sheet.class);
        when(workbook.getSheetAt(0)).thenReturn(sheet);
        Row row = mock(Row.class);
        when(sheet.iterator()).thenReturn(new Iterator<Row>() {
            @Override
            public boolean hasNext() {
                return true;
            }
            @Override
            public Row next() {
                return row;
            }
        });
        Cell cell = mock(Cell.class);
        when(row.getCell(0)).thenReturn(cell);
        when(cell.getCellType()).thenReturn(CellType.STRING);
       // when(workbook.close()).thenReturn(null);

        List<Customer> customers = excelParserService.readExcelFile("C:\\Projects\\JavaProjects\\excel-parser\\src\\main\\resources\\static\\customers-empty-test.xlsx");

        assertNotNull(customers);
        assertEquals(0, customers.size()); // Since the first cell has an invalid type
    }
}
