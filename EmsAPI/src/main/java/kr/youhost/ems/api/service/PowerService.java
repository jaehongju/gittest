package kr.youhost.ems.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.youhost.ems.api.exception.NotExistException;
import kr.youhost.ems.api.model.PanelModuleNow;
import kr.youhost.ems.api.model.PowerNow;
import kr.youhost.ems.api.response.Response;

public interface PowerService {
	Response savePowerNows(final List<PowerNow> powerNows);
	Response savePowerNow(final PowerNow powerNow);
	
	List<PanelModuleNow> panelModuleNows(final int panelId) throws NotExistException;
}
