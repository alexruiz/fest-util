package org.fest.util;

import java.text.*;
import java.util.*;

/**
 * @author Tomasz Nurkiewicz
 * @author Joel Costigliola
 */
public class Dates {

  /**
   * ISO 8601 date format "yyyy-MM-dd", example : <code>"2008-12-29"</code>
   */
  public static final DateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  /**
   * ISO 8601 datetime format for error message, example : <code>"2003-04-01T13:01:02"</code> 
   */
  static final DateFormat ISO_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
  
	public static String format(Date date) {
		return ISO_DATE_TIME_FORMAT.format(date);
	}

	public static String format(Calendar calendar) {
		return format(calendar.getTime());
	}

}
