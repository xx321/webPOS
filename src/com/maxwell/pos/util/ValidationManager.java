/**
 * 驗證的類
 */
package com.maxwell.pos.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ValidationManager
{
	/**
	 * 處理null
	 * @param str 帶處理的對象
	 * @return 處理結果
	 */
	public static Object changeNull(Object obj)
	{
		if(null==obj)
			return "";
		return obj;
	}
	/**
	 * 驗證日期是否符合規則
	 * @param checkValue 被驗證的時間字符串
	 * @return 驗證結果
	 */
	public static boolean validateDate(String checkValue)
	{
		String eL= "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))"; 
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(checkValue);    
        boolean result = m.matches();   
        return result;
	}
	/**
	 * 驗證郵編是否符合規則
	 * @param zip 被驗證的郵編字符串
	 * @return 驗證結果
	 */
	public static boolean validateZip(String zip)
	{
		String eL= "\\d{6}"; 
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(zip);    
        boolean result = m.matches();   
        return result;
	}
	/**
	 * 驗證電話是否符合規則
	 * @param phone 被驗證的電話字符串
	 * @return 驗證結果
	 */
	public static boolean validatePhone(String phone)
	{
		String eL= "(\\(\\d{3}\\)|\\d{3}-|\\d{4}-|\\(\\d{4}\\))?\\d{8}"; 
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(phone);    
        boolean result = m.matches();   
        return result;
	}
	/**
	 * 驗證郵箱是否符合規則
	 * @param email 被驗證的郵箱字符串
	 * @return 驗證結果
	 */
	public static boolean validateEmail(String email)
	{
		String eL= "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"; 
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(email);    
        boolean result = m.matches();   
        return result;
	}
	/**
	 * 驗證價格是否符合規則
	 * @param price 被驗證的價格字符串
	 * @return 驗證結果
	 */
	public static boolean validatePrice(String price)
	{
		String eL= "\\d+"; 
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(price);    
        boolean result = m.matches();   
        return result;
	}
}
