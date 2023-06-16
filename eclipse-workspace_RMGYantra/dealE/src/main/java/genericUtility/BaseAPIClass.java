package genericUtility;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

public class BaseAPIClass {
	
	public JavaUtility jUtils = new JavaUtility();
	public ExcelUtility eUtils = new ExcelUtility();
	
	@BeforeClass
	public void bsConfig() {
		baseURI = "https://www.shoppersstack.com/shopping";
		
	}

}
