import client.BeaconConnector;
import org.apache.commons.cli.*;
import org.joda.time.DateTime;
import org.joda.time.Period;
import utils.TimeUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static utils.StringUtils.countCharacter;
import static utils.StringUtils.customPrintMap;
import static utils.TimeUtils.checkDatesIsLaterThanRequired;


/**
 * Created by olga on 4/15/16.
 */
public class SummarizeBeacon {

	public static final String FROM_ARG = "from";
	public static final String TO_ARG = "to";
	public static final long REQUIRED_DATE = 1378395540000l;
	BeaconConnector beaconConnector = new BeaconConnector();

	public static void main(String[] args) throws ParseException {
		SummarizeBeacon summarizeBeacon = new SummarizeBeacon();
		try {
			TreeMap<Character, Integer> mapWithSumOfCharacters;
			if (args.length == 0) {
				mapWithSumOfCharacters = summarizeBeacon.summarizeCharactersInLastValue();
			} else {
				Map<String, String> consoleArgs = summarizeBeacon.parseArgs(args);
				mapWithSumOfCharacters = summarizeBeacon.summarizeCharactersInValuesBetweenDates(consoleArgs.get(FROM_ARG), consoleArgs.get(TO_ARG));
			}
			customPrintMap(mapWithSumOfCharacters);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public Map<String, String> parseArgs(String[] args) throws ParseException {
		Options options = new Options();
		options.addOption(FROM_ARG, true, "from time");
		options.addOption(TO_ARG, true, "to time");
		CommandLineParser parser = new DefaultParser();
		final CommandLine cmd = parser.parse(options, args);
		return new HashMap<String, String>() {{
			put(FROM_ARG, cmd.getOptionValue(FROM_ARG));
			put(TO_ARG, cmd.getOptionValue(TO_ARG));
		}};
	}

	public TreeMap<Character, Integer> summarizeCharactersInLastValue() {
		String outputValue = beaconConnector.getOutputValueFromLastRecord();
		return countCharacter(outputValue);
	}

	public TreeMap<Character, Integer> summarizeCharactersInValuesBetweenDates(String from, String to) {
		StringBuilder outputValues = new StringBuilder();
		DateTime fromDate = TimeUtils.parseDateAgo(from);
		DateTime toDate = TimeUtils.parseDateAgo(to);
		checkDatesIsLaterThanRequired(REQUIRED_DATE, fromDate, toDate);

		while (fromDate.isBefore(toDate) || fromDate.isEqual(toDate)) {
			String outputValueForDate = beaconConnector.getOutputValueFromCurrentRecord(fromDate.getMillis() / 1000);
			outputValues.append(outputValueForDate);
			fromDate = fromDate.plus(Period.minutes(1));
		}
		return countCharacter(outputValues.toString());
	}

}