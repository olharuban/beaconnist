package utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static utils.TimeUtils.parseDateAgo;

/**
 * Created by olga on 4/15/16.
 */
public class TestTimeUtils {

	//03 Mar 2016 03:30:30 GMT
	public static final long ANY_TIME_IN_MILLISECONDS = 1456975830000l;
	//03 Mar 2016 03:26:30 GMT
	public static final long SOME_MINUTES_AGO = 1456975590000l;
	//02 Mar 2016 02:30:30 GMT
	public static final long SOME_DAYS_AND_HOUR_AGO = 1456885830000l;
	//29 Feb 2016 01:29:30 GMT
	public static final long SOME_DAYS_HOURS_MINUTE_AGO = 1456709370000l;
	//01 Feb 2014 03:28:30 GMT
	public static final long SOME_MONTHS_DAYS_HOURS_MINUTES_AGO = 1391225310000l;

	@Test
	public void testParseDateAgoWithMinutesArgument() {
		DateTimeUtils.setCurrentMillisFixed(ANY_TIME_IN_MILLISECONDS);
		assertEquals(new DateTime(SOME_MINUTES_AGO),
				parseDateAgo("4 minutes ago"));
	}

	@Test
	public void testParseDateAgoWithDaysAndHoursArgument() {
		DateTimeUtils.setCurrentMillisFixed(ANY_TIME_IN_MILLISECONDS);
		assertEquals(new DateTime(SOME_DAYS_AND_HOUR_AGO),
				parseDateAgo("1 day 1 hour ago"));
	}

	@Test
	public void testParseDateAgoWithDaysHoursAndMinuteArgument() {
		DateTimeUtils.setCurrentMillisFixed(ANY_TIME_IN_MILLISECONDS);
		assertEquals(new DateTime(SOME_DAYS_HOURS_MINUTE_AGO),
				parseDateAgo("3 days 2 hours 1 minute ago"));
	}

	@Test
	public void testParseDateAgoWithMonthsDaysHoursAndMinuteArgument() {
		DateTimeUtils.setCurrentMillisFixed(ANY_TIME_IN_MILLISECONDS);
		assertEquals(new DateTime(SOME_MONTHS_DAYS_HOURS_MINUTES_AGO),
				parseDateAgo("25 months 2 days 2 minutes ago"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidValue() throws Exception {
		parseDateAgo("Invalid value");
	}

	@Test()
	public void testCheckDatesIsLaterThanRequired() throws Exception {
		final long anyDate = 1378395540000l;
		final long dateAfter = 1378395541000L;
		TimeUtils.checkDatesIsLaterThanRequired(anyDate, new DateTime(dateAfter));
	}


	@Test(expected = IllegalArgumentException.class)
	public void testCheckDatesIsBeforeThanRequired() throws Exception {
		final long anyDate = 1378395540000l;
		final long dateBefore = 1378395539000l;
		TimeUtils.checkDatesIsLaterThanRequired(anyDate, new DateTime(dateBefore));
	}
}
