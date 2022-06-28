package com.youhost.common.vo;

import com.youhost.common.code.ProcResultStatus;
import com.youhost.webcore.beans.DefaultVo;

public class ProcResultVo extends DefaultVo {
	private ProcResultStatus status;
	private String code;
	private Object data=null;
	public ProcResultVo(String code){
		this.code = code;
	}
	public ProcResultStatus getStatus() {
		return status;
	}
	public void setStatus(ProcResultStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return this.code+this.getStatus().getStr();
	}
	public boolean isSucc(){
		return this.getStatus().getCode()<500;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}