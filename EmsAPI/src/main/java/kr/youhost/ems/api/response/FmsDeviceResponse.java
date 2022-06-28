package kr.youhost.ems.api.response;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import kr.youhost.ems.api.model.DeviceFms;

@JsonInclude(Include.NON_NULL)
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
public class FmsDeviceResponse extends Response {
	private List<DeviceFms> devices;
		
	@JsonIgnore
	public FmsDeviceResponse(int code, String message) {
		super(code, message);
	}
	
	@JsonIgnore
	public FmsDeviceResponse(List<DeviceFms> devices) {
		this(OK_CODE, "RESPONSE_OK");
		this.devices = devices;
	}
}
