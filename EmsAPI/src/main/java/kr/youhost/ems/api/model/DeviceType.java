package kr.youhost.ems.api.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Data
public class DeviceType {
	private int			deviceTypeCd;
	private String		deviceTypeNm;
	private String		deviceTypeAlias;
	private String 		useYn;
	
	@JsonIgnore
	public boolean isUse() {
		return "Y".equals(useYn);
	}
	
}
