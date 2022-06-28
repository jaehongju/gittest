package com.youhost.common.util;

import java.security.MessageDigest;

public class EncUtil {
	public static String oneWayHash(String value) throws Exception {
		String encrypted = "";
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(new String(value).getBytes("UTF-8"));
		byte[] digested = md.digest();
		encrypted = new String(Base64Coder.encode(digested));
		
		return encrypted;
	}
	
	public static void main(String[] args){
		try{
			
			System.out.println(EncUtil.oneWayHash("testpassword"));
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}