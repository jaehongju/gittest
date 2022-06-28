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
public class Device extends Equip {
	private int			deviceId;
	private int			deviceTypeCd;
	private String		ipaddress;
	private Integer	port;
	private Integer	protocolNo;
	private Integer	powerCh;
	private String 		useYn;
	
	private String 		regDate;
	private String 		modDate;
	
	private String	setupAt;
	private String	correctAt;

	// ext
	private String		powerProtocol;
	private String		devicePosition;
	
	@JsonIgnore
	public boolean isUse() {
		return "Y".equals(useYn);
	}
	
}
