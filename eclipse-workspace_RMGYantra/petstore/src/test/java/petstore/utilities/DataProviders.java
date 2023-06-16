package petstore.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {
		 
		ExcelUtility eUtils = new ExcelUtility(IPathConstants.USER_DATA_FILEPATH);
		
		int rowCount = eUtils.getLastRowCount(IPathConstants.USER_DATA_SHEETNAME);
		int cellCount = eUtils.getLastCellCount(IPathConstants.USER_DATA_SHEETNAME, 1);
		
		String [][] apiData = new String[rowCount][cellCount];
		
		for (int i = 1; i <=rowCount; i++) {
			
			for (int j = 0; j < cellCount; j++) {
				apiData[i-1][j] = eUtils.getCellData(IPathConstants.USER_DATA_SHEETNAME, i, j).toString();
			}
			
		}
		
		return apiData;
			
	}
	
	@DataProvider(name = "Usernames")
	public String[] getUsernames() throws IOException {
		 
		ExcelUtility eUtils = new ExcelUtility(IPathConstants.USER_DATA_FILEPATH);
		
		int rowCount = eUtils.getLastRowCount(IPathConstants.USER_DATA_SHEETNAME);
		
		String []apiData = new String[rowCount];
		
		for (int i = 1; i <=rowCount; i++) {
			
				apiData[i-1] = eUtils.getCellData(IPathConstants.USER_DATA_SHEETNAME, i, 1);
			
		}
		
		return apiData;	
}
}