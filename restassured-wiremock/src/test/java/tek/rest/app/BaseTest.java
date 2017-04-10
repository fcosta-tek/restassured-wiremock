package tek.rest.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Rule;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import tek.rest.mocks.WiremockService;

public abstract class BaseTest {
	
	private final String CONFIG_FILE_NAME = "config.properties";
	private final String MOCK_WEBSERVICE_PROPERTY = "mock_webservice";
	
	protected final int PORT = 8080;
	protected final String ENDPOINT_URL = String.format("http://localhost:%s/an/endpoint", PORT);
	
	protected WiremockService wiremock = new WiremockService();
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(PORT);
	
	@Before
	public void setup() throws IOException {
		
		Properties properties = new Properties();
		InputStream input = new FileInputStream(CONFIG_FILE_NAME);
		properties.load(input);
		
		if(properties.getProperty(MOCK_WEBSERVICE_PROPERTY).equals("true"))
			wiremock.setupStub();
	}
}
