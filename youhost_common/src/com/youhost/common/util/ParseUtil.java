package com.youhost.common.util;

import java.util.HashMap;

public class ParseUtil {
	
	public static String parseQuota(String s){
		return ValidateUtil.isEmpty(s) ? "" : s.replaceAll("\"","″");
	}
	
	public static Float parseFloat(String s){
		return ParseUtil.parseFloat(s,0.0f);
	}
	
	public static Float parseFloat(String s, float defaultValue){
		float ret = defaultValue;
		try{
			ret = Float.parseFloat(s);
		}catch(Exception ex){
			return defaultValue;
		}
		return ret;
	}
	
	public static Long parseLong(String s){
		return ParseUtil.parseLong(s,0);
	}
	
	public static Long parseLong(String s, long defaultValue){
		long ret = 0;
		try{
			ret = Long.parseLong(s);
		}catch(Exception ex){
			ret = defaultValue;
		}
		return ret;
	}
	
	public static int parseInt(String s){
		return ParseUtil.parseInt(s,0);
	}
	
	public static int parseInt(String s, int defaultValue){
		int ret = 0;
		try{
			ret = Integer.parseInt(s);
		}catch(Exception ex){
			ret = defaultValue;
		}
		return ret;
	}
	
	public static HashMap<String,String> parse(String s) {
	    HashMap<String,String> map = new HashMap<String,String>();
	    s = s.substring(1, s.length() - 1).trim(); //get rid of the brackets
	    int kpos = 0; //the starting position of the key
	    int eqpos = s.indexOf('='); //the position of the key/value separator
	    boolean more = eqpos > 0;
	    while (more) {
	    	int cmpos = s.indexOf(',', eqpos + 1); //position of the entry separator
	    	String key = s.substring(kpos, eqpos).trim();
	    	if (cmpos > 0) {
	    		map.put(key, s.substring(eqpos + 1, cmpos).trim());
	    		eqpos = s.indexOf('=', cmpos + 1);
	    		more = eqpos > 0;
	    		if (more) {
	    			kpos = cmpos + 1;
	    		}
	    	} else {
	    		map.put(key, s.substring(eqpos + 1).trim());
	    		more = false;
	    	}
	    }
	    return map;
	}
}
