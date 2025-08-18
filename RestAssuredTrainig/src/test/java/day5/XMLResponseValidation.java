package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class XMLResponseValidation {
	
	
	@Test (enabled =false)
	public void xmlResponseValidation()
	{
		given()
		    .header("Accept", "application/xml")
		
		.when()
		     .get("https://api.tfl.gov.uk/StopPoint/Search/London?page=1&pageSize=10&forceXml=true")
		     
		 .then()
		       .statusCode(200)
		       //.log().body()
		      .contentType("application/xml")
		      // .log().body()
		       .body("SearchResponse.Matches.SearchMatch[0].Id", equalTo("HUBLBG"))
		       .body("SearchResponse.Matches.SearchMatch[0].Modes.Mode[0]", equalTo("tube"));
		
		
		
		       
	}
	
	//Approach 2 by storing the response in a variable
	
	@Test (enabled =false)
	public void xmlResponseValidationApproach2()
	{
		Response res =given()
				.accept(ContentType.XML)
		      
		
		.when()
		      .get("https://api.tfl.gov.uk/StopPoint/Search/London?page=1&pageSize=10&forceXml=true");
		
		
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.contentType(), "application/xml; charset=utf-8");
		Assert.assertEquals(res.xmlPath().get("SearchResponse.Matches.SearchMatch[0].Id").toString(), "HUBLBG");
		Assert.assertEquals(res.xmlPath().get("SearchResponse.Matches.SearchMatch[0].Modes.Mode[0]").toString(), "tube");

	}
	boolean status= false;
	
	@Test (enabled=true)
	public void usingXmlPath()
	{
		Response res =given()
		     .accept(ContentType.XML)
		
		.when()
		     .get("https://api.tfl.gov.uk/StopPoint/Search/London?page=1&pageSize=10&forceXml=true");
		     
		 XmlPath xmlobj = new XmlPath(res.asString());
			/*
			 * System.out.print("res to string   "+res.toString()); System.out.println();
			 * System.out.print("res as string    "+res.asString());
			 */
		 //Check if id jad 910GSTFD
		 
		List<String>List_Id= xmlobj.getList("SearchResponse.Matches.SearchMatch.Id");
		
		for(String ids: List_Id)
		{
			if(ids.equals("910GSTFD"))
			{
				status=true;
				break;
			}
		}
		
		System.out.println(status);
		Assert.assertEquals(status, true);
		
	}
}
