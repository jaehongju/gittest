package kr.youhost.ems.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.youhost.ems.api.dao.EmsVoltageEventDao;
import kr.youhost.ems.api.exception.NotExistException;
import kr.youhost.ems.api.model.Device;
import kr.youhost.ems.api.model.VoltageEvent;
import kr.youhost.ems.api.response.Response;
import kr.youhost.ems.api.service.DeviceService;
import kr.youhost.ems.api.service.VoltageEventService;

@Service
public class VoltageEventServiceImpl implements VoltageEventService {
	Logger logger = LoggerFactory.getLogger(VoltageEventServiceImpl.class);
	
	@Autowired EmsVoltageEventDao voltageEventEmsDao;
	@Autowired DeviceService deviceService;	
	
	@Override
	public Response saveVoltageEvents(List<VoltageEvent> voltageEvents) {
		if( voltageEvents==null ) return Response.err5001();
		
		List<VoltageEvent> goodVoltageEvents = new ArrayList<VoltageEvent>();
		for( VoltageEvent voltageEvent : voltageEvents ) {
			try {
				Device findDevice = deviceService.emsGetDevice(voltageEvent.getDeviceId());
				if( findDevice!=null ) goodVoltageEvents.add(voltageEvent);
			} catch (NotExistException e) {				
			}
		}
		
		if( goodVoltageEvents.size() < 1 ) {	// 유효 장비 정보가 없음
			return new Response(9001, "잘못된 장비 정보");
		}
		
		try {
			int ret = voltageEventEmsDao.saveVoltageEvents(goodVoltageEvents);
			if( ret>=goodVoltageEvents.size() ) {			
				return Response.ok();
			}
		} catch (Exception e) {
			logger.error("saveVoltageEvents fail : "+e.toString());
		}
		
		return new Response(9000, "voltageEvents 추가 실패");
	}
}
