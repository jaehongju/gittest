package kr.youhost.ems.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Response {
	public final static int OK_CODE = 200;

	protected 	int		code;
	protected	String	message;
	
	public Response() {}
	
	public Response(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	@JsonIgnore
	public static Response ok() {
		return new Response(OK_CODE, "RESPONSE_OK");
	}
	@JsonIgnore
	public static Response err5001() {
		return new Response(5001, "필수 파라미터 오류");
	}
	@JsonIgnore
	public boolean isOk() {
		return OK_CODE == code;
	}
}
