package petstrore.test;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import petstore.endpoints.UserEndPoints;
import petstore.payloads.User;
import petstore.utilities.DataProviders;

public class UserTestDD {
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void postUserTest(String id, String username, String firstName, String lastName,String email,String password, String phone, int userStatus ) {
		
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		
		Response response = UserEndPoints.createUser(user);
		response.then().assertThat().statusCode(200)
		.log().all();
								
		

		
	}

}
