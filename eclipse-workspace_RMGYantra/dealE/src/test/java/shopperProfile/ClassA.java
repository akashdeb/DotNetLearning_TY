package shopperProfile;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.http.ContentType;

public class ClassA {

	public static void main(String[] args) {
		
		File file = new File("./src/test/resources/createShopper.json");
		
		given()
			.body(file)
			.contentType(ContentType.JSON)
		
		.when()
			.post("https://www.shoppersstack.com/shopping/shoppers")
		
		.then()
			.statusCode(201)
			.log().all();

	}

}
