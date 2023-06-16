package petstrore.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import petstore.endpoints.UserEndPoints;
import petstore.payloads.User;

public class UserTest {
	Faker faker;
	User userPayLoad;
	
	@BeforeClass
	public void setUp() {
		
		faker = new Faker();
		userPayLoad =  new User();
		
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(7, 12));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority = 1)
	public void postUserTest() {
		
		Response response = UserEndPoints.createUser(userPayLoad);
		response.then().log().all();
		
		
		Assert.assertEquals(response.statusCode(), 200);
	
	}
	
	@Test(priority = 2)
	public void readUserUserByNameTest() {
		
		Response response = UserEndPoints.readUser(this.userPayLoad.getUsername());
		
		response.then().assertThat().statusCode(200)
		.log().all();
		
	}
	
	@Test(priority = 3)
	public void updateUserTest() {
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.userPayLoad.getUsername(), userPayLoad);
		response.then().assertThat().statusCode(200).log().all();
		
		//Checking for update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayLoad.getUsername());
		responseAfterUpdate.then().assertThat().statusCode(200)
		.log().all();
		
	}
	

}
