package shopperProfile;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericUtility.BaseAPIClass;
import genericUtility.ExcelUtility;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojoLibrary.CreateAShopper;
import pojoLibrary.ShopperLogin;
/**
 * 
 * @author Akash
 *
 */
public class RegisterAsAShopperAndLogin extends BaseAPIClass {

	@Test
	public void registerAsAShopper() {
		
		CreateAShopper createShopper = new CreateAShopper("Noida", "India", "saket123@gmail.com", "Saket", "MALE", "B", "saket@123", 8765348756l, "UP", "ALPHA");
		//File file = new File("./src/test/resources/createShopper.json");
		given()
		 	//.header("Accept", "application/json")
		    //.contentType("application/json")
			.body(createShopper)
			.contentType(ContentType.JSON)
		
		.when()
			.post("/shoppers")
		.then()
			.assertThat().contentType(ContentType.JSON)
			.statusCode(201)
			.log().all();
			
	}
	
	@Test
	public void shopperLogin() {
		int randomNo = jUtils.generateRandomNo(1000);
		
		String password = "akash"+randomNo+"@123";
		long phoneNo = 7899875654l;
		//Creating shopper 
		CreateAShopper createShopper = new CreateAShopper("Noida", "India", "akash"+randomNo+"@gmail.com", "Akash"+randomNo+"", "MALE", "Deb"+randomNo+"", password, phoneNo, "UP", "ALPHA");
			
		 Response response = given()
			.body(createShopper)
			.contentType(ContentType.JSON)
		
		.when()
			.post("/shoppers")
		.then()
			.assertThat().contentType(ContentType.JSON)
			.statusCode(201)
			.extract().response();
		 
		 JsonPath jsonPath = response.jsonPath();
		 
		 String email = jsonPath.get("data.email");
		 System.out.println("Shopper has been created");
		 
		 //Logging in the created Shopper
		 ShopperLogin shopperLogin=new ShopperLogin(email, password, "SHOPPER");
		 
		 given()
		 	.body(shopperLogin)
		 	.contentType(ContentType.JSON)
		 
		 .when()
		 	.post("/users/login")
		 
		 .then()
		 	.assertThat().contentType(ContentType.JSON)
		 	.statusCode(200)
		 	.log().all();
		 	
	}
	
	@Test(dataProvider = "dataFromExcel")
	public void findShopperDataByShopperId(String city, String country, String email, String firstName, String gender, String lastName, String password, long phoneNo, String state, String zoneId ) {
						
		//Creating shopper 
		CreateAShopper createShopper = new CreateAShopper(city, country, email, firstName, gender, lastName, password, phoneNo, state, zoneId);
		
		Response responseForCreateShopper = given()
			.body(createShopper)
			.contentType(ContentType.JSON)
		.when()
			.post("/shoppers")
		
		.then()
			.extract().response();
		
		JsonPath jsonPath = responseForCreateShopper.jsonPath();
		
		String shopperId = jsonPath.getString("data.userId");
		String emailFromJson = jsonPath.get("data.email");
		System.out.println("Shopper has been created");
		
		System.out.println("Shopper id ----------------->"+shopperId);
		
		 //Logging in the created Shopper
		 ShopperLogin shopperLogin=new ShopperLogin(emailFromJson, password, "SHOPPER");
		 
		Response responseForShopperLogin= given()
		 	.body(shopperLogin)
		 	.contentType(ContentType.JSON)
		 
		 .when()
		 	.post("/users/login")
		 
		 .then()
		 	.extract().response();
		
		String jwtToken = responseForShopperLogin.jsonPath().get("data.jwtToken");
		 	
		
		given()
			.auth().oauth2(jwtToken)
		
		.when()
			.get("/shoppers/"+shopperId+"")
		
		.then()
			.assertThat().statusCode(200)
			.contentType(ContentType.JSON)
			.log().all();
			
		
	}
	
	@DataProvider
	public Object[][] dataFromExcel() throws EncryptedDocumentException, IOException {
		return new ExcelUtility().getAllExcelData("DemoData");
	}
}
