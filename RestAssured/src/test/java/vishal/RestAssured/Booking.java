package vishal.RestAssured;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Booking extends Base{
	
	
	static Logger logObj = LogManager.getLogger("Booking");
	
@BeforeClass
public void start() {
	logObj.info("Booking Log Started");
}
	
	
	@Test(dataProvider="Excel")
	public void createBooking(String firstName, String lastName) {
		System.out.println(firstName+":"+lastName);
		RequestSpecification httpRequest = getHttpRequest();
		JSONObject jo = new JSONObject();
		jo.put("firstname", firstName);
		jo.put("lastname", lastName);
		jo.put("totalprice",111);
		jo.put("depositpaid", true);
		
		JSONObject jo2 = new JSONObject();
		jo2.put("checkin", "2022-04-23");
		jo2.put("checkout", "2022-04-28");
		jo.put("bookingdates", jo2);
		jo.put("additionalneeds", "Breakfast");
		System.out.println(jo.toString());
		httpRequest.body(jo.toJSONString());
		Response response= httpRequest.request(Method.POST,"/booking");
		System.out.println(response.getBody().asString());
		
	
	}
	
	@Test
	public void updateBooking() {
		RequestSpecification httpRequest = getHttpRequest();
		JSONObject jo = new JSONObject();
		jo.put("firstname", "Test");
		jo.put("lastname", "Test2");
		jo.put("totalprice",111);
		jo.put("depositpaid", true);
		
		JSONObject jo2 = new JSONObject();
		jo2.put("checkin", "2022-04-24");
		jo2.put("checkout", "2022-04-29");
		jo.put("bookingdates", jo2);
		jo.put("additionalneeds", "Breakfast");
		System.out.println(jo.toString());
		httpRequest.body(jo.toJSONString());
		Response response= httpRequest.request(Method.POST,"/booking");
		System.out.println(response.getBody().asString());
		
	
	}
	
	@Test
	public void partialUpdateBooking() {
		RequestSpecification httpRequest = getHttpRequest();
		String authCode =getAuth();
		httpRequest.header("Accept","application/json");
		httpRequest.header("Cookie","token="+authCode);
		JSONObject jo = new JSONObject();
		jo.put("firstname", "John");
		jo.put("lastname", "Doe");
		httpRequest.body(jo.toJSONString());
		Response response= httpRequest.request(Method.PATCH,"/booking/1");
		
	}
	
	@Test
	public void deleteBooking() {
		RequestSpecification httpRequest = getHttpRequest();
		String authCode =getAuth();
		httpRequest.header("Accept","application/json");
		httpRequest.header("Cookie","token="+authCode);
		JSONObject jo = new JSONObject();
		httpRequest.body(jo.toJSONString());
		Response response= httpRequest.request(Method.DELETE,"/booking/307");
//		System.out.println(response.getBody().asString());
		Assert.assertEquals("HTTP/1.1 201 Created", response.statusLine());
		
	}
	
	
	@DataProvider(name="Excel")
	public String[][] getExcelData() throws IOException {
		ExcelData exl = new ExcelData();
		System.out.println(exl.getTotalRows()+":"+exl.getCellCount());
		String [][] data = new String[exl.getTotalRows()][exl.getCellCount()]; 
		
		 for (int i =1;i<exl.getTotalRows();i++) {
			 for(int j=0;j<exl.getCellCount();j++) {
				 System.out.println(exl.getSheet().getRow(i).getCell(j).getStringCellValue());
				 data[i-1][j]=exl.getSheet().getRow(i).getCell(j).getStringCellValue();
			 }
		 }
		return data;
		
	}
	
	@AfterClass
	public void end() {
		logObj.info("Booking Log Ended");
	}
	


}
