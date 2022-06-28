package kr.youhost.ems.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.youhost.ems.api.response.DeviceResponse;
import kr.youhost.ems.api.response.DeviceTypeResponse;
import kr.youhost.ems.api.service.DeviceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceApiController extends ApiBaseController {
	Logger logger = LoggerFactory.getLogger(DeviceApiController.class);
	
	@Autowired DeviceService deviceService;
	
	// Section: EMS
	@RequestMapping(value = {"/api/ems/device/types"}, method = {RequestMethod.GET})
	public @ResponseBody DeviceTypeResponse emsDeviceTypes( HttpServletRequest request ) {
		return deviceService.emsDeviceTypes();		
	}
	

	@RequestMapping(value = {"/api/ems/devices"}, method = {RequestMethod.GET})
	public @ResponseBody DeviceResponse emsDevices(HttpServletRequest request,  @RequestParam Map<String, Object> param ) {
		String lastAt = (String)param.get("lastAt");
		//return _devices(null, lastAt);
		return deviceService.emsDevices(null, lastAt);	
	}


	@RequestMapping(value = {"/api/ems/devices/{deviceType}"}, method = {RequestMethod.GET})
	public @ResponseBody DeviceResponse emsDevices(HttpServletRequest request , @PathVariable Integer deviceType,  @RequestParam Map<String, Object> param) {
		String lastAt = (String)param.get("lastAt");
		//return _devices(deviceType, lastAt);
		return deviceService.emsDevices(deviceType, lastAt);
	}
	
	// Section: FMS
	@RequestMapping(value = {"/api/fms/device/types"}, method = {RequestMethod.GET})
	public @ResponseBody DeviceTypeResponse fmsDeviceTypes( HttpServletRequest request ) {
		return deviceService.fmsDeviceTypes();		
	}
	

	@RequestMapping(value = {"/api/fms/devices"}, method = {RequestMethod.GET})
	public @ResponseBody DeviceResponse fmsDevices(HttpServletRequest request,  @RequestParam Map<String, Object> param ) {
		String lastAt = (String)param.get("lastAt");
		return deviceService.fmsDevices(null, lastAt);
	}

	@RequestMapping(value = {"/api/fms/devices/{deviceType}"}, method = {RequestMethod.GET})
	public @ResponseBody DeviceResponse fmsDevices(HttpServletRequest request , @PathVariable Integer deviceType,  @RequestParam Map<String, Object> param) {
		String lastAt = (String)param.get("lastAt");
		return deviceService.fmsDevices(deviceType, lastAt);		
	}
	
	/*
	private DeviceResponse _devices( Integer deviceType, String lastAt) {
		return deviceService.emsDevices(deviceType, lastAt);		
	}
	*/
}
