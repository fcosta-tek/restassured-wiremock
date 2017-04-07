package tek.rest;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import tek.rest.mocks.WiremockService;

public class RestAssuredTest {
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8080);
	
	private WiremockService wiremock = new WiremockService();
	     
	@Test
	public void testStatusCodePositive() {
	     
		wiremock.setupStub();
	         
		RestAssured.
	    given().
        accept(ContentType.JSON).
	    when().
	        get("http://localhost:8080/an/endpoint").
	    then().
	        assertThat().statusCode(200);
	}
	
	@Test
	public void testResponseContents() {
	         
		wiremock.setupStub();
	     
	    String response = RestAssured.get("http://localhost:8080/an/endpoint").asString();
	    assertEquals("You've reached a valid WireMock endpoint", response);
	}
	
	@Test
	public void testResponseData() {
	         
	    wiremock.setupStub();
	         
	    RestAssured.
	    given().
        accept(ContentType.JSON).
        pathParam("id", 1).
	    when().
	        get("http://localhost:8080/an/endpoint/{id}").
	    then().
	        assertThat().statusCode(Matchers.equalTo(200)).
	        and().
	        assertThat().body("cars.name[1]", Matchers.equalTo("BMW"));
	}
}
