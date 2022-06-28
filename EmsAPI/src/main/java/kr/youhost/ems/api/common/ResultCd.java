package kr.youhost.ems.api.common;

import java.util.HashMap;
import java.util.Map;


public final class ResultCd {
	public static Map<String, String> mapResult = new HashMap<String, String>();
	public static final String OK 									= "0000";
	public static final String FAIL									= "9999";
	public static final String NOT_FOUND					= "9998";
	public static final String DUPLICATE						= "9997";
	public static final String CHANGE_FAIL					= "9996";
	public static final String ADD_FAIL							= "9995";
	public static final String DELETE_FAIL						= "9994";
	
	public static final String ARGUMENT_MISSING 		= "9000";
	public static final String INVALID_REQ 					= "9001";
	public static final String ROLE_ERROR 					= "9002";
	
	public static final String SIGNUP_FAIL 					= "9101";
	public static final String LOGIN_FAIL 						= "9102";
	public static final String USER_NOT_FOUND 			= "9103";
	public static final String UPDATE_USER_FAIL 			= "9104";
	public static final String ADD_USER_FAIL	 				= "9105";
	public static final String PASSWORD_FAIL	 			= "9106";
	
	public static final String DUPLICATE_USER	 			= "9107";
	public static final String DELETE_USER_FAIL	 			= "9109";
	
	public static final String ILLEGAL_EQUIP	 			= "9110";
//	

	
	static {
		mapResult.put(OK, "OK");
		mapResult.put(FAIL, "처리오류");
		mapResult.put(NOT_FOUND, "없음 오류");
		mapResult.put(DUPLICATE, "중복 오류");		
		
		mapResult.put(CHANGE_FAIL, "수정 오류");	
		mapResult.put(ADD_FAIL, "등록 오류");	
		mapResult.put(DELETE_FAIL, "삭제 오류");	
		
		mapResult.put(ARGUMENT_MISSING, "Argument 누락");
		mapResult.put(INVALID_REQ, "invalid req");
		mapResult.put(ROLE_ERROR, "작업권한 오류");
		mapResult.put(SIGNUP_FAIL, "사용자등록 오류");
		mapResult.put(LOGIN_FAIL, "login 오류");		
		mapResult.put(USER_NOT_FOUND, "user not found");
		mapResult.put(UPDATE_USER_FAIL, "update user fail");
		mapResult.put(ADD_USER_FAIL, "add user fail");
		mapResult.put(PASSWORD_FAIL, "비밀번호 오류");
		mapResult.put(DUPLICATE_USER, "중복 사용자");
		mapResult.put(DELETE_USER_FAIL, "사용자 삭제 오류");
		mapResult.put(ILLEGAL_EQUIP, "등록되지 않은 장비 오류");
	}

	public static String getResultMsg(String resultCd) {
		return mapResult.get(resultCd);
	}

}
