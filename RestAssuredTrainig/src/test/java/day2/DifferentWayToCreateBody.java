package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DifferentWayToCreateBody {
	
	//Get post method body using hashmap
	//URL- https://fakestoreapi.com/products
	
	@Test (enabled =false)
	public void postUsingHashMap()
	{
		HashMap data = new HashMap();
		data.put("id", "23");
		data.put("title", "Testing");
		data.put("price", "123");
		data.put("description", "Testing123");
		data.put("category", "Mens");
		
		//If there is any array in the request body than we can create a array and pass it the hashmap.
		/*
		 * String courses[] = {"c", "c++"};
		 * data.put("Courses", "courses");
		 */
		
		given()
		     .contentType("application/json")
		     .body(data)
		
		.when()
		     .post("https://fakestoreapi.com/products")
		
		.then()
		     .statusCode(201)
		     .body("category", equalTo("Mens")) 
		     //similar way we can compare all the fields one by one but not ideal way of validation. Works fine if there are limited fields in the response
		     .header("Content-Type", "application/json; charset=utf-8")
		     .log().all();
	}
	
	
	//Method2 - using org.json library
	/*
	 * <dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20250517</version>
    </dependency
    Make sure above dependencies is added in pom.xml before using this library to create post request body
	 * */
	
	
	@Test (enabled =false)
	public void postUsingOrgJson()
	{
		//Creating body using org.json library or JSONOBJECT class
		
		JSONObject data = new JSONObject();
		//JSONOBJECT is similar to hashmap on creating the data using put method
		data.put("id", "24");
		data.put("title", "Testing");
		data.put("price", "123");
		data.put("description", "Testing123");
		data.put("category", "Mens");
		
		
		given()
		     .contentType("application/json")
		     //convert the data which we created to string format when sending it s parameter in .body(data.toString) method
		     .body(data.toString())
		
		.when()
		     .post("https://fakestoreapi.com/products")
		
		.then()
		     .statusCode(201)
		     .header("Content-Type", containsString("application/json"))
		     .log().all();
	}
	
	
	//Method 3 - Using POJO class - Plain old java object
	@Test(enabled =false)
	public void usingPojoClass()
	{
		PojoClassPostRequest data = new PojoClassPostRequest();
		
		data.setId(29);
		data.setCategory("Mens");
		data.setPrice("101");
		data.setTitle("title");
		data.setDescription("BINGO");
		//String url = "http://example.com";
		data.setImage("http://example.com");
		
		
		given()
		     .log().all()  
		    // .contentType("application/json")
		     .contentType("application/json")
		     .body(data)
		
		.when()
		     .post("https://fakestoreapi.com/products")
		
		.then()
		     //.log().all()
		     .statusCode(201)
		     .body("category", equalTo("Mens"))
		     .log().all();
	}
	
	
	//Method4 - send json data using external file
	
	@Test (enabled = true)
	public void usingExternalJsonFile() throws FileNotFoundException
	{
		
		//Mention the file which needs to be read
		File file = new File(".\\Json_Data");
		
		//open the file to read
		FileReader fileRead = new FileReader(file);
		
		//pass the file in json tokener
		JSONTokener jt = new JSONTokener(fileRead);
		
		//Finally send it to json object
		JSONObject data = new JSONObject(jt);
		
		given()
	     .contentType("application/json")
	     //convert the data which we created to string format when sending it s parameter in .body(data.toString) method
	     .body(data.toString())
	
	.when()
	     .post("https://fakestoreapi.com/products")
	
	.then()
	     .statusCode(201)
	     .header("Content-Type", containsString("application/json"))
	     .log().all();
		
		
	}
}
