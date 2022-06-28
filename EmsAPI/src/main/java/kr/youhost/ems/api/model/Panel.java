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
public class Panel extends Equip {
	private int		panelId;
	private int		deviceId;
	private int		capacity;
	private String	useCircuit;		// 회로부하사용(Y:사용, N:미사용)
	private String 	useMain;		// 메인사용(Y:사용, N:미사용)
	
	private String regDate;
	private String modDate;
	
	@JsonIgnore
	public boolean hasUseCircuit() {
		return "Y".equals(useCircuit);
	}
	
}
