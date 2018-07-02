package org.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author VIC
 * 
 *
 */
public class DateUtil {
	
	private static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMddhhmmss");
	private static SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

	public static String getCurrentDateYYYYMMDDString() {
		return YYYYMMDD.format(new Date());
	}

	public static String formatYYYYMMDD(Date cREATE_TIME) {
		return YYYY_MM_DD.format(cREATE_TIME);
	}

}
