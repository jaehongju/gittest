package kr.youhost.ems.api.service.impl;

import kr.youhost.ems.api.dao.EmsModuleDao;
import kr.youhost.ems.api.exception.NotExistException;
import kr.youhost.ems.api.model.Module;
import kr.youhost.ems.api.service.EquipService;
import kr.youhost.ems.api.service.ModuleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youhost.webcore.beans.DefaultService;

@Service
public class ModuleServiceImpl extends DefaultService implements ModuleService {
	Logger logger = LoggerFactory.getLogger(ModuleServiceImpl.class);
	
	@Autowired EmsModuleDao moduleEmsDao;
	//@Autowired EquipService equipService;

	@Override
	public Module findByDeviceChannel( int deviceId, int channelNo ) throws NotExistException {
		Module module = new Module();
		module.setDeviceId(deviceId);
		module.setChannelNo(channelNo);
		
		try {
			return moduleEmsDao.findByDeviceChannel(module);
		} catch (Exception e) {
			throw new NotExistException("module not found for deviceId="+deviceId+", channelNo="+channelNo);
		}
	}
}
