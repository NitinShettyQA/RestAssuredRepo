package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 * Added end to end API flow 
 * */

public class HTTPRequest {
	
	int id;

	@Test (priority=0)
	public void getUser()
	{
		
		given()
		
		.when()
		       .get("https://fakestoreapi.com/products")
		
		.then()
		       .statusCode(200)
		       //.body("page",equalTo(2))
		       .log().all();
		       
	}
	
	@Test (priority=1)
	public void createUser() 
	{
		
		HashMap hm= new HashMap();
		hm.put("id", 22);
		hm.put("title", "Fjallraven - Foldsack");
		hm.put("price", 120);
		hm.put("description", "testing123");
		hm.put("category", "women's clothing");
		hm.put("image", "https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_t.png");
		
		id=given()
		     .contentType("application/json")
		     .body(hm)
		
		.when()
		    .post("https://fakestoreapi.com/products")
		    .jsonPath().getInt("id");
		
		//.then()
		  //  .statusCode(201)
		 //   .log().all();
	}
	
	
	@Test (priority=2)
	public void getNewUser()
	{
		
		given()
		
		.when()
		     .get("https://fakestoreapi.com/products/"+id+"")
		
		.then()
		     .statusCode(200)
		     //.body("id", equalTo(id))
		     .log().all();
		
	}
	
	@Test (priority=3)
	public void updateUser()
	{
		
		HashMap hm1 = new HashMap();
		hm1.put("id", id);
		hm1.put("title", "Fjallraven - Foldsack updated");
		hm1.put("price", 130);
		hm1.put("description", "testing1234");
		hm1.put("category", "Mens clothing");
		hm1.put("image", "http://example.com");
		given()
		     .contentType("application/json")
		     .body(hm1)
		.when()
		     .put("https://fakestoreapi.com/products/"+id+"")
		
		.then()
	     .statusCode(200)
	     //.body("id", equalTo(id))
	     .log().all();
	}
	
	@Test (priority=4)
	public void deleteUser()
	{
		
	
		given()
		    
		.when()
		     .delete("https://fakestoreapi.com/products/"+id+"")
		
		.then()
	     .statusCode(200)
	     //.body("id", equalTo(id))
	     .log().all();
	}
	
	
}
