package Basics;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import payload.Payload;

public class p1_Basics {

	public static void main(String[] args) {
		//use details from "first api contract" file
		//validate add place API response i.e. post method response
		
//		given(): method to take all 'api contract' details
//		when(): to submit api(using resource and http method(get/post etc.))
//		then(): to validate response
//		log().all(): to generate logs for request/response in console
		
		//base uri/url of application
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//given,when,then
		given().relaxedHTTPSValidation().log().all().queryParam("key", "qaclick123")
        //relaxedHTTPSValidation(): to access site(which require ssl certificate) without ssl certificate
		.header("Content-Type","application/json")
		.body(Payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)");
		               
		 //validated statusCode,scope and server of 'add place API' response
		
		

	}

}
