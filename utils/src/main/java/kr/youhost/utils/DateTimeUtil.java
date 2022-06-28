package kr.youhost.utils;

import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeUtil {
	private final static String DATE_TIME_FORMAT_24 = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * getCurrentDateTime
	 *  convert the current DateTime to String
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		return getCurrentDateTime(DATE_TIME_FORMAT_24);
	}
	/**
	 * getCurrentDateTime
	 *  convert the current dateTime to string wrt formatDateTime
	 */
	public static String getCurrentDateTime(String formatDateTime) {
		DateTime now = new org.joda.time.DateTime();
		return getFormattedDateTime(now, formatDateTime);
	}
	/**
	 * getFormattedDateTime
	 *  convert dateTime to string wrt formatDateTime
	 */
	public static String getFormattedDateTime(DateTime dateTime, String formatDateTime) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(formatDateTime);
		return formatter.print(dateTime);
	}
	public static String getFormattedDateTime(DateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_TIME_FORMAT_24);
		return formatter.print(dateTime);
	}	
	/**
	 * getSecondsToNextMidnight
	 *  get seconds from the current to the next midnight
	 */
	public static int getSecondsToNextMidnight() {
		DateTime now = new DateTime();
		String strTomorrow = DateTimeUtil.getFormattedDateTime(now.plusDays(1), "yyyy-MM-dd");
		DateTime nextMidnight = new DateTime(strTomorrow);

		Seconds diff = Seconds.secondsBetween(now, nextMidnight);
		return diff.getSeconds();
	}
	/**
	 * getDateTime
	 *  convert strDateTime to DateTime
	 */
	public static DateTime getDateTime(String strDateTime) {
		if ( strDateTime==null ) {
			return null;
		}

		return getDateTime(strDateTime, "yyyy-MM-dd HH:mm:ss.S");
	}
	/**
	 * getDateTime
	 *  convert strDateTime with formatDateTime to DateTime
	 */
	public static DateTime getDateTime(String strDateTime, String formatDateTime) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(formatDateTime);
		return formatter.parseDateTime(strDateTime);
	}
	
	public static int getWeekday() {
		DateTime now = new DateTime();
		return now.getDayOfWeek();
	}
	public static int getHour() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.HOUR_OF_DAY);
	}
}
