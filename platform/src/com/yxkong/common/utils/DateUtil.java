package com.yxkong.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.log4j.Logger;

/**
 * 功能说明：日期时间操作类
 * @author ducc
 * @created 2014年7月7日 下午2:45:33
 * @updated
 */
public class DateUtil {
	private static final Logger log = Logger.getLogger(DateUtil.class);
	public static Locale DEFAULT_LOCALE = Locale.CHINA;
	private static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
	private static final String TIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 默认的日期格式
	private static final SimpleDateFormat dfForExcel = new SimpleDateFormat("MM/dd/yyyy"); // 默认的日期格式
	private static final SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 默认的时间格式

	/**
	 * 
	 * 方法描述：把日期字符串转换为Date类型的日期，如把"2011-11-16"转换后为Wed Nov 16 00:00:00 CST 2011
	 * 
	 * @param str
	 * @return Date
	 */
	public static Date parseStr(String str) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
		try {
			return format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}

	}
	/**
	 * 
	 * 方法描述：把日期字符串转换为Date类型的日期，如把"2011-11-16"转换后为Wed Nov 16 00:00:00 CST 2011
	 * 
	 * @param str
	 * @return Date
	 */
	public static Date parseStr(String str,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}

	}

	/**
	 * 
	 * 方法描述:date格式化为日期字符串（默认方法）
	 * 
	 * @param date
	 *            需要格式化的日期
	 * @return 按照yyyy-MM-dd格式化后的日期字符串
	 */
	public static String dateToString(Date date) {
		return dateToString(date, null, null);
	}

	/**
	 * 
	 * 方法描述:将date格式化为日期字符串
	 * 
	 * @param date
	 *            需要格式化的日期
	 * @param format
	 *            格式化字符串 形式。 如yyyy-MM-dd、yyyy-MM-dd HH:mm:ss
	 * @param locale
	 *            本地
	 * @return format格式化的日期字符串
	 */
	public static String dateToString(Date date, String format, Locale locale) {
		if (date == null)
			return "";
		if (locale == null)
			locale = DEFAULT_LOCALE;
		if (format == null)
			format = DATE_DEFAULT_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
		return sdf.format(date);
	}

	/**
	 * 
	 * 方法描述:date格式化为时间字符串（默认方法）
	 * 
	 * @param date
	 *            需要格式化的日期
	 * @return 按照yyyy-MM-dd HH:mm:ss格式化后的日期字符串
	 */
	public static String timeToString(Date date) {
		return timeToString(date, null, null);
	}

	/**
	 * 
	 * 方法描述:将date格式化为时间字符串
	 * 
	 * @param date
	 *            需要格式化的日期
	 * @param format
	 *            格式化字符串 形式。 如yyyy-MM-dd、yyyy-MM-dd HH:mm:ss
	 * @param locale
	 *            本地
	 * @return format格式化后的时间字符串
	 */
	public static String timeToString(Date date, String format, Locale locale) {
		if (date == null)
			return "";
		if (locale == null)
			locale = DEFAULT_LOCALE;
		if (format == null)
			format = TIME_DEFAULT_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
		return sdf.format(date);
	}

	/**
	 * 
	 * 方法描述:将date格式为时间字符串 eg:2010-04-20 08：01：10
	 * 
	 * @param date
	 *            需要格式化的日期
	 * @return 按照yyyy-MM-dd HH:mm:ss格式化后的时间字符串
	 */
	public static String formatTime(Date date) {
		return time.format(date);
	}

	/**
	 * 
	 * 方法描述:将日期字符串解析为date
	 * 
	 * @param source
	 *            被解析的日期字符串"yyyy-MM-dd"
	 * @return date yyyy-MM-dd
	 */
	public static java.sql.Date praseSqlDate(String source) {
		try {
			return new java.sql.Date(df.parse(source).getTime());
		} catch (ParseException e) {
			log.info("不能转换成日期的字符串 :: " + source);
			return null;
		}
	}

	/**
	 * 
	 * 方法描述:将日期字符串解析为date
	 * 
	 * @param source
	 *            被解析的日期字符串，格式如下： MM/dd/yyyy
	 * @return date 格式如下：yyyy-MM-dd
	 */
	public static java.sql.Date praseSqlDateForExcel(String source) {
		try {
			return new java.sql.Date(dfForExcel.parse(source).getTime());
		} catch (ParseException e) {
			log.info("不能转换成日期的字符串 :: " + source);
			return null;
		}
	}

	/**
	 * 
	 * 方法描述:将时间字符串解析为date
	 * 
	 * @param source
	 *            被解析的时间字符串， 格式为：yyyy-MM-dd HH:mm:ss
	 * @return date，格式如下：yyyy-MM-dd
	 */
	public static java.sql.Date praseSqlTime(String source) {
		try {
			return new java.sql.Date(time.parse(source).getTime());
		} catch (ParseException e) {
			log.info("不能转换成日期的字符串 :: " + source);
			return null;
		}
	}

	/**
	 * 
	 * 方法描述：时间类型转换 Date to Calendar
	 * 
	 * @param date
	 * @return Calendar
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;

	}

	/**
	 * 
	 * 方法描述：时间类型转换 String to Calendar
	 * 
	 * @param date
	 * @return Calendar
	 */
	public static Calendar strToCalendar(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;

	}
	/**
	 * 功能函数说明：将Calendar按需转成字符串
	 * @author ducc
	 * @created 2014-5-26 下午6:03:52
	 * @updated 
	 * @param calendar
	 * @param format
	 * @return
	 */
	public static String CalendarToString(Calendar calendar,String format){
		  if(StringUtil.isEmpty(format)){
			  format=DATE_DEFAULT_FORMAT;
		  }
		  SimpleDateFormat sdf = new SimpleDateFormat(format);
		  return sdf.format(calendar.getTime());
	}
	/**
	 * 
	 * 方法描述：时间类型转换 String to Calendar
	 * 
	 * @param date
	 * @return Calendar
	 */
	public static Calendar strToCalendar(String time,String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;

	}

	/**
	 * 
	 * 方法描述:获得当前月
	 * 
	 * @return String 当前月字符串 1-9的范围内增加了0前缀
	 */
	public static String getCurrentMonth() {
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		String monthstr = month + "";
		if (monthstr.length() == 1) {
			monthstr = "0" + month;
		}
		return monthstr.toString();
	}

	/**
	 * 
	 * 方法描述:获得当前年 字符串
	 * 
	 * @return 当前年字符串
	 */
	public static String getCurrentYear() {
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(year);
	}

	/**
	 * 
	 * 方法描述:获得当前日期字符串 yyyy-mm-dd
	 * 
	 * @return 当前日期字符串，格式为：yyyy-mm-dd
	 */
	public static String getCurrentDate() {
		java.util.Date b = new Date();
		String date = "";
		try {
			date = df.format(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * 方法描述:获得当前日期字符串mm/dd/yyyy
	 * 
	 * @return 当前日期字符串，格式为：mm/dd/yyyy
	 */
	public static String getCurrentDateForExcel() {
		java.util.Date b = new Date();
		String date = "";
		try {
			date = dfForExcel.format(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * 方法描述:获取系统当前时间,格式为： Mon Nov 14 11:08:10 CST 2011
	 * 
	 * @return Date 系统当前时间
	 */
	public static Date getSysCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/***
	 * 
	 * 方法描述：得到两个日期间相差的月数
	 * 
	 * @param beginDate
	 *            开始时间, 格式为：yyyy-mm-dd
	 * @param endDate
	 *            结束时间, 格式为：yyyy-mm-dd
	 * @return 两个日期间相差的月
	 * @throws ParseException
	 */
	public static int getDistanceMonth(String beginDate, String endDate)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
		Date bDate = format.parse(beginDate);
		Date eDate = format.parse(endDate);
		return getDistanceMonth(bDate, eDate);
	}

	/***
	 * 
	 * 方法描述：得到两个日期间相差的月数
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param dateFmt
	 *            日期格式：如yyyy-mm-dd,mm/dd/yyyy
	 * @return 两个日期间相差的月数
	 * @throws ParseException
	 */
	public static int getDistanceMonth(String beginDate, String endDate,
			String dateFmt) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(dateFmt);
		Date bDate = format.parse(beginDate);
		Date eDate = format.parse(endDate);
		return getDistanceMonth(bDate, eDate);
	}

	/***
	 * 
	 * 方法描述：得到两个日期间相差的月数
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 两个日期间相差的月数
	 * @throws ParseException
	 */
	public static int getDistanceMonth(Date beginDate, Date endDate)
			throws ParseException {
		int result = 0;
		Calendar d1 = new GregorianCalendar();
		d1.setTime(beginDate);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(endDate);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) <= y2) {
			result = d2.get(Calendar.MONTH) - d1.get(Calendar.MONTH);
			while (d1.get(Calendar.YEAR) != y2) {
				result += 12;
				d1.add(Calendar.YEAR, 1);
			}
		} else {
			result = d2.get(Calendar.MONTH) - d1.get(Calendar.MONTH);
			while (d1.get(Calendar.YEAR) != y2) {
				result -= 12;
				d1.add(Calendar.YEAR, -1);
			}
		}
		return result;
	}


	/**
	 * 方法描述:转换结束时间，例如2012-5-17 00:00:00转换为2012-5-17 23:59:59
	 * 
	 * @param endTimeOld  老的结束时间
	 */
	public static void convertEndTime(Calendar endTimeOld) {
		endTimeOld.set(Calendar.HOUR, 23);
		endTimeOld.set(Calendar.MINUTE, 59);
		endTimeOld.set(Calendar.SECOND, 59);
	}
	
	/**
	 * 方法描述:转换结束时间，例如2012-5-17 00:00:00转换为2012-5-17 23:59:59
	 * 
	 * @param endTimeOld  老的结束时间
	 */
	public static void convertStartTime(Calendar endTimeOld) {
		endTimeOld.set(Calendar.HOUR, 0);
		endTimeOld.set(Calendar.MINUTE, 0);
		endTimeOld.set(Calendar.SECOND, 0);
	}
	/**
	 * 方法描述:转换结束时间，例如2012-5-17 00:00:00转换为2012-5-17 23:59:59
	 * 
	 * @param endTimeOld  老的结束时间
	 */
	public static Date convertDateEndTime(Date endTimeOld) {
		Calendar newDate=dateToCalendar(endTimeOld);
		convertEndTime(newDate);
		return newDate.getTime();
	}

}
