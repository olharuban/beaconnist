package client;

/**
 * Created by olga on 4/15/16.
 */
public class BeaconConnector {

	private static final String OUTPUT_VALUE_PATH = "record.outputValue";
	private static final String LAST_RECORD_URL = "/rest/record/last";
	private static final String CURRENT_RECORD_URL = "/rest/record/";
	private static final String BASE_URL = "https://beacon.nist.gov";

	private RestAssuredClient restAssuredClient;

	public BeaconConnector() {
		restAssuredClient = new RestAssuredClient(BASE_URL);
	}

	public BeaconConnector(RestAssuredClient restAssuredClient) {
		this.restAssuredClient = restAssuredClient;
	}

	public String getOutputValueFromLastRecord() {
		String xml = restAssuredClient.getBodyFromPath(LAST_RECORD_URL);
		return getOutputValue(xml);
	}

	public String getOutputValueFromCurrentRecord(long date) {
		String xml = restAssuredClient.getBodyFromPath(CURRENT_RECORD_URL + date);
		return getOutputValue(xml);
	}

	private String getOutputValue(String xml) {
		return restAssuredClient.getValueFromXml(xml, OUTPUT_VALUE_PATH);
	}


}
