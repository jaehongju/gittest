package kr.youhost.ems.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class VoltageEvent  {
	
	private	String	voltageEventAt;	// yyyy-mm-dd
	private	Integer voltageEventNo;
	
	private Integer		deviceId;
	private	String	eventType; // DIP, SWELL
	private Float	endVoltage;
	private Float	peakVoltage;
	private Integer		duration;
	private String	phase;
		
	private	String	regDate;

	// ext
	private Integer	moduleLevel;
	private String  panelNm;
	private String  positionNm;
	
}
