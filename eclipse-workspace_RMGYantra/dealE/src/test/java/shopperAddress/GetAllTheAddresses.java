package shopperAddress;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class GetAllTheAddresses {
	
	@Test
	public void getAllTheAddresses() {
		
		when()
		.get("/products/alpha")
	
		.then()
		.assertThat().statusCode(200)
		.contentType(ContentType.JSON)
		.log().all();	
		
		String name=when()
			.get("/products/alpha")
		.then()
			.extract().path("name");
		
		System.out.println("Name:--------------------"+name);
		
		
		
	}

}
