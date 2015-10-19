package com.maxwell.pos.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test06 {

	public static void main(String[] args) {
/*		String str = "123";
		
		if(str.matches("\\d*"))
			System.out.println("yes");*/
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh 時 mm 分ss 秒");
		
		Date date = new Date();
		
		System.out.println(format.format(date));
	}
}
