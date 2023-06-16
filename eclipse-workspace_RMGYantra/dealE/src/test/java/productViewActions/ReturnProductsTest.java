package productViewActions;

import org.testng.annotations.Test;

import genericUtility.BaseAPIClass;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class ReturnProductsTest extends BaseAPIClass {
	
	@Test
	public void returnAllTheProductsTest() {
		
		when()
			.get("/products/alpha")
		
		.then()
			.assertThat().statusCode(200)
			.contentType(ContentType.JSON)
			.log().all();
		
	}
	
	@Test
	public void returnAllTheDefaultProducts() {
		
		when()
		.get("/products/alpha")
	
	.then()
		.assertThat().statusCode(200)
		.contentType(ContentType.JSON)
		.log().all();
		
	}

}
