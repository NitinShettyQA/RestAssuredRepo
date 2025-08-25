package day7;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;



public class SchemaValidation {
	
	@Test (enabled = false)
	public void jsonSchemaValidation()
	{
		given()
		     .accept(ContentType.JSON)
		
		.when()
		     .get("https://fakestoreapi.com/products")
		
		.then()
		      .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSON-SchemaValidation.json"));
		
	}
	
	@Test (enabled =true)
	public void xmlSchemaValidation()
	{
		given()
		     .accept(ContentType.XML)
		
		.when()
		     .get("https://mocktarget.apigee.net/xml")
		 .then()
		       .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("XML-SchemaValidation.xsd"));
	}
	
}
