package Advanced;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import files.testBase;
import payload.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Create_bug_Attach_ss {
         //create a bug in jira and attached ss of failure
	
	@Test
	public void createBug()
	{
		RestAssured.baseURI = "https://shivamsangwan2011.atlassian.net/";
		
		//this api request will create a bug in jira
		Response res = given().log().all().header("Content-Type","application/json")
	    .header("Authorization","Basic cbhd37hH8jd") //giving token 'cbhd37hH8jd'
		.body("provide sample body present in api contract, contains code of creatin bug")
		.when().post("resource")
		.then().assertThat().statusCode(200).extract().response();
		
		String issueId = testBase.getJsonPath(res, "id"); //capture id of bug 
		  
		
		
		//add attachment to bug
		
		given().log().all()
		.pathParam("key", issueId) //issueid is passed as path parameter to load the issue in jira using uri
		.header("Authorization","Basic cbhd37hH8jd")
		.header("x-Atlassion-Token","No-Check")  //additional header provided in api contract
		
		//multipart: upload the given file to server 
		//(similar thing is done in postmant using 'form-data' section in body)
		.multiPart("File", new File("C:\\Users\\shiva\\Pictures\\flower.jpg"))
		//.when().post("rest/api/issue/1001/attachments")
		//instead of hardcoded issueId above...we can use {key} to take issueId from path parameters
		.when().post("rest/api/issue/{key}/attachments")
		.then().assertThat().statusCode(200);
		
		//note: here we are sending a File through multipart() i.e. we are not sending...
		//...any json body..so header ("Content-Type","application/json") is not needed
	}
	
	
}
