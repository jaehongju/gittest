package kr.youhost.ems.api.model.ext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class SectorPanelBase {
	private int		sectorId;
	
	private int		panelId;	
	private String	panelNm;
	
	private String centerNm;
	private String floorNm;
	private String sectorNm;
	
}
