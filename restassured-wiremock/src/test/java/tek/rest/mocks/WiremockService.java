package tek.rest.mocks;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WiremockService {

	public void setupStub() {

		stubFor(get(urlEqualTo("/an/endpoint"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "application/json")
						.withStatus(200)
						.withBody("You've reached a valid WireMock endpoint")));
		
		stubFor(get(urlEqualTo("/an/endpoint/1"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "application/json")
						.withStatus(200)
						.withBody(
								"{\n" +
								"    \"name\":\"John\",\n" +
								"    \"age\":30,\n" +
								"    \"cars\": [\n" +
								"        { \"name\":\"Ford\", \"models\":[ \"Fiesta\", \"Focus\", \"Mustang\" ] },\n" +
								"        { \"name\":\"BMW\", \"models\":[ \"320\", \"X3\", \"X5\" ] },\n" +
								"        { \"name\":\"Fiat\", \"models\":[ \"500\", \"Panda\" ] }\n" +
								"    ]\n" +
								" }")));
	}
}
