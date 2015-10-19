package com.maxwell.pos.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//日期格式轉化，工具類
public class DateUtil {
	
	
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		Date date = new Date();
		
		
		System.out.println(format.format(date));
	}

	public static Date toDate(String str) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;
		try {
			date = dateformat.parse(str);
		} catch (Exception e) {
			return null;
		}
		return date;
	}
	
	public static String toChar(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日");
		String str = null;
		try {
			str = dateformat.format(date);
		} catch (Exception e) {
			return null;
		}
		return str;
	}
	
	public static String toCharForDataGrid(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String str = null;
		try {
			str = dateformat.format(date);
		} catch (Exception e) {
			return null;
		}
		return str;
	}
	
	public static String toCharForJsp(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		String str = null;
		try {
			str = dateformat.format(date);
		} catch (Exception e) {
			return null;
		}
		return str;
	}
	
	public static Date jsonDateFormat(String str) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = dateformat.parse(str);
		} catch (Exception e) {
			return null;
		}
		return date;
	}
	public static Date addDay(Date date, int n) {
	      try {   
	            Calendar calendar = Calendar.getInstance();   
	            calendar.setTime(date);   
	            calendar.add(Calendar.DATE, n);//增加一天   
	            //cd.add(Calendar.MONTH, n);//增加一个月   
	  
	            return calendar.getTime();
	        } catch (Exception e) {   
	        	
	            return null;   
	        } 
	}
}
