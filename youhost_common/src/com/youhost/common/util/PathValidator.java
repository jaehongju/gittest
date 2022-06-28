package com.youhost.common.util;

import java.util.List;

public class PathValidator {
	private List<String> availPaths;
	public List<String> getAvailPaths() {
		return availPaths;
	}
	public void setAvailPaths(List<String> availPaths) {
		this.availPaths = availPaths;
	}

	public boolean isContain(String path){
		String[] a = new String[availPaths.size()];
		availPaths.toArray(a);
		boolean res = false;
		try{
			for(String strCheck : a){
				if(strCheck.startsWith("*")){
					strCheck = strCheck.substring(1);
					if(strCheck.endsWith("*")){
						strCheck = strCheck.substring(0,strCheck.length()-1);
						res = path.indexOf(strCheck)>-1;
					}else{
						res = path.endsWith(strCheck);
					}
				}else if(strCheck.endsWith("*")){
					strCheck = strCheck.substring(0,strCheck.length()-1);
					res = path.startsWith(strCheck);
				}else{
					res = path.equals(strCheck);
				}
				if(res) return res;
			}
		}catch(Exception e){
			res = false;
		}
		return res;
	}
}
