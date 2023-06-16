package shopperProfile;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericUtility.BaseAPIClass;
import genericUtility.ExcelUtility;
import pojoLibrary.CreateAShopper;

public class UpdateTheShopperDetails extends BaseAPIClass {
	@DataProvider
	public Object[][] updateData() throws EncryptedDocumentException, IOException {
		return ExcelUtility.getAllExcelData("Demo Data");
	}
	@Test(dataProvider = "updateData")
	public void updateTheShopperDetails(String city, String country, String email, String firstName, String gender, String lastName, String password,long phone, String state, String zoneId) {
		System.out.println(city);
		
	}

}
