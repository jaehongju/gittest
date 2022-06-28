package kr.youhost.ems.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.youhost.ems.api.dao.EmsDeviceDao;
import kr.youhost.ems.api.dao.FmsDeviceDao;
import kr.youhost.ems.api.exception.NotExistException;
import kr.youhost.ems.api.model.Device;
import kr.youhost.ems.api.model.DeviceType;
import kr.youhost.ems.api.response.DeviceResponse;
import kr.youhost.ems.api.response.DeviceTypeResponse;
import kr.youhost.ems.api.service.DeviceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youhost.webcore.beans.DefaultService;

@Service
public class DeviceServiceImpl extends DefaultService implements DeviceService {
	Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);
	
	@Autowired EmsDeviceDao deviceEmsDao;
	@Autowired FmsDeviceDao deviceFmsDao;

	// Section: Ems
	@Override
	public DeviceTypeResponse emsDeviceTypes() {
		try {
			List<DeviceType> deviceTypes = deviceEmsDao.deviceTypes();
			if( deviceTypes!=null && deviceTypes.size()>0 ) {
				return new DeviceTypeResponse(deviceTypes);
			}
		} catch (Exception e) {
			logger.error("deviceTypes fail : "+e.toString());
		}
		
		return new DeviceTypeResponse(204, "No Data");
	}

	@Override
	public DeviceResponse emsDevices(Integer deviceType, String lastAt)	 {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("deviceTypeCd", deviceType);
		cond.put("lastAt", lastAt);
		
		try {
			List<Device> devices = deviceEmsDao.devices(cond);
			if( devices!=null && devices.size()>0 ) {
				return new DeviceResponse(devices);
			}
		} catch (Exception e) {
			logger.error("devices fail : "+e.toString());
		}
		
		return new DeviceResponse(204, "No Data");
	}

	@Override
	public Device emsGetDevice(int deviceId) throws NotExistException {
		try {
			return deviceEmsDao.findByKey(deviceId);
		} catch (Exception e) {
			throw new NotExistException();
		}
	}

	// Section: Fms
	@Override
	public DeviceTypeResponse fmsDeviceTypes() {
		try {
			List<DeviceType> deviceTypes = deviceFmsDao.deviceTypes();
			if( deviceTypes!=null && deviceTypes.size()>0 ) {
				return new DeviceTypeResponse(deviceTypes);
			}
		} catch (Exception e) {
			logger.error("deviceTypes fail : "+e.toString());
		}
		
		return new DeviceTypeResponse(204, "No Data");	}

	@Override
	public Device fmsGetDevice(int deviceId) throws NotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceResponse fmsDevices(Integer deviceType, String lastAt) {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("deviceTypeCd", deviceType);
		cond.put("lastAt", lastAt);
		
		try {
			List<Device> devices = deviceFmsDao.devices(cond);
			if( devices!=null && devices.size()>0 ) {
				return new DeviceResponse(devices);
			}
		} catch (Exception e) {
			logger.error("devices fail : "+e.toString());
		}
		
		return new DeviceResponse(204, "No Data");
	}
}
