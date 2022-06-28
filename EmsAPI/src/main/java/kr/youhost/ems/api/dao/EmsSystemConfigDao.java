package kr.youhost.ems.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.youhost.ems.api.dao.EmsDefaultDao;
import kr.youhost.ems.api.model.SystemConfig;

import org.springframework.stereotype.Component;

@Component
public class EmsSystemConfigDao extends EmsDefaultDao {	
	/**
	 * 조건을 만족하는 SystemConfig 목록 조회
	 *  조건 : 설정구분, 설정이름
	 *           조건이 없으면 전체
	 *           
	 * @param cond
	 * @return
	 * @throws Exception
	 */
	public List<SystemConfig> finds(final Map<String, Object> cond) throws Exception {
		return selectList(cond, "ems_system_config.finds");
	}
}
