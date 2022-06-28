package kr.youhost.ems.api.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class EquipType {

	private int		equipTypeCd;
	private String equipTypeNm;

	private String groupYn;
	private int		equipLevel;
	private int		dependEquipTypeCd;
	
	@JsonIgnore
	public boolean isGroup() {
		return "Y".equals(groupYn);
	}

	@JsonIgnore
	public static final int ROOT = 0;
	@JsonIgnore
	public static final int CENTER = 1;
	@JsonIgnore
	public static final int FLOOR = 2;
	@JsonIgnore
	public static final int SECTOR = 3;
	@JsonIgnore
	public static final int LINE = 4;	
	@JsonIgnore
	public static final int PANEL = 5;
	@JsonIgnore
	public static final int MODULE = 6;
	@JsonIgnore
	public static final int DEVICE = 7;

	@JsonIgnore
	public static boolean isRoot(int equipTypeCd) {
		return equipTypeCd == EquipType.ROOT;
	}
	@JsonIgnore
	public static boolean isCenter(int equipTypeCd) {
		return equipTypeCd == EquipType.CENTER;
	}
	@JsonIgnore
	public static boolean isFloor(int equipTypeCd) {
		return equipTypeCd == EquipType.FLOOR;
	}
	@JsonIgnore
	public static boolean isSector(int equipTypeCd) {
		return equipTypeCd == EquipType.SECTOR;
	}
	@JsonIgnore
	public static boolean isLine(int equipTypeCd) {
		return equipTypeCd == EquipType.LINE;
	}
	
	@JsonIgnore
	public static boolean isPanel(int equipTypeCd) {
		return equipTypeCd == EquipType.PANEL;
	}
	@JsonIgnore
	public static boolean isModule(int equipTypeCd) {
		return equipTypeCd == EquipType.MODULE;
	}
	@JsonIgnore
	public static boolean isDevice(int equipTypeCd) {
		return equipTypeCd == EquipType.DEVICE;
	}
	
}
