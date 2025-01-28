package com.FISRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*; // Core methods
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class GetRequest {

	public static void main(String[] args) 
	{
		//RestAssured.baseURI = "https://api.coindesk.com/";

        // Send GET request and store the response
       // Response response = RestAssured
              Response res =  given()
                .when()
                .get("https://api.coindesk.com/v1/bpi/currentprice.json") // Endpoint
                .then()
                .statusCode(200) // Validate HTTP status code
                .body("bpi.USD.code", equalTo("USD"))
                .body("bpi.GBP.code", equalTo("GBP"))
                .body("bpi.EUR.code", equalTo("EUR")) 
                .body("bpi.GBP.description", equalTo("British Pound Sterling"))
                .extract()
                .response();


              
              String usd = res.jsonPath().get("bpi.USD.code").toString();
              Assert.assertEquals(usd, "USD");
              String gbp = res.jsonPath().get("bpi.GBP.code").toString();
              Assert.assertEquals(gbp, "GBP");

              String eur = res.jsonPath().get("bpi.EUR.code").toString();
              Assert.assertEquals(eur, "EUR");

              String gbpdes = res.jsonPath().get("bpi.GBP.description").toString();
              Assert.assertEquals(gbpdes, "British Pound Sterling");



        // Print response body
              
        System.out.println("Response Body:");
        System.out.println(res.asString());

        // Print a specific field (optional)
        String title = res.jsonPath().getString("title");
        System.out.println("Title: " + title);
    }

	}


