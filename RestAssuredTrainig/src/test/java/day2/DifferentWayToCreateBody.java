package day2;

import java.util.HashMap;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DifferentWayToCreateBody {
	
	//Get post method body using hashmap
	//URL- https://fakestoreapi.com/products
	
	@Test
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
}
