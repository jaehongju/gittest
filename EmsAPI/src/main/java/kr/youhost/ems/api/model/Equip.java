package kr.youhost.ems.api.model;

import lombok.Data;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@ToString(callSuper=true)
@Data
public class Equip {
	protected int		equipId;
	protected int		equipTypeCd;
	protected String	equipName;
	protected int 		parentEquipId;
	protected String 	useYn;
	protected int		orderNo;
	protected String 	regDate;
	protected String 	modDate;
	
	//
	private String equipTypeNm;
	
	@JsonIgnore
	public boolean isUse() {
		return "Y".equals(useYn);
	}
	
	@JsonIgnore
	public boolean isRoot() {
		return EquipType.isRoot(equipTypeCd);
	}
	@JsonIgnore
	public boolean isCenter() {
		return EquipType.isCenter(equipTypeCd);
	}
	
	@JsonIgnore
	public boolean isPanel() {
		return EquipType.isPanel(equipTypeCd);
	}
	@JsonIgnore
	public boolean isModule() {
		return EquipType.isModule(equipTypeCd);
	}
	@JsonIgnore
	public boolean isDevice() {
		return EquipType.isDevice(equipTypeCd);
	}
	@JsonIgnore
	public boolean isLine() {
		return EquipType.isLine(equipTypeCd);
	}
	@JsonIgnore
	public boolean isGroup() {
		return !(isPanel() || isModule() || isDevice());
	}
	
	
}
