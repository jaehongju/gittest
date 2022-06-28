package kr.youhost.ems.api.service;

import java.util.List;
import java.util.Map;

import kr.youhost.ems.api.model.SystemConfig;
import kr.youhost.ems.api.response.SystemConfigResponse;

public interface SystemConfigService {
	/**
	 * 시스템 설정 조회
	 * @param configNo
	 * @return
	 */
	List<SystemConfig> getSystemConfigs(final Map<String, Object> cond);
	SystemConfig getSystemConfigByName(final String configNm);
	
	SystemConfigResponse systemConfigs();
	SystemConfigResponse systemConfig(String systemConfigNm);
}
