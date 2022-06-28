package kr.youhost.ems.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import kr.youhost.ems.api.model.VoltageEvent;

@Component
public class EmsVoltageEventDao extends EmsDefaultDao {	
	public int saveVoltageEvents(final List<VoltageEvent> voltageEvents) throws Exception {
		if( voltageEvents==null ) {
			return 0;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", voltageEvents);
		return insert(map, "ems_voltage_event.saveVoltageEvents");
	}
}
