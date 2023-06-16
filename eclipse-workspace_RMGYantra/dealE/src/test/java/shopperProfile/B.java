package shopperProfile;

import java.io.File;

import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class B {

	@Test
	public void demo() {
		
	File file = new File("C:\\Users\\AkashDeb\\eclipse-workspace_RMGYantra\\dealE\\src\\test\\resources\\createShopper.json");
	
	ObjectMapper obj = new ObjectMapper();
	obj.writeValue(file);
		
	}

}
