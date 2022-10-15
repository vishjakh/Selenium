package swagger.petstore;

import static Resource.LoggerManager.logger;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class user extends Base {
	Faker f = new Faker();
	String userName = "";


	@Test
	public void createUserWithArray() {
		JSONObject obj = new JSONObject();
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		String firstName = f.name().firstName();
		obj.put("id", f.number().numberBetween(1, 1000));
		obj.put("username", firstName);
		obj.put("lastname", f.name().lastName());
		obj.put("email", firstName + "@testing.com");
		obj.accumulate("password", firstName);
		obj.put("number", f.number().randomNumber(10, true));
		obj.put("userStatus", 0);
		JSONArray array = new JSONArray();
		array.put(obj);
		given().contentType(ContentType.JSON).header("accept", "application/json").body(array.toString()).when()
				.post("/user/createWithArray").then().assertThat().statusCode(200).and().extract().path("username");
		userName = firstName;
	}

	@Test
	public void createUserWithList() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		Map obj = new HashMap();
		String firstName = f.name().firstName();
		obj.put("id", f.number().numberBetween(1, 1000));
		obj.put("username", firstName);
		obj.put("lastname", f.name().lastName());
		obj.put("email", firstName + "@testing.com");
		obj.put("password", firstName);
		obj.put("number", f.number().randomNumber(10, true));
		obj.put("userStatus", 0);
		List list = new ArrayList();
		list.add(obj);
		given().contentType(ContentType.JSON).header("accept", "application/json")
				.body(list.toString()).when().post("/user/createWithList").then().assertThat().statusCode(200);

	}

	@Test( dependsOnMethods = "createUserWithArray")
	public void getUser() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
//		System.out.println(userName);
		given().header("accept", "application/json").when().get("/user/" + userName).then().assertThat()
				.statusCode(200);
	}

	@Test(dependsOnMethods = "createUserWithArray")
	public void updateUser() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		JSONObject obj = new JSONObject();
		String firstName = f.name().firstName();
		obj.put("id", f.number().numberBetween(1, 1000));
		obj.put("username", firstName);
		obj.put("lastname", f.name().lastName());
		obj.put("email", firstName + "@testing.com");
		obj.accumulate("password", firstName);
		obj.put("number", f.number().randomNumber(10, true));
		obj.put("userStatus", 0);
		given().header("Content-Type", "application/json").header("accept", "application/json").body(obj.toString())
				.when().put("/user/" + userName).then().assertThat().statusCode(200);
	}

	
	@Test(priority = 1, dependsOnMethods = "createUserWithArray")
	public void deleteUser() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		given().header("accept", "application/json").when().delete("/user/" + userName).then().assertThat()
				.statusCode(200);
	}

	
	@Test(dependsOnMethods = "createUserWithArray")
	public void login() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		JSONObject obj = new JSONObject();
		obj.put("username", userName);
		obj.put("password", userName);
		String message = given().contentType(ContentType.JSON).header("accept", "application/json").body(obj.toString())
				.when().get("/user/login").then().assertThat().statusCode(200).and().extract().path("message");
		Assert.assertTrue(message.contains("session"));

	}

	@Test(dependsOnMethods="login")
	public void logout() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		given().when().get("/user/logout").then().assertThat().statusCode(200);
	}
	
	
	

}
