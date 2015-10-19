package com.maxwell.pos.test;

import java.util.Date;

import com.maxwell.pos.util.DateUtil;

public class Test03 {

	public static void main(String[] args) {
		
		Date date1 = DateUtil.toDate("2013/06/07");
		Date date2 = DateUtil.toDate("2013/06/09");

		System.out.println(date1.before(date2));
		System.out.println(date1.after(date2));
	}
}
