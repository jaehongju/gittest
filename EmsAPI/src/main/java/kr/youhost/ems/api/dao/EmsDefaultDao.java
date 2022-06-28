package kr.youhost.ems.api.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.youhost.common.db.AbstractDefaultDao;
import com.youhost.common.db.ISqlSessionInvoker;

public class EmsDefaultDao extends AbstractDefaultDao {
	
	@Autowired protected ISqlSessionInvoker sqlSessionInvoker;
	
	// 2019.04.11 LEE Start
	EmsDefaultDao(){
		setSessionId("ems");
	}
	// 2019.04.11 LEE End
	
	@Override
	public ISqlSessionInvoker getSqlSessionInvoker() {
		return sqlSessionInvoker;
	}

	public void setSqlSessionInvoker(ISqlSessionInvoker sqlSessionInvoker) {
		this.sqlSessionInvoker = sqlSessionInvoker;
	}
}