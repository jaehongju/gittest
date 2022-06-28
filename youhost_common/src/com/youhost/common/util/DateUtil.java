package com.youhost.common.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Calendar getCalendarYmdhms(String ymdhms) throws Exception {
		if(ValidateUtil.isEmpty(ymdhms) || ymdhms.length()<14) {
			throw new Exception("Invalid yyyy-MM-dd HH:MI:SS format : "+ymdhms);
		}else if(ymdhms.length()>14){
			ymdhms = ymdhms.substring(0,14);
		}
		try{Long.parseLong(ymdhms);}catch(NumberFormatException nfe){
			throw new Exception("Invalid yyyy-MM-dd HH:MI:SS format : "+ymdhms);
		}
		Calendar ret = getCalendarYmdh(ymdhms.substring(0,10));
		int min = Integer.parseInt(ymdhms.substring(10,12));
		int sec = Integer.parseInt(ymdhms.substring(12,14));
		ret.set(Calendar.MINUTE, min);
		ret.set(Calendar.SECOND, sec);
		ret.set(Calendar.MILLISECOND,0);
		return ret;
	}
	
	public static Calendar getCalendarYmdh(String ymdh) throws Exception{
		if(ValidateUtil.isEmpty(ymdh) || ymdh.length()!=10) {
			throw new Exception("Invalid yyyy-MM-dd HH format : "+ymdh);
		}
		try{Integer.parseInt(ymdh);}catch(NumberFormatException nfe){
			throw new Exception("Invalid yyyy-MM-dd HH format : "+ymdh);
		}
		Calendar ret = Calendar.getInstance();
		int year = Integer.parseInt(ymdh.substring(0,4));
		int month = Integer.parseInt(ymdh.substring(4,6));
		int date = Integer.parseInt(ymdh.substring(6,8));
		int hourOfDay = Integer.parseInt(ymdh.substring(8,10));
		ret.set(year, month-1, date, hourOfDay, 0, 0);
		ret.set(Calendar.MILLISECOND,0);
		return ret;
	}
	
	public static Date getDateYmdh(String ymdh) throws Exception{
		return getCalendarYmdh(ymdh).getTime();
	}
}
