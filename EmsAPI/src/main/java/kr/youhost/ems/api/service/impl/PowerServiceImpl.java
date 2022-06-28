package kr.youhost.ems.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.youhost.ems.api.dao.EmsPowerDao;
import kr.youhost.ems.api.exception.NotExistException;
import kr.youhost.ems.api.model.Module;
import kr.youhost.ems.api.model.PanelModuleNow;
import kr.youhost.ems.api.model.PowerNow;
import kr.youhost.ems.api.response.Response;
import kr.youhost.ems.api.service.ModuleService;
import kr.youhost.ems.api.service.PowerService;

@Service
public class PowerServiceImpl implements PowerService {
	Logger logger = LoggerFactory.getLogger(PowerServiceImpl.class);

	@Autowired EmsPowerDao powerEmsDao;
	@Autowired ModuleService moduleService;
	
	@Override
	public List<PanelModuleNow> panelModuleNows(int panelId) throws NotExistException {
		try {
			return powerEmsDao.panelModuleNows(panelId);
		} catch (Exception e) {
			throw new NotExistException("panelModuleNows fail : "+e.toString());
		}	
	}

	@Override
	public Response savePowerNows(List<PowerNow> powerNows) {
		if( powerNows==null ) return Response.err5001();
		
		List<PowerNow> goodPowerNows = new ArrayList<PowerNow>();
		for( PowerNow powerNow : powerNows ) {
			try {
				Module findModule = moduleService.findByDeviceChannel(powerNow.getDeviceId(), powerNow.getChannelNo());
				if( findModule!=null ) goodPowerNows.add(powerNow);
			} catch (NotExistException e) {				
			}
		}
		
		if( goodPowerNows.size() < 1 ) {	// 유효 장비 정보가 없음
			return new Response(9001, "잘못된 장비 정보");
		}
		
		try {
			int ret = powerEmsDao.savePowerNows(goodPowerNows);
			if( ret>=goodPowerNows.size() ) {
				/* TOBE
				PowerProcess powerProcess = new PowerProcess(goodPowerNows);
				Thread thr = new Thread(powerProcess);
				thr.start();
				*/
				
				return Response.ok();
			}
		} catch (Exception e) {
			logger.error("savePowerNows fail : "+e.toString());
		}
		
		return new Response(9000, "powerNow 추가 실패");
	}
	
	@Override
	public Response savePowerNow(PowerNow powerNow) {
		if( powerNow==null ) return Response.err5001();
		
		List<PowerNow> powerNows = new ArrayList<PowerNow>();
		powerNows.add(powerNow);
		return savePowerNows(powerNows);
	}
}
