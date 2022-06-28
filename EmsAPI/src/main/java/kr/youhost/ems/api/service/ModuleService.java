package kr.youhost.ems.api.service;

import kr.youhost.ems.api.exception.NotExistException;
import kr.youhost.ems.api.model.Module;

public interface ModuleService {
	Module findByDeviceChannel(final int deviceId, final int channelNo) throws NotExistException;
}
