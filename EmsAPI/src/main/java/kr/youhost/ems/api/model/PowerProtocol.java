package kr.youhost.ems.api.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class PowerProtocol {

	private int		protocolNo;
	private String	protocol;
	private String circuitType;	// 회로구분(1:1상, 3:3상)
	private String	useYn;
	
	private String regDate;
	private String modDate;

	@JsonIgnore
	public boolean isPhase1() {
		return isPhase1( circuitType );
	}
	@JsonIgnore
	public boolean isPhase3() {
		return isPhase3( circuitType );
	}
	@JsonIgnore
	public static boolean isPhase1(String circuitType) {
		return "1".equals( circuitType );
	}
	@JsonIgnore
	public static boolean isPhase3(String circuitType) {
		return "3".equals( circuitType );
	}
	
	@JsonIgnore
	public boolean isUse() {
		return "Y".equals(useYn);
	}
	
}
