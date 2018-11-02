package com.lukeking.redislock.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author lukeking
 *
 */
public final class DateUtil {
	public static final long MILLONS = 1000l;

	public static final long SECOND = MILLONS;

	public static final long MINITE = SECOND * 60;

	public static final long HOUR = MINITE * 60;

	public static final long DAY = HOUR * 24;

	static Calendar calendar = null;

	static {
		calendar = Calendar.getInstance();
		calendar.set(1970, 0, 1, 0, 0, 0);
	}

	/**
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static String shortDateFormat(Date date) {

		return dateFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public static String shortDateFormatCurrentDate() {

		return dateFormat(new Date(), "yyyy-MM-dd");
	}

	// 返回HH:mm:ss 16:33:11
	public static String shortTime(String date) {

		Date time = dateFormat(date, "yyyy-MM-dd HH:mm:ss");

		return dateFormat(time, "HH:mm:ss");
	}

	public static String shortEntrustDate(String date) {

		Date time = dateFormat(date, "yyyy-MM-dd HH:mm:ss");

		return dateFormat(time, "yyyy.MM.dd");
	}

	/**
	 * 
	 * 
	 * @param date
	 *            日期对象
	 * @return
	 */
	public static String longDateFormat(Date date) {

		return dateFormat(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String longDateFormat2(Date date) {

		return dateFormat(date, "yyyy-MM-dd 00:00:00");
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public static String longDateFormatCurrentDate() {

		return dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String longDateFormatCurrentDate2() {

		return dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * @Title: currentDate @Description: @param @return @return
	 * String  @throws
	 */
	public static String currentDate() {

		return dateFormat(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * @Title: longCurrentDate @Description: @param @return @return String
	 * @throws
	 */
	public static String longCurrentDate() {

		return dateFormat(new Date(), "yyyyMMddHHmmssSSSS");
	}

	public static String getCurrentDate() {

		return dateFormat(new Date(), "yyyy/MM/dd HH:mm:ss");
	}

	/***
	 * 
	 */
	public static Date shortDateFormat(String date) {

		return dateFormat(date, "yyyy-MM-dd");
	}

	public static Date longDateFormat(String date) {

		return dateFormat(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public static long getTimesFromStard() {

		return calendar.getTimeInMillis();
	}

	public static String formatStandartSecond(Integer seconds) {

		Date date = new Date(calendar.getTimeInMillis() + seconds * MILLONS);
		return longDateFormat(date);
	}

	/**
	 * 
	 * 
	 * @param date
	 *            
	 * @param foramt
	 *            
	 * @return
	 */
	public static String dateFormat(Date date, String foramt) {

		String result ="";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(foramt);
			result = df.format(date);
		}
		return result;
	}

	public static Date dateFormat(String date, String format) {

		Date result = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			result = df.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式出现错误!", e);
		}
		return result;
	}

	/**
	 * 
	 * 
	 * @param startDate
	 *      
	 * @param endDate
	 *    
	 * @return 
	 */
	public static int calculateDays(Date startDate, Date endDate) {

		int result = 0;
		if (startDate != null && endDate != null) {
			result = (int) ((endDate.getTime() - startDate.getTime()) / DAY);
		}
		return result;
	}

	public static int calculateDays(Date startDate, String orderCreateDate) {

		Date endDate = longDateFormat(orderCreateDate);
		return calculateDays(startDate, endDate);
	}

	/**
	 * 
	 * 
	 * @param startDate
	 *        
	 * @param endDate
	 *         
	 * @return 
	 */
	public static int calculateHours(Date startDate, Date endDate) {

		int result = 0;
		if (startDate != null && endDate != null) {
			result = (int) ((endDate.getTime() - startDate.getTime()) / HOUR);
		}
		return result;
	}

	public static int calculateHours(String startDateString, Date endDate) {

		Date startDate = longDateFormat(startDateString);
		return calculateHours(startDate, endDate);
	}

	/**
	 *
	 * 
	 * @param startDate
	 *         
	 * @param endDate
	 *           
	 * @return 
	 */
	public static int calculateMinutes(Date startDate, Date endDate) {

		int result = 0;
		if (startDate != null && endDate != null) {
			result = Math.abs((int) ((endDate.getTime() - startDate.getTime()) / MINITE));
		}
		return result;
	}

	public static int calculateMinutes(Date startDate, String orderCreateDate) {

		Date endDate = longDateFormat(orderCreateDate);
		return calculateDays(startDate, endDate);
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public static long currentTime() {

		return new Date().getTime();
	}

	public static String getTime(String date) {

		return String.valueOf(dateFormat(date, "yyyy-MM-dd HH:mm:ss").getTime());
	}

	/**
	 * 
	 * 
	 * @param time
	 * @return 2012-11-11 格式
	 */
	public static String currentTime(long time) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(time));
	}

	/**
	 * @param time
	 * @param patten
	 * @return
	 */
	public static String currentTime(long time, String patten) {

		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		return sdf.format(new Date(time));
	}

	/**
	 * <pre>

	 * &#64;param time 
	 * &#64;return long
	 * </pre>
	 */
	public static long currentTime(String time) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long date = -1;
		try {
			date = sdf.parse(time).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static long currentTime(String time, String paten) {

		SimpleDateFormat sdf = new SimpleDateFormat(paten);
		long date = -1;
		try {
			date = sdf.parse(time).getTime();
		} catch (ParseException e) {
			throw new RuntimeException("日期格式出现错误!", e);
		}
		return date;
	}


	@SuppressWarnings("deprecation")
	public static int getCurrentHour() {

		Date date = new Date();
		int hours = date.getHours();
		return hours;
	}


	public static int subMinute(long expir) {

		long time = new Date().getTime();
		long m = time - expir;
		return (int) (m / 1000 / 60);
	}





	public static String formatDate(Date date, String pattern) {
		if (date == null || pattern == null || pattern == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date nextDate() {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static String showTime(String ctime) {
		return showTime(longDateFormat(ctime));
	}

	public static String showTime(Date time) {
		String r = null;
		long nowtimelong = System.currentTimeMillis();
		long ctimelong = time.getTime();
		long result = Math.abs(nowtimelong - ctimelong);
		System.out.println("result=" + result);
		if (result < 60000) {// 一分钟内
			r = "刚刚";
		} else if (result >= 60000 && result < 3600000) {// 一小时内
			long seconds = result / 60000;
			r = seconds + "分钟前";
		} else if (result >= 3600000 && result < 86400000) {// 一小时后
			r = formatDate(time, "HH:mm");
		} else if (result >= 86400000 && result < 172800000) {// 一天前
			long seconds = result / 86400000;
			r = "昨天" + formatDate(time, "HH:mm");

		} else if (result >= 172800000 && result < 604800000) {
			// 
			r = getWeekOfDate(time) + " " + formatDate(time, "HH:mm");

		} else if (result >= 604800000 && result < 31500000000L) {
			// 
			r = formatDate(time, "MM月dd日 HH:mm");
		} else {//

			r = formatDate(time, "yy年MM月dd日");
		}
		return r;
	}

	public static String getWeekOfDate(String date) {

		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(shortDateFormat(date));
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysName[intWeek];
	}

	public static String getWeekOfDate(Date date) {

		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysName[intWeek];
	}

	public static int dayForWeek(String pTime) {

		Calendar c = Calendar.getInstance();
		c.setTime(shortDateFormat(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 
	 * <p>
	 * Title: addTime
	 * </p>
	 * <p>
	 * Description: 
	 * </p>
	 * 
	 * @author yangyang
	 * @param hour
	 * @return
	 */
	public static long addTime(int hour) {

		Calendar now = Calendar.getInstance();

		now.add(Calendar.HOUR, hour);

		return now.getTimeInMillis();
	}

	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	public int compareDate(Date d1, Date d2) {
		if (d1.getTime() > d2.getTime()) {
			return 1;
		} else if (d1.getTime() < d2.getTime()) {
			return -1;
		} else {// 相等
			return 0;
		}
	}


	public static int calculateYears(String startDate, String endDate) {
		try {
			Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(start);
			c2.setTime(end);
			int year1 = c1.get(Calendar.YEAR);
			int year2 = c2.get(Calendar.YEAR);
			// System.out.println(Math.abs(year1 - year2));
			return (year2 - year1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}



	public static void main(String[] args) {
		// System.out.println(getCurrentHour());
		// long expir = new Date().getTime();
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// System.out.println(subMinute(expir));

		/*
		 * System.out.println ( shortEntrustDate ( "2016-10-21 10:31:30" ) );
		 * System.out.println ( getCurrentDate ( ) ); System.out.println (
		 * calculateMinutes ( new Date ( ) , "2016-12-31 00:00:00" ) + 1 );
		 * System.err.println ( longDateFormatCurrentDate2 ( ) ); Calendar
		 * now=Calendar.getInstance();
		 * 
		 * now.add(Calendar.HOUR,2); System.err.println
		 * (DateUtil.shortDateFormat ( getNextDay(new Date ( ))));
		 */

	/*	int result = calculateYears("2017-09-07", "2027-09-07");
		System.out.println(result);
		*/
		//System.err.println(printDay("2017.9.07","2017.9.09"));
		//System.out.println(daysBetween("2017-9-7","2017-9-8"));
		try {
			/*long l = new Date().getTime();
			Thread.sleep(20000);
			System.out.println(subMinute(l));*/
			/*int i = daysBetween("2017-9-30","2017-11-14");
			System.out.println(i);*/
			/*String s = shortDateFormatCurrentDate();
			System.out.println(s);*/
			System.out.println(shortDateFormat(new Date()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	

	public static int daysBetween(String smdate,String bdate){
		try {
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		     Calendar cal = Calendar.getInstance();    
		     cal.setTime(sdf.parse(smdate));    
		     long time1 = cal.getTimeInMillis();                 
		     cal.setTime(sdf.parse(bdate));    
		     long time2 = cal.getTimeInMillis();         
		     long between_days=(time2-time1)/(1000*3600*24);  
		     return Integer.parseInt(String.valueOf(between_days));   
		} catch (Exception e) {
			System.out.println("计算:"+smdate+",与:"+bdate+"相差天数出错!"+e);
		}
		return -1;
    }  
			
	public static int daysBetween(String smdate){
		return daysBetween(shortDateFormatCurrentDate(),smdate);
    }

	public static int newDaysBetween(String smdate){
		String nowTime = dateFormat(new Date(), "yyyy.MM.dd");
		return newDaysBetween(nowTime,smdate);
    }
	
	
	
	public static int newDaysBetween(String smdate, String bdate) {
		try {
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");  
		     Calendar cal = Calendar.getInstance();    
		     cal.setTime(sdf.parse(smdate));    
		     long time1 = cal.getTimeInMillis();                 
		     cal.setTime(sdf.parse(bdate));    
		     long time2 = cal.getTimeInMillis();         
		     long between_days=(time2-time1)/(1000*3600*24);  
		     return Integer.parseInt(String.valueOf(between_days));   
		} catch (Exception e) {
			System.out.println("计算:"+smdate+",与:"+bdate+"相差天数出错!"+e);
		}
		return -1;
	}
	
	public static int[] subDateFormat(String from, String to) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		try
		{
			c1.setTime(df.parse(from));
			c2.setTime(df.parse(to));
		} catch (java.text.ParseException e)
		{
		// XXX Auto-generated catch block
		System.err.println("日期格式不正确，应该是yyyyMMdd");
		e.printStackTrace();
		}
		int[] result = new int[3];
		result[0] = (c2.get(java.util.Calendar.YEAR) - c1.get(java.util.Calendar.YEAR)) < 0 ? 0 : c2
		.get(java.util.Calendar.YEAR)
		- c1.get(java.util.Calendar.YEAR);
		result[1] = (c2.get(java.util.Calendar.MONTH) - c1.get(java.util.Calendar.MONTH)) < 0 ? 0 : c2
		.get(java.util.Calendar.MONTH)
		- c1.get(java.util.Calendar.MONTH);
		result[2] = (c2.get(java.util.Calendar.DAY_OF_MONTH) - c1.get(java.util.Calendar.DAY_OF_MONTH)) < 0 ? 0 : c2
		.get(java.util.Calendar.DAY_OF_MONTH)
		- c1.get(java.util.Calendar.DAY_OF_MONTH);
		return result;
	}  
	public static String format(int[] result)
	{
	StringBuffer sb = new StringBuffer();
	sb.append("[");
	for (int i = 0; i < result.length - 1; i++)
	sb.append(result[i] + ",");
	sb.append(result[result.length - 1] + "]");
	return sb.toString();
	}
	
}