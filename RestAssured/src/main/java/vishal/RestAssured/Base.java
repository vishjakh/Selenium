package vishal.RestAssured;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//import io.restassured.RestAssured;
public class Base {

	
	public  RequestSpecification getHttpRequest() {
		
		//BaseURI
				RestAssured.baseURI ="https://restful-booker.herokuapp.com";
				
				//Request Object
				RequestSpecification httpRequest = RestAssured.given();
				
				// Adding Header and JSON parameters
				httpRequest.header("Content-Type", "application/json");
				
				return httpRequest;
		
	}

	public String getAuth() {
		
		RequestSpecification httpRequest = getHttpRequest();
		JSONObject params = new JSONObject();
		params.put("username", "admin");
		params.put("password", "password123");
		
		httpRequest.body(params.toJSONString());
		//Response Object
		Response response =  httpRequest.request(Method.POST,"/auth");		
			
//		System.out.println(response.getBody().asString());

		JsonPath jp =response.jsonPath();
		String authCode = jp.get("token");
		return authCode;
		
	}
	
	
	@BeforeSuite
	public void initialize_log4j() {
		
	}
	
}
