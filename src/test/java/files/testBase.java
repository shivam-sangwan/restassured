package files;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class testBase {
	
	//to extract any value from any response
	public static String getJsonPath(Response response, String key) 
	{
		String s = response.asString();
		JsonPath js = new JsonPath(s);
		String value = js.get(key);
		return value;
	}
	
	
	

}
