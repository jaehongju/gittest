package kr.youhost.ems.api.model.ext;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import kr.youhost.ems.api.model.PanelModuleNow;

@JsonInclude(Include.NON_NULL)
@Data
public class PanelCircuit {	
	private List<PanelModuleNow> 	lefts;
	private List<PanelModuleNow>	rights;
	
	@JsonIgnore
	public void addLeft(PanelModuleNow left) {
		if( left!=null ) {
			if( lefts==null ) lefts = new ArrayList<PanelModuleNow>();
			
			if( lefts!=null ) lefts.add(left);	
		}
			
	}
	@JsonIgnore
	public void addRight(PanelModuleNow right) {
		if( right!=null ) {
			if( rights==null ) rights = new ArrayList<PanelModuleNow>();
			
			if( rights!=null ) rights.add(right);	
		}			
	}	
	
}
