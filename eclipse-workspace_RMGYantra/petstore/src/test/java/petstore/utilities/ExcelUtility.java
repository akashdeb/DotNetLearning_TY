package petstore.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFCell cell;
	String filePath;
	
	public ExcelUtility(String filePath) {
		this.filePath = filePath;
	}
	
	public int getLastRowCount(String sheetName) throws IOException {

		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);

		int rowNum = workbook.getSheet(sheetName).getLastRowNum();
//		workbook.close();
//		fis.close();
		return rowNum;
	}

	public int getLastCellCount(String sheetName, int rowNo) throws IOException {

		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);

		int cellNum = workbook.getSheet(sheetName).getRow(rowNo).getLastCellNum();
//		workbook.close();
//		fis.close();
		return cellNum;
	}
	
	public String getCellData(String sheetName, int rowNo, int cellNo) throws IOException {
		
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);

		cell = workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo);
		
		DataFormatter formater  = new DataFormatter();
		String data;
		try {
			data = formater.formatCellValue(cell);
		} catch (Exception e) {
			
			data = "";
		}
		
//		workbook.close();
//		fis.close();
		return data;
	}
}
