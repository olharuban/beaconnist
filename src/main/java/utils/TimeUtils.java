package utils;

import org.joda.time.DateTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by olga on 4/15/16.
 */
public class TimeUtils {

	public static Pattern p = Pattern.compile("^((?<months>[1-9][0-9]*)\\s+months?\\s+)?((?<days>[1-9][0-9]*)\\s+days?\\s+)?((?<hours>[1-9][0-9]*)\\s+hours?\\s+)?((?<minutes>[1-9][0-9]*)\\s+minutes?\\s+)?ago$");

	public static DateTime parseDateAgo(String timeAgoStr) {
		Matcher matcher;
		if (timeAgoStr != null && (matcher = p.matcher(timeAgoStr)).matches()) {
			int monthsAgo = (matcher.group("months") != null) ? Integer.parseInt(matcher.group("months")) : 0;
			int daysAgo = (matcher.group("days") != null) ? Integer.parseInt(matcher.group("days")) : 0;
			int hoursAgo = (matcher.group("hours") != null) ? Integer.parseInt(matcher.group("hours")) : 0;
			int minutesAgo = (matcher.group("minutes") != null) ? Integer.parseInt(matcher.group("minutes")) : 0;

			DateTime dateTime = new DateTime().minusMonths(monthsAgo).minusDays(daysAgo).minusHours(hoursAgo).minusMinutes(minutesAgo);

			return new DateTime(dateTime);
		} else {
			throw new IllegalArgumentException("Invalid time: " + timeAgoStr);
		}
	}

	public static void checkDatesIsLaterThanRequired(long requiredDate, DateTime... dateTimes) {
		DateTime requiredDateTime = new DateTime(requiredDate);
		for (DateTime dateTime : dateTimes) {
			if (dateTime.isBefore(requiredDateTime)) {
				throw new IllegalArgumentException("Date should be later than " + requiredDateTime.toString());
			}
		}
	}


}
