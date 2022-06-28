package kr.youhost.ems.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import kr.youhost.ems.api.dao.EmsDefaultDao;
import kr.youhost.ems.api.model.PanelModuleNow;
import kr.youhost.ems.api.model.PowerNow;

@Component
public class EmsPowerDao extends EmsDefaultDao {
	public int savePowerNows(final List<PowerNow> powerNows) throws Exception {
		if( powerNows==null ) {
			return 0;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", powerNows);
		return insert(map, "ems_power.savePowerNows");
	}
	
	public int savePowerNow(final PowerNow powerNow) throws Exception {
		if( powerNow==null ) {
			return 0;
		}
		return insert(powerNow, "ems_power.savePowerNow");
	}
	
	public List<PanelModuleNow> panelModuleNows(final int panelId) throws Exception {
		return selectList(panelId, "ems_power.panelModuleNows");
	}
}
