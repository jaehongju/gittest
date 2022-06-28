package kr.youhost.ems.api.model.ext;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class EquipChain {
	private int	parentEquipId;
	private int	childEquipId;
	private int	childEquipTypeCd;
	
	public EquipChain() {}

	@JsonIgnore
	public EquipChain(int parentEquipId, int childEquipId, int childEquipTypeCd) {
		super();
		this.parentEquipId = parentEquipId;
		this.childEquipId = childEquipId;
		this.childEquipTypeCd = childEquipTypeCd;
	};

}
