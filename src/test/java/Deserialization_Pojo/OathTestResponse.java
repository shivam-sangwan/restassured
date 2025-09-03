package Deserialization_Pojo;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.testBase;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OathTestResponse {
	//generating response and parsing the same using deserialization
	
	
	@Test
	public void Oath()
	{
		//accessing "AS" to get access token
		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		
		Response res = given().log().all()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		//we provided entire baseURI in post, as 'resource' was not available in Api contract
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().assertThat().statusCode(200).extract().response();
		
		System.out.println(res);
		String token = testBase.getJsonPath(res, "access_token");
		
		
		//accessing 'api GetCourseDetails' using token
		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/getCourseDetails";
		
		//deserialization
		GetCourseDetails gc = given().log().all().header("Authorization", token)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then().log().all().assertThat().statusCode(401)
		.extract().response().as(GetCourseDetails.class);
		//json response will get converted to 'pojo class object' using as() function
		//..of restAssured
		//...and response details from "GetCourseDetails" will get accessed using..
	    //..object(gc) of that class
		
		
		//getting response details from "GetCourseDetails" pojo class
		//scenario1: getting linkdin url from "GetCourseDetails" pojo class
		String ld = gc.getLinkedIn();
	    System.out.println(ld);
	    
	    
	    
	    //scenario2: extract price of "SoapUI Webservices testing" courseTitle from response
	    
	    //getCourses() is giving us the object of courses class, using which we are..
	    //..calling getApi() which returns list of objects of 'Api pojo class'
	    
	    List<Api> api = gc.getCourses().getApi();
	    
	  //..using get(i) method of list we accessed ith element of APi list (which is ith object of 'Api pojo class')
	    //..and then we accessed CourseTitle using getCourseTitle() method of 'Api pojo class'
	    for(int i=0;i<api.size();i++)
	    {
	    	String title = api.get(i).getCourseTitle();
	    	if(title.equals("SoapUI Webservices testing"))
	    	{
	    		System.out.println(api.get(i).getPrice());
	    	}
	    }
	    
	    
	    //scenario3: get all courseTitles of 'webAutomation array' in a list
	    //..and compare that list with an array that contains expected titles.
	    List<Webautomation> wa =  gc.getCourses().getWebAutomation();
	    String a[] = {"Selenium Webdriver Java","Cypress","Protractor"};
	    List<String> al = new ArrayList();
	    for(int i=0;i<wa.size();i++)
	    {
	    	al.add(wa.get(i).getCourseTitle()); //adding actual titles to 'al' list
	    }
	   
	    //converting array into list
	    List<String> el = Arrays.asList(a);
	    
	    Assert.assertTrue(el.equals(al));
	}

}
