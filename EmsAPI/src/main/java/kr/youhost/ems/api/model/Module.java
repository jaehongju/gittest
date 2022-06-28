package kr.youhost.ems.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
@Data
public class Module extends Equip {
	protected int		moduleId;
	protected int		moduleType; 	// 모듈구분(0:메인, 1:회로)
	protected int		moduleLevel;
	
	protected String	circuitType;		// 회로구분(1:1상,3:3상)
	protected String	circuitRst;		// 1상 PHASE(R, S, T)
	protected String circuitSide;		// 회로면 (L, M, R)
	protected int		circuitPosition;		// 회로위치 (0:메인, 1,2,3,...)
	
	protected int		deviceId; 	
	protected int		channelNo;
	
	protected String useYn;
	protected String alarmYn;
	
	protected float minPower;
	protected float maxPower;
	protected float maxZctcurrent;
	protected float stdVoltage;
	
	protected String regDate;
	protected String modDate;
	
	// ext
	protected String circuitTypeNm;
	
	@JsonIgnore
	public boolean isMain() {
		return moduleType==0;
	}
	@JsonIgnore
	public boolean isCircuit() {
		return moduleType==1;
	}
	
	@JsonIgnore
	public boolean isPhase1() {
		return PowerProtocol.isPhase1( circuitType );
	}
	@JsonIgnore
	public boolean isPhase3() {
		return PowerProtocol.isPhase3( circuitType );
	}

	@JsonIgnore
	public boolean isR() {
		return "R".equals( circuitRst );
	}
	@JsonIgnore
	public boolean isS() {
		return "S".equals( circuitRst );
	}
	@JsonIgnore
	public boolean isT() {
		return "T".equals( circuitRst );
	}
	
	@JsonIgnore
	public boolean isUse() {
		return "Y".equals(useYn);
	}
	@JsonIgnore
	public boolean isAlarm() {
		return "Y".equals(alarmYn);
	}
	
	@JsonIgnore
	public boolean isLeft() {
		return "L".equals(circuitSide);
	}
	@JsonIgnore
	public boolean isMiddle() {
		return "M".equals(circuitSide);
	}
	@JsonIgnore
	public boolean isRight() {
		return "R".equals(circuitSide);
	}	
	
}
