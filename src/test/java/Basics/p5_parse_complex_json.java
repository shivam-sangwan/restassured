package Basics;

import org.testng.Assert;

import payload.Payload;
import io.restassured.path.json.JsonPath;

public class p5_parse_complex_json {

	public static void main(String[] args) {
		//refer p5 notepad file for json and questions
		//using dummy response from payload class
		JsonPath js = new JsonPath(Payload.coursePrice());
		
		
		//Print No of courses returned by API
		int count = js.getInt("courses.size()");
		//size(): method of jsonpath, gives size of array present in jsonpath
		System.out.println(count);
		
		
		//Print Purchase Amount
		int pur_amt = js.getInt("dashboard.purchaseAmount");
		System.out.println(pur_amt);
		
		
		//Print Title of the first course..ie..access title of first element of array
		String title_1 = js.get("courses[0].title");
		//get() also returnes a string just like getstring()
		System.out.println(title_1);
		
		
		//Print All course titles and their respective Prices
		
		for(int i=0;i<count;i++)
		{
			String title = js.get("courses["+i+"].title");
			int prices = js.get("courses["+i+"].price");
		    System.out.println(title); 
		    System.out.println(prices);  
		}
		
		System.out.println("switch");
		
		//Print no of copies sold by RPA Course
		for(int i=0;i<count;i++)
		{
		   String title = js.get("courses["+i+"].title");
		   if(title.equals("RPA"))
		   {
			   int copies = js.get("courses["+i+"].copies");
			   System.out.println(copies);
			   break;
		   }
		   
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		int purc_amt = js.getInt("dashboard.purchaseAmount");
		int sum =0;
		for(int i=0;i<count;i++)
		{
		   
			   int c = js.get("courses["+i+"].copies"); 
			   int p = js.get("courses["+i+"].price"); 
			   sum = sum + (c*p); 
		}
	
		Assert.assertEquals(sum, purc_amt);
		
	}

}
