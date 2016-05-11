import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.joda.time.DateTimeUtils;
import org.junit.Test;

import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by olga on 4/15/16.
 */
public class TestSummarizeBeacon {

	@Test
	public void testSummarizeCharactersInValuesBetweenDates() throws Exception {
		//15 Apr 2016 14:05:05 GMT
		final long ANY_TIME_IN_MILLISECONDS = 1460729105000l;
		DateTimeUtils.setCurrentMillisFixed(ANY_TIME_IN_MILLISECONDS);
		final TreeMap<Character, Integer> mapWithCountedCharacters = new TreeMap<Character, Integer>() {
			{
				put('0', 30);
				put('1', 29);
				put('2', 32);
				put('3', 38);
				put('4', 25);
				put('5', 35);
				put('6', 27);
				put('7', 42);
				put('8', 30);
				put('9', 28);
				put('A', 35);
				put('B', 25);
				put('C', 43);
				put('D', 35);
				put('E', 27);
				put('F', 31);
			}
		};
		SummarizeBeacon summarizeBeacon = new SummarizeBeacon();
		assertEquals(mapWithCountedCharacters, summarizeBeacon.summarizeCharactersInValuesBetweenDates("4 minutes ago", "1 minutes ago"));
	}

	@Test
	public void testParseArgs() throws ParseException {
		final String startingDate = "5 minutes ago";
		final String tillDate = "1 minutes ago";
		String[] commandLineArgs = {"-from", startingDate, "-to", tillDate};
		final TreeMap<String, String> expectedMap = new TreeMap<String, String>() {
			{
				put(SummarizeBeacon.FROM_ARG, startingDate);
				put(SummarizeBeacon.TO_ARG, tillDate);
			}
		};
		SummarizeBeacon summarizeBeacon = new SummarizeBeacon();
		assertEquals(expectedMap, summarizeBeacon.parseArgs(commandLineArgs));
	}

	@Test(expected = UnrecognizedOptionException.class)
	public void testParseArgsWithInvalidOption() throws ParseException {
		final String startingDate = "5 minutes ago";
		final String tillDate = "1 minutes ago";
		final String invalidOption = "-frodfgm";
		String[] commandLineArgs = {invalidOption, startingDate, "-to", tillDate};
		SummarizeBeacon summarizeBeacon = new SummarizeBeacon();
		summarizeBeacon.parseArgs(commandLineArgs);
	}

	@Test
	public void testSummarizeCharactersInValue() throws ParseException {
		final String startingDate = "5 minutes ago";
		final String tillDate = "1 minutes ago";
		String[] commandLineArgs = {"-from", startingDate, "-to", tillDate};
		final TreeMap<String, String> expectedMap = new TreeMap<String, String>() {
			{
				put(SummarizeBeacon.FROM_ARG, startingDate);
				put(SummarizeBeacon.TO_ARG, tillDate);
			}
		};
		SummarizeBeacon summarizeBeacon = new SummarizeBeacon();
		assertEquals(expectedMap, summarizeBeacon.parseArgs(commandLineArgs));
	}

	/**
	 * Should be error for time that predates the start of the beacon, Unix epoch time 1378395540
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSummarizeCharactersInValuesBetweenDatesWithInvalidDate() {
		SummarizeBeacon summarizeBeacon = new SummarizeBeacon();
		summarizeBeacon.summarizeCharactersInValuesBetweenDates("32 months 1 day 1 hour ago", "5 minutes ago");
	}


}
