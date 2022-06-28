package kr.youhost.ems.api.service;

import kr.youhost.ems.api.exception.NotExistException;
import kr.youhost.ems.api.model.Device;
import kr.youhost.ems.api.response.DeviceResponse;
import kr.youhost.ems.api.response.DeviceTypeResponse;


public interface DeviceService {
	// Section: EMS
	DeviceTypeResponse emsDeviceTypes();
	Device emsGetDevice(final int deviceId) throws NotExistException;
	DeviceResponse emsDevices(final Integer deviceType, final String lastAt) ;
	
	// Section: FMS
	DeviceTypeResponse fmsDeviceTypes();
	Device fmsGetDevice(final int deviceId) throws NotExistException;
	DeviceResponse fmsDevices(final Integer deviceType, final String lastAt) ;
}
