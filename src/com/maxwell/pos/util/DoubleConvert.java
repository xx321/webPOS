package com.maxwell.pos.util;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
//解決Struts2 框架錯誤，double 0 或 0.0 轉換上的錯誤!
public class DoubleConvert extends StrutsTypeConverter {  
	  
    @Override  
    public Object convertFromString(Map context, String[] values, Class toClass) {  
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>調用類型轉換");  
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + toClass);  
        if (Double.class == toClass) {  
            String doubleStr = values[0];  
            //System.out.println("獲取到的字符串" + doubleStr);  
            if ("".equals(doubleStr.trim()) || doubleStr ==null)
            	return 0.00;
            Double d = Double.parseDouble(doubleStr);  
            return d;  
        }  
        return 0.00;  
    }  
  
    @Override  
    public String convertToString(Map context, Object o) {  
  
        //System.out.println(">>>>>>>>>>>>>>>>>>value  " + o);  
        //System.out.println("value instanceof Double  " + (o instanceof Double));  
        return o.toString();  
    }  
  
}