package kr.youhost.ems.api.response;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import kr.youhost.ems.api.model.DeviceType;

@JsonInclude(Include.NON_NULL)
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
public class DeviceTypeResponse extends Response {
	private 	List<DeviceType> deviceTypes;
		
	@JsonIgnore
	public DeviceTypeResponse(int code, String message) {
		super(code, message);
	}
	
	@JsonIgnore
	public DeviceTypeResponse(List<DeviceType> deviceTypes) {
		this(OK_CODE, "RESPONSE_OK");
		this.deviceTypes = deviceTypes;
	}
	
}
