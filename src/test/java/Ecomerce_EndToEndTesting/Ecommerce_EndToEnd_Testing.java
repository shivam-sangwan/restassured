package Ecomerce_EndToEndTesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import files.testBase;

public class Ecommerce_EndToEnd_Testing {

	public static void main(String[] args) {
		//website to test: "https://rahulshettyacademy.com/client/"
		//refer "ecommerce" doc for api contract details
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
	    Login_Request l = new Login_Request();
		l.setUserEmail("shivamsangwan2011@gmail.com");
		l.setUserPassword("Aa1@Aa1@K");
				
				
		//part1: login api
		Login_Response lr = given().header("Content-Type","application/json")
		.body(l) //serialization
		.when().post("/api/ecom/auth/login")
		.then().log().all().extract().response().as(Login_Response.class); //deserialization
		
		String token = lr.getToken();
		String Userid = lr.getUserId();
		
 //part2: Create Product api(no serialization/deserialization due to simple payload/response)
		Response res = given().header("Authorization",token) //using token provided by login response
		.formParam("productName", "qwerty")
		.formParam("productAddedBy", Userid) //userid provided by login response
		.formParam("productCategory", "fashion")
		.formParam("productSubCategory", "shirts")
		.formParam("productPrice", 11500)
		.formParam("productDescription", "Addias Originals")
		.formParam("productFor", "women")
		.multiPart("File",new File("C:\\Users\\shiva\\Pictures\\flower.jpg"))
        .when().post("/api/ecom/product/add-product")
        .then().log().all().assertThat().statusCode(200).extract().response();
		//here we used JsonPath instead of deserialization bcoz response is simple
		
		String pid = testBase.getJsonPath(res, "productid");
		 
		
		//part3: Create order api
		Orders o = new Orders();
		o.setCountry("india");
		o.setProductOrderedId(pid);
		
		List<Orders> lo = new ArrayList();
		lo.add(o); //object of orders class which contains country and orderid details, is 
		//..added to list
		
		Create_Order_Request co = new Create_Order_Request();
		co.setOrders(lo);
		
		
		given().header("Content-Type","application/json")
		.header("Authorization",token)
		.body(co)
		.when().post("/api/ecom/order/create-order")
		.then().assertThat().statusCode(200);
		
		//part4: Delete Product api
		Response res2 = given().header("Content-Type","application/json")
		.header("Authorization",token)
		.pathParams("productid", pid)
		.when().delete("/api/ecom/product/delete-product/{productid}") 
		//we uses 'curly braces' to fetch path parameter values, to avoid hard coding
		//..here pid is fetched using {productid} during runTime
 		.then().assertThat().statusCode(200).extract().response();
		
		String msg =  testBase.getJsonPath(res2, "msg"); 
		Assert.assertEquals(msg, "product deleted successfully");
		
		
	}

	
	}


