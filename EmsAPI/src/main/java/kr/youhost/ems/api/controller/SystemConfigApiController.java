package kr.youhost.ems.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.youhost.ems.api.response.SystemConfigResponse;
import kr.youhost.ems.api.service.SystemConfigService;

@Controller
public class SystemConfigApiController extends ApiBaseController {
	Logger logger = LoggerFactory.getLogger(SystemConfigApiController.class);
	
	@Autowired SystemConfigService systemConfigService;
	
	@RequestMapping(value = {"/api/system/configs"}, method = {RequestMethod.GET})
	public @ResponseBody SystemConfigResponse systemConfigs( HttpServletRequest request ) {
		return systemConfigService.systemConfigs();		
	}

	@RequestMapping(value = {"/api/system/config/{configNm}"}, method = {RequestMethod.GET})
	public @ResponseBody SystemConfigResponse systemConfig(HttpServletRequest request , @PathVariable String configNm) {
		return systemConfigService.systemConfig(configNm);		
	}

}
