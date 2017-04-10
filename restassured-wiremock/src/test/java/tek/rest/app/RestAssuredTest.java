package tek.rest.app;

import static org.junit.Assert.*;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAssuredTest extends BaseTest {
	     
	@Test
	public void testStatusCodePositive() {
	         
		RestAssured.
	    given().
        	accept(ContentType.JSON).
	    when().
	        get(ENDPOINT_URL).
	    then().
	        assertThat().statusCode(200);
	}
	
	@Test
	public void testResponseContents() {
	         
	    String response = RestAssured.get(ENDPOINT_URL).asString();
	    assertEquals("You've reached a valid WireMock endpoint", response);
	}
	
	@Test
	public void testResponseData() {
	         
	    RestAssured.
	    given().
        	accept(ContentType.JSON).
        	pathParam("id", 1).
	    when().
	        get(ENDPOINT_URL + "/{id}").
	    then().
	        assertThat().statusCode(equalTo(200)).
	    and().
	        assertThat().body("cars.name[2]", equalTo("Fiat")).
	    	assertThat().body("cars.models[2]", equalTo(Arrays.asList("500", "Panda")));
	}
}
