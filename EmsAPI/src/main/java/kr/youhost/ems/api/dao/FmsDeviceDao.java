package kr.youhost.ems.api.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.youhost.ems.api.dao.FmsDefaultDao;
import kr.youhost.ems.api.model.Device;
import kr.youhost.ems.api.model.DeviceType;

import org.springframework.stereotype.Component;

@Component
public class FmsDeviceDao extends FmsDefaultDao {
	public List<DeviceType> deviceTypes() throws Exception {
		return selectList(null, "fms_device.deviceTypes");
	}

	public Device findByKey(int deviceId) throws Exception {
		return selectOne( deviceId, "fms_device.findByKey");
	}

	public List<Device> devices(final Map<String, Object> cond) throws Exception {
		return selectList(cond, "fms_device.devices");
	}
	
}
