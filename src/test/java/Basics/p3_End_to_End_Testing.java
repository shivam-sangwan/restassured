package Basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import files.testBase;
import payload.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class p3_End_to_End_Testing {

	public static void main(String[] args) {
		
		//update 'address section of body' of the added place
		
	
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//1: post api
		Response response = given().log().all().queryParam("key", "qaclick123")
	    .header("Content-Type","application/json")
		.body(Payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().extract().response();
		
		//validating responsetime
		Long tm = response.time();
		Assert.assertTrue(tm<2000);
		
		//statuscode
		int stcode = response.getStatusCode();
		Assert.assertEquals(200,stcode);
		
		//header
		String head = response.getHeader("content-type");
		Assert.assertEquals("application/json",head);
		
		String head2 = response.getHeader("Authorization");
		Assert.assertEquals("token",head2);
		
		//cookies
		String cook = response.getCookie("cookie name");
		Assert.assertEquals("cookie value",cook);
		
		//responsebody
		ResponseBody body = response.getBody();
		System.out.println(body.asString());  //prints enrire response
		
		
		
		
		
		
		//extracting placeid and token
		String place_id = testBase.getJsonPath(response,"place_id");
		String token = testBase.getJsonPath(response,"token");
		up.setSddress("newaddress")  //suppose up is object of pojo class and we are updating address here
		

		//2: update api
		Response res2 = given().log().all()
		.header("Content-Type","application/json")
		.header("authorization",token)
		.pathParams("placeid", place_id)
		//note: in pathparams() "place_id" of above response is used.
		.body(up) //passing pojo object with updated address
		.when().put("/maps/api/{placeid}/update/json")  //place_id from pathparams will get populated here
		.then().log().all().statusCode(200).extract().response();

		String msg = testBase.getJsonPath(response,"msg");
		Assert.assertEquals(msg, "address updated successfully");
		

		//3: get api
		//get the place using 'get' method and check if updated address is present there
		

		//First method of validating updated address
		Response res3 = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
		.header("authorization",token)
	    .when().get("/maps/api/place/get/json")
	    .then().log().all().statusCode(200).extract().response();


		String address = testBase.getJsonPath(response,"address");
		Assert.assertEquals(address, "70 winter walk, USA");
		
		//4: delete api
		Response res4 = given().log().all().queryParam("key", "qaclick123")
		.header("authorization",token)
		.pathParams("placeid", place_id)
	    .when().delete("/maps/api/{placeid}/get/json")
	    .then().log().all().statusCode(200).extract().response();


		String msg = testBase.getJsonPath(response,"msg");
		Assert.assertEquals(msg, "api deleted successfully");
	}

}
