package kr.youhost.ems.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import kr.youhost.ems.api.model.SystemConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(Include.NON_NULL)
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
public class SystemConfigResponse extends Response {
	private 	List<SystemConfig> systemConfigs;
	private 	SystemConfig systemConfig;
		
	@JsonIgnore
	public SystemConfigResponse(int code, String message) {
		super(code, message);
	}
	
	@JsonIgnore
	public SystemConfigResponse(List<SystemConfig> systemConfigs) {
		this(OK_CODE, "RESPONSE_OK");
		this.systemConfigs = systemConfigs;
	}
	@JsonIgnore
	public SystemConfigResponse(SystemConfig systemConfig) {
		this(OK_CODE, "RESPONSE_OK");
		this.systemConfig = systemConfig;
	}
	
}
