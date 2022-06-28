package kr.youhost.ems.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.youhost.ems.api.dao.EmsSystemConfigDao;
import kr.youhost.ems.api.model.SystemConfig;
import kr.youhost.ems.api.response.SystemConfigResponse;
import kr.youhost.ems.api.service.SystemConfigService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youhost.webcore.beans.DefaultService;

@Service
public class SystemConfigServiceImpl extends DefaultService implements SystemConfigService {
	Logger logger = LoggerFactory.getLogger(SystemConfigServiceImpl.class);

	@Autowired EmsSystemConfigDao systemConfigEmsDao;
	
	@Override
	public SystemConfig getSystemConfigByName(String configNm) {
		if(configNm==null) return null;
		
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("configNm", configNm);
		
		List<SystemConfig> systemConfigs = getSystemConfigs(cond);
		if( systemConfigs!=null && systemConfigs.size()>0 ) {
			return systemConfigs.get(0);
		}
		return null;
		
	}

	@Override
	public List<SystemConfig> getSystemConfigs(Map<String, Object> cond) {
		try {
			return systemConfigEmsDao.finds(cond);
		} catch (Exception e) {
			logger.error("systemConfig finds fail : "+e.toString());
		}
		return null;
	}

	@Override
	public SystemConfigResponse systemConfigs() {
		List<SystemConfig> systemConfigs = getSystemConfigs(null);
		
		if( systemConfigs!=null ) return new SystemConfigResponse(systemConfigs);
		else return new SystemConfigResponse(204, "No Data");
	}

	@Override
	public SystemConfigResponse systemConfig(String systemConfigNm) {
		SystemConfig systemConfig = getSystemConfigByName(systemConfigNm);
		if( systemConfig!=null ) return new SystemConfigResponse(systemConfig);
		else return new SystemConfigResponse(204, "No Data");
	}
}
