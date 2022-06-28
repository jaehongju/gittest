package kr.youhost.ems.api.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class PowerNow {
	int 		deviceId;
	int			channelNo;
	
	float		currentR;
	float		currentS;
	float		currentT;
	float		currentAvg;

	float		tddCurrentR;
	float		tddCurrentS;
	float		tddCurrentT;
	float		thdCurrentR;
	float		thdCurrentS;
	float		thdCurrentT;
	float		zctCurrent;
	
	float		activePowerR;
	float		activePowerS;
	float		activePowerT;
	float		activePower;
	float		reactivePowerR;
	float		reactivePowerS;
	float		reactivePowerT;
	float		reactivePower;
	
	float		apparentPowerR;
	float		apparentPowerS;
	float		apparentPowerT;
	
	float		activePowerQty;
	float		reactivePowerQty;
	float		frequency;
	
	float		powerFactorR;
	float		powerFactorS;
	float		powerFactorT;
	
	float		voltageR;
	float		voltageS;
	float		voltageT;	
	float		avgVoltage;
	float		lineVoltageR;
	float		lineVoltageS;
	float		lineVoltageT;
	float		avgLineVoltage;
	float		thdVoltageR;
	float		thdVoltageS;
	float		thdVoltageT;
	
	float		currentUnbalance;
	float		voltageUnbalance;
	float		lineVoltageUnbalance;
	
	String		regDate;		// yyyy-mm-dd hi:mi:ss.sss
	
//	public String		getRegDate() {
//		return DateUtil.adjustRegDate(30, regDate); 
//	}
	

}
