package genericUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static String getExcelData(String sheetName, int rowNumber, int columnNumber) {
		String data = "";
		try {
			// Create a FileInputStream object for the Excel file
			FileInputStream file = new FileInputStream(new File("./src/test/resources/demo_data.xlsx"));

			// Create a XSSFWorkbook object for the Excel file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get the sheet with the specified name
			Sheet sheet = workbook.getSheet(sheetName);

			// Get the row with the specified row number
			Row row = sheet.getRow(rowNumber);

			// Get the cell with the specified column number
			Cell cell = row.getCell(columnNumber);

			// Get the value of the cell
			data = cell.getStringCellValue();

			// Close the workbook and the file input stream
			workbook.close();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}
	
	public static Object[][] getAllExcelData(String sheetName) throws EncryptedDocumentException, IOException {
	   Object[][] data=null;
	   FileInputStream fis = new FileInputStream("./src/test/resources/demo_data.xlsx");
       Workbook workbook=WorkbookFactory.create(fis);
       
       Sheet sheet = workbook.getSheet(sheetName);
       
       int lastRow = sheet.getLastRowNum();
       for (int i = 0; i < lastRow; i++) {
    	   int lastCell = sheet.getRow(i).getLastCellNum();
    	   data = new Object[lastRow][lastCell];
    	   for (int j = 0; j < lastCell; j++) {
    		   data[i][j]=sheet.getRow(i).getCell(j).toString();	
    	   }
       }
	return data;
       
    }

}
