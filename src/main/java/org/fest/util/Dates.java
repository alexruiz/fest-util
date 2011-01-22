package org.fest.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Tomasz Nurkiewicz
 * @since 18.01.11, 23:47
 */
public class Dates {

	public static String format(Date date) {
		return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(date);
	}

	public static String format(Calendar calendar) {
		return format(calendar.getTime());
	}

}
