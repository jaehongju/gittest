package kr.youhost.ems.api.model.ext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@Data
public class EquipBase {
	
	protected int		equipId;
	protected String	equipName;
	
}
