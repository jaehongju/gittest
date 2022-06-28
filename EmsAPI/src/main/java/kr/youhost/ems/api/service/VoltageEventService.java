package kr.youhost.ems.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.youhost.ems.api.common.VoltageEventResult;
import kr.youhost.ems.api.model.VoltageEvent;
import kr.youhost.ems.api.response.Response;

public interface VoltageEventService {
	Response saveVoltageEvents(final List<VoltageEvent> voltageEvents);
}
