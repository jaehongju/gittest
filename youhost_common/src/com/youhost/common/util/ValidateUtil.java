package com.youhost.common.util;

import java.util.List;

public class ValidateUtil {
	public static boolean isEmpty(String str){
		if(str==null) return true;
		if(str.trim().equals("")) return true;
		return false;
	}
	
	public static boolean isEmpty(List<?> list){
		if(list==null) return true;
		if(list.size()==0) return true;
		return false;
	}
	
	public static boolean isEmpty(Object... objs){
		for(Object obj : objs){
			if(ValidateUtil.isEmpty(obj)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isEmpty(Object obj){
		if(obj instanceof List) return isEmpty((List<?>) obj);
		if(obj instanceof String) return isEmpty((String) obj);
		if(obj == null) return true;
		return false;
	}
	
	public static boolean isInclude(String list, String check){
		return isInclude(list, ",", check);
	}
	public static boolean isInclude(String list, String seperater, String check){
		boolean res = false;
		try{
			if(ValidateUtil.isEmpty(list)){
				return true;
			}
			String[] excludeStrs = null;
			if(ValidateUtil.isEmpty(seperater)){
				excludeStrs = list.split(seperater);
			}else{
				excludeStrs = list.split(",");
			}
			res = isInclude(excludeStrs, check);
		}catch(Exception ex){
			res = false;
		}
		return res;
	}
	
	public static boolean isInclude(List<String> excludeList, String check){
		String[] a = new String[excludeList.size()];
		return isInclude(excludeList.toArray(a), check);
	}
	
	public static boolean isInclude(String[] excludeList, String check){
		boolean res = false;
		try{
			for(String strCheck : excludeList){
				if(strCheck.startsWith("*")){
					strCheck = strCheck.substring(1);
					if(strCheck.endsWith("*")){
						strCheck = strCheck.substring(0,strCheck.length()-1);
						res = check.indexOf(strCheck)>-1;
					}else{
						res = check.endsWith(strCheck);
					}
				}else if(strCheck.endsWith("*")){
					strCheck = strCheck.substring(0,strCheck.length()-1);
					res = check.startsWith(strCheck);
				}else{
					res = check.equals(strCheck);
				}
				if(res) return res;
			}
		}catch(Exception e){
			res = false;
		}
		return res;
	}
}
