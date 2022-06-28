package kr.youhost.ems.api.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import kr.youhost.ems.api.common.BaseResult;
import kr.youhost.ems.api.model.VoltageEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(Include.NON_NULL)
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
public class VoltageEventResult extends BaseResult {
	private List<VoltageEvent> voltageEvents;
	private	Integer totalCnt;
		
	@JsonIgnore
	public VoltageEventResult(String resultCd, String resultMsg) {
		super(resultCd, resultMsg);
	}
	@JsonIgnore
	public VoltageEventResult(String resultCd) {
		this(resultCd, ResultCd.getResultMsg(resultCd));
	}	
	@JsonIgnore
	public VoltageEventResult(List<VoltageEvent> voltageEvents) {
		this( ResultCd.OK );
		this.voltageEvents = voltageEvents;
	}	
	
}
