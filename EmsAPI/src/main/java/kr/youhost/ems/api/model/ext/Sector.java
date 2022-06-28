package kr.youhost.ems.api.model.ext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class Sector {
	private int		sectorId;
	private String	sectorNm;
	
	private String	floorNm;
}
