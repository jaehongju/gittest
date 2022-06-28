package kr.youhost.ems.api.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.youhost.ems.api.dao.EmsDefaultDao;
import kr.youhost.ems.api.model.Device;
import kr.youhost.ems.api.model.DeviceType;

import org.springframework.stereotype.Component;

@Component
//@Resource(name="emsSqlSession")
public class EmsDeviceDao extends EmsDefaultDao {
	public List<DeviceType> deviceTypes() throws Exception {
		return selectList(null, "ems_device.deviceTypes");
	}

	public Device findByKey(int deviceId) throws Exception {
		return selectOne( deviceId, "ems_device.findByKey");
	}

	public List<Device> devices(final Map<String, Object> cond) throws Exception {
		return selectList(cond, "ems_device.devices");
	}
	
}
