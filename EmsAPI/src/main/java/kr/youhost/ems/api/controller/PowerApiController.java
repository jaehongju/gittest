package kr.youhost.ems.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.youhost.ems.api.model.PowerNow;
import kr.youhost.ems.api.model.VoltageEvent;
import kr.youhost.ems.api.response.Response;
import kr.youhost.ems.api.service.PowerService;
import kr.youhost.ems.api.service.VoltageEventService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PowerApiController extends ApiBaseController {
	Logger logger = LoggerFactory.getLogger(PowerApiController.class);
	
	@Autowired PowerService powerService;
	@Autowired VoltageEventService voltageEventService;
	
	@RequestMapping(value = {"/api/ems/power/now"}, method = {RequestMethod.POST})
	public @ResponseBody Response powerNow(HttpServletRequest request , @RequestBody PowerNow powerNow) {
		if(logger.isDebugEnabled()) logger.debug("powerNow="+powerNow.toString());
		return powerService.savePowerNow(powerNow);		
	}
	@RequestMapping(value = {"/api/ems/power/nows"}, method = {RequestMethod.POST})
	public @ResponseBody Response powerNows(HttpServletRequest request , @RequestBody List<PowerNow> powerNows) {
		if(logger.isDebugEnabled()) logger.debug("powerNows="+powerNows.toString());
		return powerService.savePowerNows(powerNows);		
	}
	
	@RequestMapping(value = {"/api/ems/voltage/events"}, method = {RequestMethod.POST})
	public @ResponseBody Response voltageEvents(HttpServletRequest request , @RequestBody List<VoltageEvent> voltageEvents) {
		if(logger.isDebugEnabled()) logger.debug("voltageEvents="+voltageEvents.toString());
		return voltageEventService.saveVoltageEvents(voltageEvents);		
	}
	
	// 2019.04.11 LEE Start
	/*
	@RequestMapping(value = {"/api/fms/pm"}, method = {RequestMethod.POST})
	public @ResponseBody Response voltageFmsEvents(HttpServletRequest request , @RequestBody List<VoltageEvent> voltageEvents) {
		if(logger.isDebugEnabled()) logger.debug("voltageEvents="+voltageEvents.toString());
		return voltageEventService.saveFmsVoltageEvents(voltageEvents);		
	}	

	@RequestMapping(value = {"/api/fms/th"}, method = {RequestMethod.POST})
	public @ResponseBody Response temperatureFmsEvents(HttpServletRequest request , @RequestBody List<TemperatureEventVO> temperatureEventVO) {
		if(logger.isDebugEnabled()) logger.debug("temperatureEvents="+temperatureEventVO.toString());
		return voltageEventService.saveFmsTemperatureEvents(temperatureEventVO);		
	}	

	@RequestMapping(value = {"/api/fms/loadcell"}, method = {RequestMethod.POST})
	public @ResponseBody Response loadCellFmsEvents(HttpServletRequest request , @RequestBody List<RoadCellNowVO> loadCellNowVO) {
		if(logger.isDebugEnabled()) logger.debug("loadCellNowEvents="+loadCellNowVO.toString());
		return voltageEventService.saveFmsloadCellNowEvents(loadCellNowVO);		
	}
	*/	
	// 2019.04.11 LEE End
	
}
