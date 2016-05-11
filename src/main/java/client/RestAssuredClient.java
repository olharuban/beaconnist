package client;

import com.jayway.restassured.RestAssured;

import static com.jayway.restassured.path.xml.XmlPath.from;

/**
 * Created by olga on 4/21/16.
 */
public class RestAssuredClient {

	public RestAssuredClient(String baseURL) {
		RestAssured.baseURI = baseURL;
	}

	public String getBodyFromPath(String path) {
		return RestAssured.get(path).andReturn().body().asString();
	}

	public String getValueFromXml(String xml, String path) {
		return from(xml).get(path);
	}

}
