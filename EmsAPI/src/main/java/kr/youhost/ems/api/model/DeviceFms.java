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
public class DeviceFms extends Equip {
	private int			deviceId;
	private int			dvid;
	private int			deviceTypeCd;
	private String		ip;
	private String		ipaddress;
	private Integer	    port;
	private Integer	    protocolNo;
	private String	    ptclnm;
	private Integer	    powerCh;
	private String 		useyn;
	
	private String 		cdt;
	private String 		modDate;

	// ext
	private String		powerProtocol;
	
	@JsonIgnore
	public boolean isUse() {
		return "Y".equals(useyn);
	}	
}
