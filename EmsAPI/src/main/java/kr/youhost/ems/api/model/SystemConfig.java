package kr.youhost.ems.api.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class SystemConfig {
	private int		configNo;
	private String	configGroup;
	private String configNm;
	private String configTitle;
	private String configValue;
	private String configValueType; // 설정값유형(1:문자, 2:INT, 3:FLOAT)
	private String configValueUnit;	
	private String regDate;
	private String modDate;
	
	@JsonIgnore
	public boolean isString() {
		return "1".equals(configValueType);
	}
	@JsonIgnore
	public boolean isInt() {
		return "2".equals(configValueType);
	}
	@JsonIgnore
	public boolean isFloat() {
		return "3".equals(configValueType);
	}
	
}
