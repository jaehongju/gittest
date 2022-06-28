package com.youhost.common.code;

public enum ProcResultStatus {
	
	SUCC(0,".succ")
	,FAIL(500,".fail")
	,FAIL_0(500,".fail_0")
	,FAIL_1(501,".fail_1")
	,FAIL_2(502,".fail_2")
	,FAIL_3(503,".fail_3")
	,FAIL_4(504,".fail_4");
	
	private int code;
	private String str;
	ProcResultStatus(int code, String str){
		this.code = code;
		this.str = str;
	}
	public int getCode() {
		return code;
	}
	public String getStr() {
		return str;
	}
}
