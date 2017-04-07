package tek.rest.app;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import tek.rest.mocks.WiremockService;

public class RestAssuredTest {
	
	private final int PORT = 8080;
	private final String ENDPOINT_URL = String.format("http://localhost:%s/an/endpoint", PORT);
	
	private WiremockService wiremock = new WiremockService();
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(PORT);
	     
	@Test
	public void testStatusCodePositive() {
	     
		wiremock.setupStub();
	         
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
	         
		wiremock.setupStub();
	     
	    String response = RestAssured.get(ENDPOINT_URL).asString();
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
	        get(ENDPOINT_URL + "/{id}").
	    then().
	        assertThat().statusCode(Matchers.equalTo(200)).
	        and().
	        assertThat().body("cars.name[1]", Matchers.equalTo("BMW"));
	}
}
