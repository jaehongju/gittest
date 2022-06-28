package kr.youhost.ems.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
@Data
public class PanelModuleNow extends PanelModule {
	
	private String moduleNm;
	
	private float	currentR;
	private float	currentS;
	private float	currentT;
	
	private float	activePowerR;
	private float	activePowerS;
	private float	activePowerT;
	
}
