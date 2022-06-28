package kr.youhost.ems.api.dao;

import kr.youhost.ems.api.dao.EmsDefaultDao;
import kr.youhost.ems.api.model.Module;

import org.springframework.stereotype.Component;

@Component
public class EmsModuleDao extends EmsDefaultDao {
	/*
	 * find module by deviceId & channelNo
	 */
	public Module findByDeviceChannel(Module module) throws Exception {
		return selectOne( module, "ems_module.findByDeviceChannel");
	}
}
