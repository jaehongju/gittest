package kr.youhost.ems.api.model.ext;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class SectorPanel {
	String centerNm;
	String floorNm;
	String sectorNm;
	
	List<EquipBase> panels;
}
